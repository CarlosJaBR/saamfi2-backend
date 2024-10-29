package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.UserSaamfiUserRoleController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;

import java.util.ArrayList;
import java.util.List;

//TODO implement and organize in appropriate controllers
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usr/institutions/{instid}/systems/{sysid}/users/{personid}/roles")
public class UserSaamfiUserRoleControllerImpl implements UserSaamfiUserRoleController {

	@Autowired
	private SaamfiUserRoleService userRoleService;

	@Override
	@GetMapping("/")
	public Iterable<SaamfiRole> getPersonRoles(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long personid) {
		// TODO revisar
		if (ControllerAuthorizationValidator.validateInstitutionAndSystemAndPerson(instid, sysid, personid)) {
			Iterable<SaamfiUserRole> saamfiUserRoles = userRoleService.getUserRolesAll(personid);
			List<SaamfiRole> saamfiRoles = new ArrayList<>();
			saamfiUserRoles.forEach(s -> saamfiRoles.add(s.getSaamfiRole()));
			return saamfiRoles;
		}
		return null;
	}

}
