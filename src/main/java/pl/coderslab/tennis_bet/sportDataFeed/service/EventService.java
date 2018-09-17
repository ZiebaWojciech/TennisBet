package pl.coderslab.tennis_bet.sportDataFeed.service;

import pl.coderslab.tennis_bet.sportDataFeed.entity.Event;

import java.util.List;

public interface EventService {
    Event getOne(int id);
    List<Event> getAll();
    Event save(Event event);
}
