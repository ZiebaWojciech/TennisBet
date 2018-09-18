package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

import java.util.List;

public interface EventService {
    Event getOne(int id);
    List<Event> getAll();
    List<Event> getUpcomingEvents();
    Event save(Event event);
}
