package com.opi.backend.repository;

import com.opi.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByBottleId(Integer bottleId);
}