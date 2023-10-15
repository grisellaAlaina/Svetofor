package com.example.exeption;


public enum ErrorCode {
    CONFIG_READ_ERROR("CONFIG_READ_ERROR"),
    CONFIG_UPDATE_ERROR("CONFIG_UPDATE_ERROR"),
    BACKGROUND_PROCESS_ERROR("BACKGROUND_PROCESS_ERROR");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

