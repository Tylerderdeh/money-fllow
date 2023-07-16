package com.service.moneyfllow.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.moneyfllow.persistence.entity.Account;
import com.service.moneyfllow.num.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "recipient_account_id")
    private Account recipientAccount;

    private Double amount;
    private LocalDateTime date;

}
