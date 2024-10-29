package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adm/institutions/{instid}/users")
public class AdmSaamfiUserManageControllerImpl {

	@Autowired
	private SaamfiUserService userService;

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('Admin-users','Create-users')")
	public ResponseEntity<?> addNewUser(@RequestBody SaamfiNewuserSdtInDTO userDto, @PathVariable long instid) {
		try {
			return ResponseEntity.ok(userService.createUser(instid, userDto));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/edit/{userId}")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> modifyUserSuper(@RequestBody SaamfiNewuserSdtInDTO userDto, @PathVariable long userId)
			throws Exception {
		// TODO userDto is for change passord

		try {
			System.out.println("ENTRAMOS MELOS");
			userService.modifyUser(userId, userDto);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}
}
