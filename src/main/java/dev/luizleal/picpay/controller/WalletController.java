package dev.luizleal.picpay.controller;

import dev.luizleal.picpay.controller.dto.CreateWalletDTO;
import dev.luizleal.picpay.persistence.entity.Wallet;
import dev.luizleal.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO walletDTO) {
        var createdWallet = walletService.createWallet(walletDTO);

        return ResponseEntity.ok(createdWallet);
    }
}
