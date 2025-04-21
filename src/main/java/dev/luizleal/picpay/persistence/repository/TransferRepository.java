package dev.luizleal.picpay.persistence.repository;

import dev.luizleal.picpay.persistence.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
