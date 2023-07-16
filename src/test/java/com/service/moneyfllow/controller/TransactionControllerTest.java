package com.service.moneyfllow.controller;

import com.service.moneyfllow.data.TransferRequest;
import com.service.moneyfllow.persistence.entity.Transaction;
import com.service.moneyfllow.service.ITransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private ITransactionService transactionService;

    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        transactionService = mock(ITransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    void transferFunds() {
        TransferRequest request = new TransferRequest(1, 2, 100.0);

        when(transactionService.transferFunds(request)).thenReturn(new Transaction());

        ResponseEntity<Transaction> response = transactionController.transferFunds(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(transactionService, times(1)).transferFunds(request);
    }
}
