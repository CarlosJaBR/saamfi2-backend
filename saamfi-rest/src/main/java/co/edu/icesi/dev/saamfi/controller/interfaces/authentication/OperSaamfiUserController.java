package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;

public interface OperSaamfiUserController {

	public ResponseEntity<?> createUser(long instid, long sysid, SaamfiUserStInDTO userPerson);
}
