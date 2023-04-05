package com.example.currencyexchanger.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeResultDto {
     boolean success;
     Query query;
     Info info;
     boolean historical;
     LocalDate date;
     double result;

}

