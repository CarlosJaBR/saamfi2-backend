package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.UserSaamfiUserController;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserProfileInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserProfileOutDTO;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;

import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;

@RestController
@RequestMapping("/usr/institutions/{instid}/users")
@CrossOrigin(origins = "*")
public class UserSaamfiUserControllerImpl implements UserSaamfiUserController {

	private SaamfiUserService userService;

	@Autowired
	public UserSaamfiUserControllerImpl(SaamfiUserService userService) {
		this.userService = userService;
	}

	@Override
	@GetMapping("/userProfile/{userid}")
	public ResponseEntity<SaamfiUserProfileOutDTO> getDataProfile(@PathVariable("userid") Long userId) {
		SaamfiUserProfileOutDTO user;
		try {
			user=userService.getDataProfile(userId);
		}
		catch(NoResultException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@Override
	@PutMapping("/modifyUserProfile/{userid}")	
	public ResponseEntity<SaamfiUserProfileOutDTO> modifyProfile(@PathVariable("userid") Long userId,@RequestBody SaamfiUserProfileInDTO profile) {
		SaamfiUserProfileOutDTO user;
		try {
			user=userService.modifyProfile(profile);
		}
		catch(NoResultException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<>(user, HttpStatus.OK);
	}

}


