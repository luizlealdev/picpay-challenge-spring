package dev.luizleal.picpay.service;

import dev.luizleal.picpay.controller.dto.TransferDTO;
import dev.luizleal.picpay.exception.InsufficientBalanceException;
import dev.luizleal.picpay.exception.TransferNotAllowedForWalletTypeException;
import dev.luizleal.picpay.exception.TransferNotAuthorizedException;
import dev.luizleal.picpay.exception.WalletNotFoundException;
import dev.luizleal.picpay.persistence.entity.Transfer;
import dev.luizleal.picpay.persistence.entity.Wallet;
import dev.luizleal.picpay.persistence.repository.TransferRepository;
import dev.luizleal.picpay.persistence.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(
            NotificationService notificationService,
            AuthorizationService authorizationService,
            TransferRepository transferRepository,
            WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {
        var sender = walletRepository
                .findById(transferDTO.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));
        var receiver = walletRepository
                .findById(transferDTO.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult =transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceGreaterOrEqualThan(transferDTO.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
