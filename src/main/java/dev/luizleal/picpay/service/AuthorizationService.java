package dev.luizleal.picpay.service;

import dev.luizleal.picpay.client.AuthorizationClient;
import dev.luizleal.picpay.exception.PicPayException;
import dev.luizleal.picpay.persistence.entity.Transfer;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transfer transfer){
        var resp = authorizationClient.isAuthorized();
        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
