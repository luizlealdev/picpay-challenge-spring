package dev.luizleal.picpay.persistence.repository;

import dev.luizleal.picpay.persistence.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
