package dev.luizleal.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Insufficient Balance");
        pd.setDetail("You Cannot Transfer A Value Greater Than Your Current Balance");

        return pd;
    }

}
