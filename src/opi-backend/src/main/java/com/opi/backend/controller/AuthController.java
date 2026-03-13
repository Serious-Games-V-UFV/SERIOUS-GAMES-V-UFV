package com.opi.backend.controller;

import com.opi.backend.model.Account;
import com.opi.backend.model.RegisterRequest;
import com.opi.backend.repository.AccountRepository;
import com.opi.backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest body) {
        if (accountRepository.findByEmail(body.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        Account account = new Account();
        account.setFirstName(body.getFirstName());
        account.setLastName1(body.getLastName1());
        account.setEmail(body.getEmail());
        account.setPassword(passwordEncoder.encode(body.getPassword()));
        account.setHeight(body.getHeight());
        account.setWeight(body.getWeight());
        account.setDesiredWater(body.getDesiredWater());
        return ResponseEntity.ok(accountRepository.save(account));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Optional<Account> account = accountRepository.findByEmail(body.get("email"));
        if (account.isEmpty() || !passwordEncoder.matches(body.get("password"), account.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(account.get().getEmail())));
    }
}