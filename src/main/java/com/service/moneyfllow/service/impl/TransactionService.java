package com.service.moneyfllow.service.impl;

import com.service.moneyfllow.data.ExchangeRateApiResponse;
import com.service.moneyfllow.data.TransferRequest;
import com.service.moneyfllow.exeptions.AccountNotFoundException;
import com.service.moneyfllow.exeptions.ExchangeRateNotFoundException;
import com.service.moneyfllow.exeptions.InsufficientBalanceException;
import com.service.moneyfllow.exeptions.PaymentFailedException;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.persistence.repository.AccountRepository;
import com.service.moneyfllow.persistence.entity.Transaction;
import com.service.moneyfllow.persistence.repository.TransactionRepository;
import com.service.moneyfllow.service.ITransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Value("${exchange-rate-api.base-url}")
    private String baseUrl;

    @Value("${exchange-rate-api.api-key}")
    private String apiKey;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction transferFunds(TransferRequest transferRequest) {
        Account senderAccount = accountRepository.getAccountById(transferRequest.getSenderAccountId());
        Account recipientAccount = accountRepository.getAccountById(transferRequest.getRecipientAccountId());
        Double amount = transferRequest.getAmount();

        validateAccountsExist(senderAccount, recipientAccount);
        validateSufficientBalance(senderAccount, amount);


        double exchangeRate = getExchangeRate(senderAccount.getCurrency().toString(), recipientAccount.getCurrency().toString());
        double convertedAmount = amount * exchangeRate;

        boolean paymentSuccessful = processPayment(senderAccount, amount);

        if (!paymentSuccessful) {
            throw new PaymentFailedException("Payment failed");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + convertedAmount);

        Transaction transaction = Transaction.builder()
                .senderAccount(senderAccount)
                .recipientAccount(recipientAccount)
                .amount(amount)
                .date(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);

        return transaction;
    }

    private void validateAccountsExist(Account senderAccount, Account recipientAccount) {
        if (senderAccount == null || recipientAccount == null) {
            throw new AccountNotFoundException("Sender or recipient account not found");
        }
    }

    private void validateSufficientBalance(Account senderAccount, Double amount) {
        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in the sender account");
        }
    }

     boolean processPayment(Account senderAccount, Double amount) {
        return senderAccount.getBalance() >= amount;
    }

    @Override
    public double getExchangeRate(String sourceCurrencyCode, String targetCurrencyCode) {
        System.out.println(sourceCurrencyCode);
        System.out.println(targetCurrencyCode);
        String url = baseUrl + "/v6/" + apiKey + "/latest/" + sourceCurrencyCode;
        try {
            URI uri = new URI(url);
            RequestEntity<?> request = new RequestEntity<>(getHeaders(), HttpMethod.GET, uri);
            RestTemplate restTemplate = new RestTemplate();
            ExchangeRateApiResponse response = restTemplate.exchange(request, ExchangeRateApiResponse.class).getBody();

            if (response != null && Objects.equals(response.getResult(), "success")) {
                return response.getConversion_rates().get(targetCurrencyCode);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        throw new ExchangeRateNotFoundException("Failed to retrieve exchange rate for the specified currencies");
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
