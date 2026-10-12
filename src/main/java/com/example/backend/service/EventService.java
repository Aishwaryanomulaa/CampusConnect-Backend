
package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Event;
import com.example.backend.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create event
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get event by ID
    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    // Update event
    public Event updateEvent(int id, Event event) {
        event.setId(id);
        return eventRepository.save(event);
    }

    // Delete event
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
