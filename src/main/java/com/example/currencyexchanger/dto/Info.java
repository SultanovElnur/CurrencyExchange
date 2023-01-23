package com.example.currencyexchanger.dto;

import lombok.Getter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Info {
     int timestamp;
     double quote;
}
