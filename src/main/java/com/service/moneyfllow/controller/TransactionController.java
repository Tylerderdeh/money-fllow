package com.service.moneyfllow.controller;

import com.service.moneyfllow.data.TransferRequest;
import com.service.moneyfllow.persistence.entity.Transaction;
import com.service.moneyfllow.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TransactionController {

    private final ITransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferFunds(@RequestBody TransferRequest transferRequest) {
        return ResponseEntity.ok(transactionService.transferFunds(transferRequest));
    }


}
