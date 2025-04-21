package dev.luizleal.picpay.service;

import dev.luizleal.picpay.client.AuthorizationClient;
import dev.luizleal.picpay.controller.dto.TransferDTO;
import dev.luizleal.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer){
        var resp = authorizationClient.isAuthorized();
        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
