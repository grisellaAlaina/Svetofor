package com.example.exeption;

public class BackgroundProcessException extends ApplicationException {
    public BackgroundProcessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
