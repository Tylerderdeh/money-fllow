package com.service.moneyfllow.exeptions;

public class ExchangeRateNotFoundException extends RuntimeException{
    public ExchangeRateNotFoundException(String message) {
        super(message);
    }
}
