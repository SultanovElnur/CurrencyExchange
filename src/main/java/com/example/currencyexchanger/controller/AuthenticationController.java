package com.example.currencyexchanger.controller;

import com.example.currencyexchanger.dto.AuthenticationDto;
import com.example.currencyexchanger.dto.RegisterUserDto;
import com.example.currencyexchanger.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthenticationController {
    protected final Log logger = LogFactory.getLog(getClass());
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.add(registerUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid AuthenticationDto authenticationDto) {
        return authenticationService.signIn(authenticationDto);
    }
}
