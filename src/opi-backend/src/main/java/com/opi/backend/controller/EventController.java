package com.opi.backend.controller;

import com.opi.backend.model.Event;
import com.opi.backend.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/bottle/{bottleId}")
    public List<Event> getByBottle(@PathVariable Integer bottleId) {
        return eventRepository.findByBottleId(bottleId);
    }

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!eventRepository.existsById(id)) return ResponseEntity.notFound().build();
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}