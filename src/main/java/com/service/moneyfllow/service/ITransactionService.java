package com.service.moneyfllow.service;

import com.service.moneyfllow.data.TransferRequest;
import com.service.moneyfllow.persistence.entity.Transaction;

public interface ITransactionService {
    Transaction transferFunds(TransferRequest transferRequest);
    double getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode);
}
