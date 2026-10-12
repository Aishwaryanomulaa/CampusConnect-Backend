
package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.Event;
import com.example.backend.service.EventService;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {

    @Autowired
    private EventService eventService;

    // CREATE - Add an event
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event savedEvent = eventService.addEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // READ - Get all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // READ - Get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE - Update an event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable int id,
            @RequestBody Event event) {

        if (eventService.getEventById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    // DELETE - Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        if (eventService.getEventById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
