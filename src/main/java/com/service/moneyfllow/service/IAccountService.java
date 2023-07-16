package com.service.moneyfllow.service;

import com.service.moneyfllow.data.AccountBalanceDTO;
import com.service.moneyfllow.data.AccountCreationRequest;
import com.service.moneyfllow.persistence.entity.Account;

public interface IAccountService {
     Account createAccount(AccountCreationRequest creationRequest);
     AccountBalanceDTO getAccountBalance(Integer accountId);
}
