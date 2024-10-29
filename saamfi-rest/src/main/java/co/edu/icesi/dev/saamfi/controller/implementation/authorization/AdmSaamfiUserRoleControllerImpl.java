package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.AdmSaamfiUserRoleController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/users/{userid}/roles")
public class AdmSaamfiUserRoleControllerImpl implements AdmSaamfiUserRoleController {

	@Autowired
	private SaamfiUserRoleService saamfiUserRoleService;

	@Override
	@PostMapping("/{roleId}")
	@PreAuthorize("hasAnyRole('Admin-person-roles-adm')")
	public ResponseEntity<String> assignRoletoUser(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long userid, @PathVariable long roleId) {
		boolean response = false;
		String message = "";
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			try {
				response = saamfiUserRoleService.addRoleToUserAdm(instid, sysid, userid, roleId);
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

	@Override
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('Admin-person-roles-adm')")
	public Iterable<SaamfiRole> getUserRoles(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long userid) {
		if (ControllerAuthorizationValidator.validateInstitutionAndSystemAndPerson(instid, sysid, userid)) {
			return null;
		}
		return null;
	}

}
