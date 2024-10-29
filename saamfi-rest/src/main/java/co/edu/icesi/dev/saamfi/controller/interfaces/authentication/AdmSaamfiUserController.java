package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;

public interface AdmSaamfiUserController {

	public ResponseEntity<?> addUser(long instid, long sysid, SaamfiUserStInDTO userPerson) throws Exception;

	public void changePassword(long instid, long sysid, SaamfiUserPasswordSpeInDTO userPerson);

	ResponseEntity<?> DeleteUser(long instid, long sysid, long userId) throws Exception;

	ResponseEntity<?> getUserById(long instid, long sysid, long userid) throws Exception;

	ResponseEntity<?> getUsers(long instid, long sysid) throws Exception;

	public ResponseEntity<?> modifyUser(long instid, long sysid, long userId, SaamfiUserSpeInDTO userDto)
			throws Exception;

	public void uploadFile(@PathVariable long instid, @RequestParam MultipartFile file) throws Exception;

	ResponseEntity<?> modifyUserSuper(long instid, long sysid, long userId, SaamfiUserSpeInDTO userDto)
			throws Exception;

}
