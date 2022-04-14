package com.autoParkingLot.autoParkingLot.exception;

public class ApiExceptionBadRequest extends RuntimeException {
    public ApiExceptionBadRequest(String message) {
        super(message);
    }
}
