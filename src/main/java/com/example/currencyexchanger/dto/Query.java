package com.example.currencyexchanger.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
class Query {
     String from;
     String to;
     int amount;
}
