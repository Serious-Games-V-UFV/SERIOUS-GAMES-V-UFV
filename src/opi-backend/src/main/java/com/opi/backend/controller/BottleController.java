package com.opi.backend.controller;

import com.opi.backend.model.Bag;
import com.opi.backend.repository.BottleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bottles")
public class BottleController {

    private final BottleRepository bottleRepository;

    public BottleController(BottleRepository bottleRepository) {
        this.bottleRepository = bottleRepository;
    }

    @GetMapping
    public List<Bag> getAll() {
        return bottleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bag> getById(@PathVariable Integer id) {
        return bottleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    public List<Bag> getByAccount(@PathVariable Integer accountId) {
        return bottleRepository.findByAccountId(accountId);
    }

    @PostMapping
    public Bag create(@RequestBody Bag bag) {
        return bottleRepository.save(bag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!bottleRepository.existsById(id)) return ResponseEntity.notFound().build();
        bottleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}