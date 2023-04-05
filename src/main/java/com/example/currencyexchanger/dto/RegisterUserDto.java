package com.example.currencyexchanger.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserDto {
    @NotBlank(message = "User name is required")
    String userName;
    @NotBlank(message = "First name is required")
    String firstName;
    @NotBlank(message = "Last name is required")
    String lastName;
    @Email(message = "Please enter your email address")
    String email;
    @NotBlank(message = "Password is required")
    String password;
}
