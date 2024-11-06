package org.jsp.events.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.events.entity.Profile;

public interface ProfileDao {
	
	Profile saveProfile(Profile profile);
	
	Profile updateProfile(Profile profile);

	Optional<Profile> findProfileById(int id);
	
	List<Profile> findAllProfiles();
	
	
}
