package com.service.moneyfllow.service.impl;

import com.service.moneyfllow.data.AccountBalanceDTO;
import com.service.moneyfllow.data.AccountCreationRequest;
import com.service.moneyfllow.exeptions.AccountNotFoundException;
import com.service.moneyfllow.num.AccountType;
import com.service.moneyfllow.num.Currency;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.persistence.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void createAccount() {
        AccountCreationRequest creationRequest = new AccountCreationRequest();
        creationRequest.setAccountNumber("123456789");
        creationRequest.setBalance(100.0);
        creationRequest.setOwnerName("John Doe");
        creationRequest.setAccountType(AccountType.SAVINGS);
        creationRequest.setCurrency(Currency.USD);

        Account createdAccount = accountService.createAccount(creationRequest);

        verify(accountRepository, times(1)).save(createdAccount);

        // Проверка соответствия значений полей
        assertEquals(creationRequest.getAccountNumber(), createdAccount.getAccountNumber());
        assertEquals(creationRequest.getBalance(), createdAccount.getBalance());
        assertEquals(creationRequest.getOwnerName(), createdAccount.getOwnerName());
        assertEquals(creationRequest.getAccountType(), createdAccount.getAccountType());
        assertEquals(creationRequest.getCurrency(), createdAccount.getCurrency());
    }

    @Test
    void getAccountBalance_existingAccount_returnsBalance() {
        Integer accountId = 1;
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(500.0);

        when(accountRepository.getAccountById(accountId)).thenReturn(account);

        AccountBalanceDTO balance = accountService.getAccountBalance(accountId);

        assertEquals(account.getBalance(), balance);
    }

    @Test
    void getAccountBalance_nonExistingAccount_throwsException() {
        Integer nonExistingAccountId = 999;

        when(accountRepository.getAccountById(nonExistingAccountId)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountBalance(nonExistingAccountId));
    }
}
