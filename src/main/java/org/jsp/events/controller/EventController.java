package org.jsp.events.controller;

import java.time.LocalDateTime;

import org.jsp.events.entity.Event;
import org.jsp.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Hidden
	@Operation(summary = "To save the events", description = "This API will accept the Event JSON Object and saves it to the db table..")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Event saved successfully ")} )
	@PostMapping
	public ResponseEntity<?> saveEvent(@RequestBody Event event){
		return eventService.saveEvent(event);
	}
	
	@Operation(summary = "To fetch all events", description = "This API will fetch all the events available in the database table..")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All the Events found successfully."), @ApiResponse(responseCode = "404", description = "No Events present in the db.")})
	@GetMapping
	public ResponseEntity<?> findAllEvents(){
		return eventService.findAllEvents();
	}
	
	@GetMapping(value = "/upcoming")
	public ResponseEntity<?> findAllUpcomingEvents(){
		return eventService.findAllUpcomingEvents();
	}
	
	@GetMapping(value = "/ongoing")
	public ResponseEntity<?> findAllOngoingEvents(){
		return eventService.findAllOngoingEvents();
	}

	@GetMapping(value = "/completed")
	public ResponseEntity<?> findAllCompletedEvents(){
		return eventService.findAllCompletedEvents();
	}
	
	@Hidden
	@GetMapping(value = "/deleted")
	public ResponseEntity<?> findAllDeletedEvents(){
		return eventService.findAllDeletedEvents();
	}
	
	@Operation(summary = "To set Event status to ON_GOING", description = "This API will accept the Event ID as Path variable and set the EventStatus to ON_GOING")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "EventStatus updated to ON_GOING successfully"), @ApiResponse(responseCode = "400", description = "Invalid Event Id. Unable to set EventStatus to ON_GOING")})
	@PatchMapping(value = "/ongoing/{id}")
	public ResponseEntity<?> setEventStatusToOngoing(@PathVariable int id){
		return eventService.setEventStatusToOngoing(id);
	}
	@PatchMapping(value = "/completed/{id}")
	public ResponseEntity<?> setEventStatusToCompleted(@PathVariable int id){
		return eventService.setEventStatusToCompleted(id);
	}
	
	@Hidden
	@PatchMapping(value = "/deleted/{id}")
	public ResponseEntity<?> setEventStatusToDeleted(@PathVariable int id){
		return eventService.setEventStatusToDeleted(id);
	}
	
	@GetMapping(value="/in-date")
	public ResponseEntity<?> findEventsBetweenDates(@RequestParam LocalDateTime fromDateTime, 
													@RequestParam LocalDateTime toDateTime)
	{
		return eventService.findEventsBetweenDates(fromDateTime,toDateTime);
	}
	
	
}
