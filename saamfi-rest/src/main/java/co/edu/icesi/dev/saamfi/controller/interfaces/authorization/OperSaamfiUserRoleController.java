package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;

public interface OperSaamfiUserRoleController {

	public ResponseEntity<String> assignRoletoPerson(long instId, long sysId, long persDocument, long roleId);

}
