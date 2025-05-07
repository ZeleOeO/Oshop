package com.zele.ishop.exceptions;

public class NotEnoughInStockException extends RuntimeException {
    public NotEnoughInStockException(String message) {
        super(message);
    }
}
