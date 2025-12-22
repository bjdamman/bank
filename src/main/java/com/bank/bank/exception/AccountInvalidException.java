package com.bank.bank.exception;

public class AccountInvalidException extends RuntimeException {
    private String message;

    public AccountInvalidException() {}

    public AccountInvalidException(String msg) {
        super(msg);
        this.message = msg;
    }
}