package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.dev.saamfi.entities.SaamfiRole;

public interface AdmSaamfiUserRoleController {

	public ResponseEntity<String> assignRoletoUser(long instId, long sysId, long userId, long roleId);

	public Iterable<SaamfiRole> getUserRoles(@PathVariable long instid, long sysId, long userId);

}
