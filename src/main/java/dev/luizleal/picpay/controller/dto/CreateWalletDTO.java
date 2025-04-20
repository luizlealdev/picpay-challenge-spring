package dev.luizleal.picpay.controller.dto;

import dev.luizleal.picpay.persistence.entity.Wallet;
import dev.luizleal.picpay.persistence.entity.WalletTypeEnum;

public record CreateWalletDTO(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletTypeEnum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
