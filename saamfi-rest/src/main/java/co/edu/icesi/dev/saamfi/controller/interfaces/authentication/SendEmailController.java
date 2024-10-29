package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserChangePassSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserCodeDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserEmailStdInDTO;

public interface SendEmailController {

	ResponseEntity<SaamfiUserPasswordSpeInDTO> changePassword(SaamfiUserChangePassSpeInDTO user);

	ResponseEntity<String> generateCode(long instid, SaamfiUserEmailStdInDTO user);

	ResponseEntity<Boolean> validateCode(SaamfiUserCodeDTO user);

}
