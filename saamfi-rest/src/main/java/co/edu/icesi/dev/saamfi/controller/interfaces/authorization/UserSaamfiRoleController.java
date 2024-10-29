package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public interface UserSaamfiRoleController {

	public Iterable<SaamfiPermissionnStdOutDTO> getPermissionByType(Long permissionTypeId);

	public void getRolee(Long userrId);

}
