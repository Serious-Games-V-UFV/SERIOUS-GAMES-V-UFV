package com.opi.backend.controller;

import com.opi.backend.model.DailyReminder;
import com.opi.backend.repository.DailyRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-records")
public class DailyRecordController {

    private final DailyRecordRepository dailyRecordRepository;

    public DailyRecordController(DailyRecordRepository dailyRecordRepository) {
        this.dailyRecordRepository = dailyRecordRepository;
    }

    @GetMapping
    public List<DailyReminder> getAll() {
        return dailyRecordRepository.findAll();
    }

    @GetMapping("/account/{accountId}")
    public List<DailyReminder> getByAccount(@PathVariable Integer accountId) {
        return dailyRecordRepository.findByAccountId(accountId);
    }

    @PostMapping
    public DailyReminder create(@RequestBody DailyReminder dailyReminder) {
        return dailyRecordRepository.save(dailyReminder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!dailyRecordRepository.existsById(id)) return ResponseEntity.notFound().build();
        dailyRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}