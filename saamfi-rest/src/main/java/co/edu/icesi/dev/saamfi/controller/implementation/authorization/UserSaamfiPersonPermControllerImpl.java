package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.UserSaamfiPersonPermController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usr/institutions/{instid}/systems/{sysid}/users/{userid}/permissions")
public class UserSaamfiPersonPermControllerImpl implements UserSaamfiPersonPermController {

	SaamfiUserRoleService personPermissionService;

	public UserSaamfiPersonPermControllerImpl(SaamfiUserRoleService personPermissionService) {
		this.personPermissionService = personPermissionService;
	}

	@Override
	@GetMapping("/")
	public Iterable<SaamfiPermissionnStdOutDTO> getActivePermissions(@PathVariable long instid,
			@PathVariable long sysid, @PathVariable long userid) {
		if (ControllerAuthorizationValidator.validateInstitutionAndSystemAndPerson(instid, sysid, userid)) {
			return personPermissionService.getActivePermissions(userid);
		}
		return null;
	};

}
