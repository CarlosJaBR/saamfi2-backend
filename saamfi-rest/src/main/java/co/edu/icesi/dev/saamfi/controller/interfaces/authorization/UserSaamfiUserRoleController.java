package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.dev.saamfi.entities.SaamfiRole;

public interface UserSaamfiUserRoleController {

	public Iterable<SaamfiRole> getPersonRoles(@PathVariable long instid, @PathVariable long sysid, long personId);

}
