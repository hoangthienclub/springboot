package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_GATEWAY),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_GATEWAY),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.BAD_GATEWAY),
    USERNAME_INVALID(1004, "Username must be at least 3 characters", HttpStatus.BAD_GATEWAY),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters", HttpStatus.BAD_GATEWAY),
    USER_NOT_EXIST(1006, "User not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1009, "Invalid date of birth", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
