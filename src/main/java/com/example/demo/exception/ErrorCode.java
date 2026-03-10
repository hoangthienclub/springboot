package com.example.demo.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Uncategorized error"),
    USER_EXISTED(1002, "User existed"),
    USER_NOT_FOUND(1003, "User not found"),
    USERNAME_INVALID(1004, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters"),
    USER_NOT_EXIST(1006, "User not exist"),
    UNAUTHENTICATED(1007, "Unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
