package com.service.moneyfllow.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Integer senderAccountId;
    private Integer recipientAccountId;
    private Double amount;
}
