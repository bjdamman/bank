package com.bank.bank.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ACCOUNT_NAME_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "Iban is al aanwezig");
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
