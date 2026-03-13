package com.opi.backend.controller;

import com.opi.backend.model.Account;
import com.opi.backend.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Integer id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Integer id, @RequestBody Account updated) {
        return accountRepository.findById(id).map(account -> {
            account.setFirstName(updated.getFirstName());
            account.setLastName1(updated.getLastName1());
            account.setLastName2(updated.getLastName2());
            account.setPhone(updated.getPhone());
            account.setEmail(updated.getEmail());
            account.setHeight(updated.getHeight());
            account.setWeight(updated.getWeight());
            account.setBirthDate(updated.getBirthDate());
            account.setDesiredWater(updated.getDesiredWater());
            return ResponseEntity.ok(accountRepository.save(account));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!accountRepository.existsById(id)) return ResponseEntity.notFound().build();
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}