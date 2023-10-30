package com.banco.test.cuentasms.web.error;

public class ConsumingServiceError extends RuntimeException {
    public ConsumingServiceError(String message) {
        super(message);
    }
}
