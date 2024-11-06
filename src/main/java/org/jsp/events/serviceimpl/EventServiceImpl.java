package org.jsp.events.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.events.dao.EventDao;
import org.jsp.events.entity.Event;
import org.jsp.events.exceptionclasses.InvalidEventIdException;
import org.jsp.events.exceptionclasses.NoEventFoundException;
import org.jsp.events.responsestructure.ResponseStructure;
import org.jsp.events.service.EventService;
import org.jsp.events.util.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	private EventDao eventDao;
	
	@Override
	public ResponseEntity<?> saveEvent(Event event) {
		event.setStatus(EventStatus.UP_COMING);
		Event dbEvent = eventDao.saveEvent(event);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Event saved successfully...").body(dbEvent).build());
	}

	@Override
	public ResponseEntity<?> findAllEvents() {
		List<Event> events = eventDao.findAllEvents();
		if(events.isEmpty()) 
			throw NoEventFoundException.builder().message("No Event found in db").build();
			
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("ALl events found successfully").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllUpcomingEvents() {
		List<Event> events = eventDao.findAllUpcomingEvents();
		if(events.isEmpty())
			throw NoEventFoundException.builder().message("No UP_COMING Events found in db").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All UP_COMING events found successfully").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllOngoingEvents() {
		List<Event> events = eventDao.findAllOngoingEvents();
		if(events.isEmpty())
			throw NoEventFoundException.builder().message("No ON_GOING Events found in db").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All ON_GOING events found successfully").body(events).build());
	}
	
	@Override
	public ResponseEntity<?> findAllCompletedEvents() {
		List<Event> events = eventDao.findAllCompletedEvents();
		if(events.isEmpty())
			throw NoEventFoundException.builder().message("No COMPLETED Events found in db").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All COMPLETED events found successfully").body(events).build());
	}

	@Override
	public ResponseEntity<?> findAllDeletedEvents() {
		List<Event> events =  eventDao.findAllDeletedEvents();
		if(events.isEmpty())
			throw NoEventFoundException.builder().message("No DELETED Events found in db").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All DELETED events found successfully").body(events).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToOngoing(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if(event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event ID...Unable to set status to ON_GOING").build();
		Event e = event.get();
		e.setStatus(EventStatus.ON_GOING);
		e = eventDao.updateEvent(e);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Event status updated to ON_GOING successfully").body(e).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToCompleted(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if(event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event ID...Unable to set status to COMPLETED").build();
		Event e = event.get();
		e.setStatus(EventStatus.COMPLETED);
		e = eventDao.updateEvent(e);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Event status updated to COMPLETED successfully").body(e).build());
	}

	@Override
	public ResponseEntity<?> setEventStatusToDeleted(int id) {
		Optional<Event> event = eventDao.findEventById(id);
		if(event.isEmpty())
			throw InvalidEventIdException.builder().message("Invalid Event ID...Unable to set status to DELETED").build();
		Event e = event.get();
		e.setStatus(EventStatus.DELETED);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Event status updated to DELETED successfully").body(e).build());
	}

	@Override
	public ResponseEntity<?> findEventsBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		List<Event> events = eventDao.findAllEvents();
		if(events.isEmpty())
			throw NoEventFoundException.builder().message("No Event found in db").build();
		
		ArrayList<Event> eventsInRange = new ArrayList<>();
		
		for(Event e:events) {
			if(e.getFromDateTime().isAfter(fromDateTime) && e.getToDateTime().isBefore(toDateTime)) {
				eventsInRange.add(e);
			}
		}
		if(eventsInRange.isEmpty())
			throw NoEventFoundException.builder().message("No Event found in given data range").build();
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("All events found in given data range").body(eventsInRange).build());
	}
	
	

}
