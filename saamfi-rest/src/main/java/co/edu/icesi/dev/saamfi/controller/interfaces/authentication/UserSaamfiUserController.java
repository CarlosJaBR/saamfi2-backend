package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserProfileInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserProfileOutDTO;

public interface UserSaamfiUserController {

	ResponseEntity<SaamfiUserProfileOutDTO> getDataProfile(Long userId);

	ResponseEntity<SaamfiUserProfileOutDTO> modifyProfile(Long userId, SaamfiUserProfileInDTO profile);

}
