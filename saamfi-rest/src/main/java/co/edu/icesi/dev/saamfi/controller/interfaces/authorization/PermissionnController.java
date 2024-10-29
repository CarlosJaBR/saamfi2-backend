package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public interface PermissionnController {

	public ResponseEntity<?> getPermissionsBySystem(long instid, long system);

	public ResponseEntity<?> savePermissionsBySystem(long instid, long system,
			SaamfiPermissionnStdOutDTO newPermission);

}
