package dev.luizleal.picpay.service;

import dev.luizleal.picpay.controller.dto.CreateWalletDTO;
import dev.luizleal.picpay.exception.WalletDataAlreadyExistsException;
import dev.luizleal.picpay.persistence.entity.Wallet;
import dev.luizleal.picpay.persistence.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO walletDTO) {

        var existentWallet = walletRepository.findByCpfCnpjOrEmail(walletDTO.cpfCnpj(), walletDTO.email());
        if (existentWallet.isPresent()) {
            throw new WalletDataAlreadyExistsException("CPF/CNPJ Or Email Already Exists");
        }

        return walletRepository.save(walletDTO.toWallet());
    }
}
