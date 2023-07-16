package com.service.moneyfllow.controller;

import com.service.moneyfllow.data.AccountBalanceDTO;
import com.service.moneyfllow.data.AccountCreationRequest;
import com.service.moneyfllow.num.AccountType;
import com.service.moneyfllow.num.Currency;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.service.IAccountService;
import com.service.moneyfllow.service.impl.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private IAccountService accountService;

    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        accountController = new AccountController(accountService);
    }

    @Test
    void createAccount() {
        AccountCreationRequest request = new AccountCreationRequest("1234567890", 100.0, "John Doe", AccountType.SAVINGS, Currency.USD);

        Account account = new Account();
        account.setId(1);
        account.setAccountNumber("1234567890");
        account.setBalance(100.0);
        account.setOwnerName("John Doe");
        account.setAccountType(AccountType.SAVINGS);
        account.setCurrency(Currency.USD);

        when(accountService.createAccount(request)).thenReturn(account);

        ResponseEntity<Account> response = accountController.createAccount(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Account returnedAccount = response.getBody();
        assertNotNull(returnedAccount);
        assertEquals(account.getId(), returnedAccount.getId());
        assertEquals(account.getAccountNumber(), returnedAccount.getAccountNumber());
        assertEquals(account.getBalance(), returnedAccount.getBalance());
        assertEquals(account.getOwnerName(), returnedAccount.getOwnerName());
        assertEquals(account.getAccountType(), returnedAccount.getAccountType());
        assertEquals(account.getCurrency(), returnedAccount.getCurrency());

        verify(accountService, times(1)).createAccount(request);
    }

    @Test
    void getAccountBalance() {
        Integer accountId = 1;
        double expectedBalance = 100.0;

        Account account = Account.builder()
                .id(accountId)
                .accountNumber("1234567890")
                .balance(expectedBalance)
                .ownerName("John Doe")
                .accountType(AccountType.SAVINGS)
                .currency(Currency.USD)
                .build();

        AccountBalanceDTO accountBalanceDTO = AccountBalanceDTO.builder()
                .accountId(accountId)
                .balance(expectedBalance)
                .currency(account.getCurrency().toString())
                .build();

        when(accountService.getAccountBalance(accountId)).thenReturn(accountBalanceDTO);

        ResponseEntity<AccountBalanceDTO> response = accountController.getAccountBalance(accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        AccountBalanceDTO returnedAccountBalanceDTO = response.getBody();
        assertNotNull(returnedAccountBalanceDTO);
        assertEquals(accountId, returnedAccountBalanceDTO.getAccountId());
        assertEquals(expectedBalance, returnedAccountBalanceDTO.getBalance());
        assertEquals(account.getCurrency().toString(), returnedAccountBalanceDTO.getCurrency());

        verify(accountService, times(1)).getAccountBalance(accountId);
    }
}
