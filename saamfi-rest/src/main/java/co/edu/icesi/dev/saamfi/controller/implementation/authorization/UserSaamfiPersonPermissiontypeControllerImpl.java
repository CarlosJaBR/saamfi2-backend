package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.UserSaamfiPersonPermissiontypeController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionTypeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usr/institutions/{instid}/systems/{sysid}/users/{userid}/permissiontypes")
public class UserSaamfiPersonPermissiontypeControllerImpl implements UserSaamfiPersonPermissiontypeController {

	SaamfiPermissionTypeService permissiontypeService;

	public UserSaamfiPersonPermissiontypeControllerImpl(SaamfiPermissionTypeService personPermissiontypeService) {
		this.permissiontypeService = personPermissiontypeService;
	}

	@Override
	@GetMapping("/")
	public Iterable<SaamfiPermissionTypeStdOutDTO> getActivePermissiontypes(@PathVariable long instid,
			@PathVariable long sysid, @PathVariable long userid) {
		if (ControllerAuthorizationValidator.validateInstitutionAndSystemAndPerson(instid, sysid, userid)) {
			return permissiontypeService.getActivePermissiontype(userid);
		}
		return null;
	};

}
