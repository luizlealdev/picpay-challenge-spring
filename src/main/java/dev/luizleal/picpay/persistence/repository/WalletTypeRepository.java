package dev.luizleal.picpay.persistence.repository;

import dev.luizleal.picpay.persistence.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
