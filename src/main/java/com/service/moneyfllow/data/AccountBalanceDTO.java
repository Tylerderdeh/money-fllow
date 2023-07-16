package com.service.moneyfllow.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountBalanceDTO {
    private Integer accountId;
    private Double balance;
    private String currency;

}
