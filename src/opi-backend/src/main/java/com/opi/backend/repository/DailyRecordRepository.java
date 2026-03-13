package com.opi.backend.repository;

import com.opi.backend.model.DailyReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DailyRecordRepository extends JpaRepository<DailyReminder, Integer> {
    List<DailyReminder> findByAccountId(Integer accountId);
}