package com.example.currencyexchanger.controller;

import com.example.currencyexchanger.dto.AuthenticationDto;
import com.example.currencyexchanger.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDataEditController {
    AuthenticationService authenticationService;

    @PatchMapping("/edit/{username}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<?> editUserCredential(@PathVariable String username, @RequestBody @Valid AuthenticationDto newUser, Authentication authentication) {
        return authenticationService.edit(username, newUser, authentication);
    }

    @Transactional
    @RolesAllowed({"ROLE_ADMIN" })
    @DeleteMapping("/{username}")
    public String delete(@PathVariable String username) {
        return authenticationService.delete(username);
    }
}
