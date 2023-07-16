package com.service.moneyfllow.persistence.entity;

import com.service.moneyfllow.num.AccountType;
import com.service.moneyfllow.num.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountNumber;
    private Double balance;
    private String ownerName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "senderAccount")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "recipientAccount")
    private List<Transaction> incomingTransactions;

}
