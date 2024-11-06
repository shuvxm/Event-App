package org.jsp.events.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.events.dao.EventDao;
import org.jsp.events.entity.Event;
import org.jsp.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl implements EventDao{

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Optional<Event> findEventById(int id) {
		return eventRepository.findById(id);
	}

	@Override
	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public List<Event> findAllUpcomingEvents() {
		return eventRepository.findAllUpcomingEvents();
	}

	@Override
	public List<Event> findAllOngoingEvents() {
		return eventRepository.findAllOngoingEvents();
	}

	@Override
	public List<Event> findAllCompletedEvents() {
		return eventRepository.findAllCompletedEvents();
	}

	@Override
	public List<Event> findAllDeletedEvents() {
		return eventRepository.findAllDeletedEvents();
	}

}
