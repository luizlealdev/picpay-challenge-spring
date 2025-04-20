package dev.luizleal.picpay.controller;

import dev.luizleal.picpay.exception.PicPayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException exception) {
        return exception.toProblemDetail();
    }

}
