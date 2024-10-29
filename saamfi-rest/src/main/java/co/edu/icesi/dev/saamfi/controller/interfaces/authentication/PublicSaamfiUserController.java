package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiLoginRequestInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;

public interface PublicSaamfiUserController {

	// public void createUserr(long instid, UserrPersonStInDTO userPerson);

	/**
	 * <b>Name:</b> createUserr<br>
	 * This method is in charge of create a new user in the database and call the
	 * delegate for create a person <br>
	 * Modified: Kliver Daniel Giron <b>pre:</b> The userName and persIddocument
	 * doesn't exists in the database <br>
	 *
	 * @param instid:     Id of the institution to which the person and will be
	 *                    associated<br>
	 * @param userPerson: DTO with the data of the new person and user
	 * @return
	 */

	public ResponseEntity<?> authenticateUser(SaamfiLoginRequestInDTO loginRequest, long instid, long sysid)
			throws Exception;

	public ResponseEntity<?> createUser(long instid, long sysid, SaamfiUserStInDTO userPerson);

	public ResponseEntity<?> validateUser(SaamfiLoginRequestInDTO loginRequest, long instid, long sysid);

	ResponseEntity<?> modifyUser(long instid, long sysid, long userId, SaamfiUserStInDTO userDto) throws Exception;

	ResponseEntity<?> getUserById(long instid, long sysid, long userid) throws Exception;

	ResponseEntity<List<?>> getAllUsersByInstAndSys(Long instid, Long sysid);

}
