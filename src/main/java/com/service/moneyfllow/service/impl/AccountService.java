package com.service.moneyfllow.service.impl;

import com.service.moneyfllow.data.AccountCreationRequest;
import com.service.moneyfllow.exeptions.AccountNotFoundException;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.persistence.repository.AccountRepository;
import com.service.moneyfllow.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountCreationRequest creationRequest){
        Account account = Account.builder()
                .accountNumber(creationRequest.getAccountNumber())
                .balance(creationRequest.getBalance())
                .ownerName(creationRequest.getOwnerName())
                .accountType(creationRequest.getAccountType())
                .currency(creationRequest.getCurrency())
                .build();

        accountRepository.save(account);
        return account;
    }

    @Override
    public Double getAccountBalance(Integer accountId){
        Account account = accountRepository.getAccountById(accountId);
        if (account != null) {
            return account.getBalance();
        } else {
            throw new AccountNotFoundException("Account not found with id: " + accountId);
        }
    }
}
