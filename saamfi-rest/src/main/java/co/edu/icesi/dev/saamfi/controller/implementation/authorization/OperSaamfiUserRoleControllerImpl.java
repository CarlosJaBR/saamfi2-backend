package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.OperSaamfiUserRoleController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/oper/institutions/{instid}/systems/{sysid}/users/{userid}/roles")
public class OperSaamfiUserRoleControllerImpl implements OperSaamfiUserRoleController {

	@Autowired
	private SaamfiUserRoleService userRoleService;

	@Override
	@PostMapping("/{roleId}")
	@PreAuthorize("hasAnyRole('Admin-user-roles-oper')")
	public ResponseEntity<String> assignRoletoPerson(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long userid, @PathVariable long roleId) {
		boolean response = false;
		String message = "";
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			try {
				response = userRoleService.addRoleToUserOper(instid, sysid, userid, roleId);
			} catch (Exception e) {
				message = e.getMessage();
				if (message.equals("El rol ya se encuentra agregado a esta persona")) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
				} else if (message.equals("Verifique el documento al cual va a agregar el rol")) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
				}
			}
			if (response == true) {
				return new ResponseEntity<>("The Role has been correctly added to person", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("The Role has not been correctly added to person", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("You do not have access to make this request", HttpStatus.FORBIDDEN);
		}

	}

}
