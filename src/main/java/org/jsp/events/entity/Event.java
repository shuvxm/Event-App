package org.jsp.events.entity;

import java.time.LocalDateTime;

import org.jsp.events.util.EventStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String organizer;
	private String chiefGuest;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	@OneToOne
	private Profile profile;

}
