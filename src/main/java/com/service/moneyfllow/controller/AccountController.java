package com.service.moneyfllow.controller;

import com.service.moneyfllow.data.AccountCreationRequest;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable("accountId") Integer accountId) {
        return ResponseEntity.ok(accountService.getAccountBalance(accountId));
    }

}
