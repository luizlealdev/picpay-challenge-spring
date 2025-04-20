package dev.luizleal.picpay.persistence.repository;

import dev.luizleal.picpay.persistence.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByCpfCnpjOrEmail(String CpfCnpj, String email);

}
