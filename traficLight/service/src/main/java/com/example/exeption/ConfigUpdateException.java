package com.example.exeption;

public class ConfigUpdateException extends ApplicationException {
    public ConfigUpdateException(ErrorCode errorCode) {
        super(errorCode);
    }
}