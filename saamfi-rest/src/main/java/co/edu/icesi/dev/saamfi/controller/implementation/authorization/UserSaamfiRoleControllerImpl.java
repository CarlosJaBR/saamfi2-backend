package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.UserSaamfiRoleController;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

//TODO implement and organize in appropriate controllers
//@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usr/institutions/{instid}/systems/{sysid}/roles")
public class UserSaamfiRoleControllerImpl implements UserSaamfiRoleController {

	@Override
	@GetMapping("/permissiontypes/{permissionTypeId}/permissions/")
	public Iterable<SaamfiPermissionnStdOutDTO> getPermissionByType(@PathVariable Long permissionTypeId) {
		return null;
	};

	@Override
	public void getRolee(Long userrId) {

	};

}
