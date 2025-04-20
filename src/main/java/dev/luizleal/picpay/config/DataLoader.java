package dev.luizleal.picpay.config;

import dev.luizleal.picpay.persistence.entity.WalletTypeEnum;
import dev.luizleal.picpay.persistence.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {
    
    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletTypeEnum.values())
                .forEach(walletTypeEnum -> walletTypeRepository.save(walletTypeEnum.get()));
    }

}
