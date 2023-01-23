package com.example.currencyexchanger.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeDto {

    @Pattern(regexp = "^[a-zA-Z\\s]+")
    @NotBlank(message = "clientName can not be null")
    String clientName;
    @NotBlank(message = "fin can not be null")
    String fin;
    @Pattern(regexp = "^[A-Z\\s]{3}")
    @NotBlank(message = "currencySell can not be null")
    String currencySell;
    @Pattern(regexp = "^[A-Z\\s]{3}")
    @NotBlank(message = "currencyBuy can not be null")
    String currencyBuy;
    @Min(0)
    @NotNull(message = "amount can not be null")
    Double amount;

}
