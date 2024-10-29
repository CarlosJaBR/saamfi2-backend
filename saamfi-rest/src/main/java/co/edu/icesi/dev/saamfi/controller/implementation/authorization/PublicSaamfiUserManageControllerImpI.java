package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.mappers.UserMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public/institutions/{instid}/users")
public class PublicSaamfiUserManageControllerImpI {

	// Clase para manejar usuarios publicos, registro de usuarios
	@Autowired
	private SaamfiUserService userService;

	@PostMapping("/add")
	// @PreAuthorize("hasAnyRole('Admin-users','Create-users')")
	public ResponseEntity<?> registerUser(@RequestBody SaamfiNewuserSdtInDTO userDto, @PathVariable long instid) {
		try {

			String pass = ControllerAuthorizationValidator.getPasswordHash(userDto.getUserPassword());
			userDto.setUserPassword(pass);
			return ResponseEntity.ok(userService.createUser(instid, userDto));

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<?>> getAllUsersByIns(@PathVariable Long instid) {
		List<SaamfiUser> users = userService.findAllUsersByIns(instid);
		List<SaamfiUserSpeOutDTO> dtoUserList = UserMapper.INSTANCE.asListUsersSpecOutDTO(users);

		return new ResponseEntity<>(dtoUserList, HttpStatus.OK);
	}

	@GetMapping("/{userid}")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> getUserById(@PathVariable long instid, @PathVariable long userid)
			throws Exception {

		return new ResponseEntity<SaamfiNewuserSdtInDTO>(userService.getUserById(userid), HttpStatus.OK);

	}
}
