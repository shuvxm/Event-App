package org.jsp.events.service;

import java.time.LocalDateTime;

import org.jsp.events.entity.Event;
import org.springframework.http.ResponseEntity;

public interface EventService {

	ResponseEntity<?> saveEvent(Event event);

	ResponseEntity<?> findAllEvents();

	ResponseEntity<?> findAllUpcomingEvents();
	
	ResponseEntity<?> findAllOngoingEvents();

	ResponseEntity<?> findAllCompletedEvents();

	ResponseEntity<?> findAllDeletedEvents();

	ResponseEntity<?> setEventStatusToOngoing(int id);

	ResponseEntity<?> setEventStatusToCompleted(int id);

	ResponseEntity<?> setEventStatusToDeleted(int id);

	ResponseEntity<?> findEventsBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime);

	

	

}
