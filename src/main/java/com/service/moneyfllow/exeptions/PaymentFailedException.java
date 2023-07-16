package com.service.moneyfllow.exeptions;

public class PaymentFailedException extends RuntimeException{
    public PaymentFailedException(String message) {
        super(message);
    }
}
