package com.business.order.ExceptionHandler;

public class TotalPriceException extends RuntimeException {
    public TotalPriceException(String message) {
        super(message);
    }
}
