package org.jsp.events.serviceimpl;

import java.io.File;
import java.io.IOException;

import org.jsp.events.dao.ProfileDao;
import org.jsp.events.entity.Profile;
import org.jsp.events.responsestructure.ResponseStructure;
import org.jsp.events.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService{

	private static final String FOLDER_PATH = "C:\\Users\\Lenovo\\Desktop\\event profiles\\";
	
	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public ResponseEntity<?> saveProfile(MultipartFile file) {

		String name = file.getOriginalFilename();
		String type = file.getContentType();
		String path = FOLDER_PATH + name;
		
		try {
			file.transferTo(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		Profile p = new Profile();
//		p.setName(name);
//		p.setType(type);
		// without using new keyword
		Profile p = Profile.builder().name(name).type(type).path(path).build();
		profileDao.saveProfile(p);
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Profile image saved successfully").body(p).build());
	}

}
