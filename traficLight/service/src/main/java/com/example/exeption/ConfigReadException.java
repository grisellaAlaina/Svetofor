package com.example.exeption;

public class ConfigReadException extends ApplicationException {
    public ConfigReadException(ErrorCode errorCode) {
        super(errorCode);
    }
}
