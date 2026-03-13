package com.opi.backend.repository;

import com.opi.backend.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BottleRepository extends JpaRepository<Bag, Integer> {
    List<Bag> findByAccountId(Integer accountId);
}