package com.service.moneyfllow.persistence.repository;

import com.service.moneyfllow.persistence.entity.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AccountRepositoryTest {

    @Test
    void getAccountById_existingAccount_returnsAccount() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

        Integer accountId = 1;
        Account expectedAccount = new Account();
        expectedAccount.setId(accountId);

        when(accountRepository.getAccountById(accountId)).thenReturn(expectedAccount);

        Account actualAccount = accountRepository.getAccountById(accountId);

        assertEquals(expectedAccount, actualAccount);
    }

    @Test
    void getAccountById_nonExistingAccount_returnsNull() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

        Integer nonExistingAccountId = 999;

        when(accountRepository.getAccountById(nonExistingAccountId)).thenReturn(null);

        Account actualAccount = accountRepository.getAccountById(nonExistingAccountId);

        assertEquals(null, actualAccount);
    }
}
