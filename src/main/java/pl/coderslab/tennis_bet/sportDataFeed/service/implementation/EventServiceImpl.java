package pl.coderslab.tennis_bet.sportDataFeed.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennis_bet.entity.enumUtil.RoleName;
import pl.coderslab.tennis_bet.sportDataFeed.entity.*;
import pl.coderslab.tennis_bet.sportDataFeed.entity.enumUtil.EventStatus;
import pl.coderslab.tennis_bet.sportDataFeed.repository.EventRepository;
import pl.coderslab.tennis_bet.sportDataFeed.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getOne(int id) {
        return eventRepository.getOne(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getUpcomingEvents() {
        return eventRepository.getAllByStatusEqualsOrderByTimeOfStartDesc(EventStatus.SCHEDULED);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }


}
