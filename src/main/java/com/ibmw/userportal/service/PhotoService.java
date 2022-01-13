package com.ibmw.userportal.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ibmw.userportal.model.Photo;
import com.ibmw.userportal.model.User;
import com.ibmw.userportal.repository.PhotoRepository;
import com.ibmw.userportal.repository.UserRepository;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photorepository;
	
	@Autowired
	private UserRepository userRepo;

	public Photo store(MultipartFile file,Long id) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Photo photo = new Photo(fileName, file.getContentType(), file.getBytes());
		
		Optional<Photo> photoDB = photorepository.findByUserId(id);
		if(photoDB.isPresent()) {
			Photo photoUpdate = photoDB.get();
			photoUpdate.setType(photo.getType());
			photoUpdate.setData(photo.getData());
			photoUpdate.setName(photo.getName());
			return photorepository.save(photoUpdate);
		}
		Optional<User> userDb = userRepo.findById(id);
		User user = userDb.get();
		photo.setUser(user);

		return photorepository.save(photo);
	}

	public Photo getFile(Long id) {
		return photorepository.findByUserId(id).get();
	}

	public ResponseEntity<?> deletePhoto(Long id) {
		Optional<Photo> photoDb = photorepository.findByUserId(id);

		if(photoDb.isPresent()) {
			photorepository.deleteById(photoDb.get().getId());
			//photorepository.deleteById(id);
			return new ResponseEntity("Deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);

	}

}
