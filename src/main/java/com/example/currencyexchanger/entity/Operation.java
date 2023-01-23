package com.example.currencyexchanger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_operation")
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String clientName;

    String fin;

    String currencySell;

    String currencyBuy;

    Double amount;

    Double quote;

    Double result;

    LocalDate date;



    public Operation(String clientName, String fin, String currencySell, String currencyBuy,
                     Double amount, Double quote, Double result, LocalDate date) {
        this.clientName = clientName;
        this.fin = fin;
        this.currencySell = currencySell;
        this.currencyBuy = currencyBuy;
        this.amount = amount;
        this.quote = quote;
        this.result = result;
        this.date = date;
    }
}
