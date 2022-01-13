package com.ibmw.userportal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibmw.userportal.model.Photo;
import com.ibmw.userportal.model.PhotoResponseMessage;
import com.ibmw.userportal.service.PhotoService;

@CrossOrigin(origins = "*", maxAge = 4200)
@RestController
@RequestMapping("/home")
public class PhotoController {
	@Autowired
	PhotoService photoservice;

	@PostMapping("/upload")
	public ResponseEntity<PhotoResponseMessage> uploadFile(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("id") Long userId) throws IOException {
		
		String message = "";
		try {
			photoservice.store(file,userId);

			message = "Photo is uploaded successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new PhotoResponseMessage(message));
		} catch (Exception e) {
			message = "Failed to upload photo " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new PhotoResponseMessage(message));
		}
	}

//	@GetMapping("/getphoto/{id}")
//	public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
//		Photo photo = photoservice.getFile(id);
//
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
//				.body(photo.getData());
//	}
	
	@GetMapping("/getphoto/{id}")
	public Photo getPhoto(@PathVariable Long id) {
		Photo photo = photoservice.getFile(id);
		return photo;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletephoto(@PathVariable Long id) {

		return photoservice.deletePhoto(id);
	}

}
