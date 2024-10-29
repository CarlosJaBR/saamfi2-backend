package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.AdmSaamfiUserController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserrStdOutDTO;
import co.edu.icesi.dev.saamfi.logic.implementation.authentication.SaamfiUserServiceImpl;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;

@RestController
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/users")
@CrossOrigin(origins = "*")
public class AdmSaamfiUserControllerImpl implements AdmSaamfiUserController {

	private SaamfiUserService userService;
	private SaamfiUserRoleService userRoleService;

	public AdmSaamfiUserControllerImpl(SaamfiUserServiceImpl userServiceimpl, SaamfiUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
		this.userService = userServiceimpl;
	}

	@Override
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('Admin-Users','Create-users')")
	public ResponseEntity<?> addUser(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiUserStInDTO userPerson) throws Exception {
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			return new ResponseEntity<Long>(userService.createUser(instid, sysid, userPerson, "Adm"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Long>(HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@PostMapping("/change-password")
	@PreAuthorize("hasAnyRole('Admin-Users')")
	public void changePassword(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiUserPasswordSpeInDTO userPerson) {
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			userService.changePassword(instid, userPerson);
		}

	}

	@Override
	@DeleteMapping("/{userId}")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> DeleteUser(@PathVariable long instid, @PathVariable long sysid, @PathVariable long userId)
			throws Exception {

		if (ControllerAuthorizationValidator.validateInstitution(instid)) {

			userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@Override
	@GetMapping("/{userid}")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> getUserById(@PathVariable long instid, @PathVariable long sysid, @PathVariable long userid)
			throws Exception {

		return new ResponseEntity<SaamfiNewuserSdtInDTO>(userService.getUserById(userid), HttpStatus.OK);

	}

	@Override
	@GetMapping("/")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> getUsers(@PathVariable long instid, @PathVariable long sysid) throws Exception {
		// TODO agregar wrapper de lista
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			List<SaamfiUserrStdOutDTO> respOutDTOs = userService.getUsers(instid);
			return new ResponseEntity<>(respOutDTOs, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	// @Override
	// @PutMapping("/{userId}")
	//// @PreAuthorize("hasAnyRole('Admin-Persons')")
	// public ResponseEntity<?> modifyUserSuper(@PathVariable long instid,
	// @PathVariable long sysid, @PathVariable long userId, @RequestBody
	// SaamfiNewuserSdtInDTO userDto) throws Exception {
	// // TODO userDto is for change passord
	// if (ControllerAuthorizationValidator.validateInstitution(instid)) {
	//
	// userService.modifyUser(userId, userDto);
	// return new ResponseEntity<>(HttpStatus.OK);
	// }
	//
	// return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	// }

	@Override
	@PostMapping("/csv")
	@PreAuthorize("hasAnyRole('Admin-Persons')")
	public void uploadFile(@PathVariable long instid, @RequestParam MultipartFile file) throws Exception {
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			if (userRoleService.hasCSVFormat(file)) {
				try {
					// TODO implementar servicio
				} catch (Exception e) {
					throw new Exception("Error with saving the persons " + e.getMessage());
				}
			}
		}
	}

	@Override
	public ResponseEntity<?> modifyUser(long instid, long sysid, long userId, SaamfiUserSpeInDTO userDto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> modifyUserSuper(long instid, long sysid, long userId, SaamfiUserSpeInDTO userDto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
