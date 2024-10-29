package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.OperSaamfiUserController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;

/**
 * Class to handle the user creation by oper
 *
 *
 * @author Juan Carlos Muñoz
 *
 */
@RestController
@RequestMapping("/oper/institutions/{instid}/systems/{sysid}/users")
@CrossOrigin(origins = "*")
public class OperSaamfiUserControllerImpl implements OperSaamfiUserController {

	private SaamfiUserService userService;

	public OperSaamfiUserControllerImpl(SaamfiUserService userService) {
		this.userService = userService;
	}

	/**
	 * <b>Name:</b> createUser<br>
	 * This method creates a new user in the database <br>
	 *
	 * @param instid:     Id of the institution to which the user and will be
	 *                    associated<br>
	 * @param sysid:      Id of the system to which the user and will be
	 *                    associated<br>
	 *
	 * @param userPerson: DTO with the data of the new user
	 *
	 * @return
	 */
	@Override
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('Create-users-sys','Create-users-glob','Create-users')")
	public ResponseEntity<?> createUser(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiUserStInDTO userPerson) {
		String pass = ControllerAuthorizationValidator.getPasswordHash(userPerson.getUserPassword());
		userPerson.setUserPassword(pass);
		if (ControllerAuthorizationValidator.validateInstitutionAndSystem(instid, sysid)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		// TODO validate according to the permission the filter of inst and sys
		userService.createUser(instid, sysid, userPerson, "oper");
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
