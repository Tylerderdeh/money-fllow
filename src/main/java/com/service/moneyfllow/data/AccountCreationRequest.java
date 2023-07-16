package com.service.moneyfllow.data;

import com.service.moneyfllow.num.AccountType;
import com.service.moneyfllow.num.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequest {
    private String accountNumber;
    private Double balance;
    private String ownerName;
    private AccountType accountType;
    private Currency currency;

}
