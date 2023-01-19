package com.example.currencyexchanger.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeResultDto {
    public boolean success;
    public Query query;
    public Info info;
    public boolean historical;
    public LocalDate date;
    public double result;
}

