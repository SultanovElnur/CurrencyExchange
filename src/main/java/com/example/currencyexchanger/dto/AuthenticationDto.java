package com.example.currencyexchanger.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationDto {
    @NotBlank(message = "User name is required")
    String userName;

    @NotBlank(message = "Password is required")
    String password;
}
