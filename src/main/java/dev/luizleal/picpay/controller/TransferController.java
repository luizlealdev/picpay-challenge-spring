package dev.luizleal.picpay.controller;

import dev.luizleal.picpay.controller.dto.TransferDTO;
import dev.luizleal.picpay.persistence.entity.Transfer;
import dev.luizleal.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO transferDTO) {
        var resp = transferService.transfer(transferDTO);
        return ResponseEntity.ok(resp);
    }
}
