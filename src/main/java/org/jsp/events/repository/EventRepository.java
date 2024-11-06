package org.jsp.events.repository;

import java.util.List;

import org.jsp.events.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("select e from Event e where e.status='UP_COMING'")
	List<Event> findAllUpcomingEvents();

	@Query("select e from Event e where e.status='ON_GOING'")
	List<Event> findAllOngoingEvents();

	@Query("select e from Event e where e.status='COMPLETED'")
	List<Event> findAllCompletedEvents();

	@Query("select e from Event e where e.status='DELETED'")
	List<Event> findAllDeletedEvents();

}
