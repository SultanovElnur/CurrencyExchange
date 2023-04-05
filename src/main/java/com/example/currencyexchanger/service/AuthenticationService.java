package com.example.currencyexchanger.service;

import com.example.currencyexchanger.dto.AuthenticationDto;
import com.example.currencyexchanger.dto.RegisterUserDto;
import com.example.currencyexchanger.entity.Role;
import com.example.currencyexchanger.entity.User;
import com.example.currencyexchanger.repository.UserRepository;
import com.example.currencyexchanger.util.JwtTokenUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AuthenticationService {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    JwtUserDetailsService userDetailsService;
    JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<?> add(RegisterUserDto registerUserDto) {
        Map<String, Object> responseMap = new HashMap<>();

        User userFromDatabase = userRepository.findUserByUserName(registerUserDto.getUserName());
        if (userFromDatabase != null) {
            responseMap.put("message", "User has been already registered!");
            return ResponseEntity.status(400).body(responseMap);
        }

        User user = new User(
                registerUserDto.getUserName(),
                registerUserDto.getFirstName(),
                registerUserDto.getLastName(),
                registerUserDto.getEmail(),
                new BCryptPasswordEncoder().encode(registerUserDto.getPassword()),
                Role.ROLE_USER);
        userRepository.save(user);

        responseMap.put("username", registerUserDto.getUserName());
        responseMap.put("message", "Account created successfully");
        return ResponseEntity.ok(responseMap);
    }


    public ResponseEntity<?> signIn(AuthenticationDto authenticationDto) {
        Map<String, Object> responseMap = new HashMap<>();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getUserName(),
                        authenticationDto.getPassword()));

        if (!auth.isAuthenticated()) {
            responseMap.put("message", "Incorrect credentials");
            return ResponseEntity.status(401).body(responseMap);
        }

        User user = userRepository.findUserByUserName(authenticationDto.getUserName()); // Ask about this;
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails, user.getRole());
        responseMap.put("message", "Logged In");
        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }

    public ResponseEntity<?> edit(String username, AuthenticationDto newUser, Authentication authentication) {
        Map<String, Object> responseMap = new HashMap<>();

        String currentUsername = authentication.getName();
        // Спросить про получение имени Owner-a
        if (!currentUsername.equals(username) && !currentUsername.equals(userRepository.findUserByUserName("username").getUserName())) {
            responseMap.put("message", "You do not hava permission to edit this account");
            return ResponseEntity.status(403).body(responseMap);
        }

        var toBeEditedUser = userRepository.findUserByUserName(username);
        toBeEditedUser.setUserName(newUser.getUserName());
        toBeEditedUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepository.save(toBeEditedUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(toBeEditedUser.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails, toBeEditedUser.getRole());
        responseMap.put("message", "Account credential is updated successfully");
        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }

    @SneakyThrows
    public String delete(String username) {
        if (!userRepository.existsByUserName(username))
            return String.format("User with username %s not found", username);

        userRepository.deleteByUserName(username);
        return "Successfully deleted";
    }
}
