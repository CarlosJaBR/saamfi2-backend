package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.SendEmailController;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserChangePassSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserCodeDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserEmailStdInDTO;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.ExpirationCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.implementation.authentication.NoPasswordsEqualException;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;

@RestController
@RequestMapping("/public/institutions/{instid}/recovery-password")
@CrossOrigin(origins = "*")
public class SendEmailControllerImp implements SendEmailController {

	private SaamfiUserService userService;

	@Autowired
	public SendEmailControllerImp(SaamfiUserService userService) {
		this.userService = userService;
	}

	@Override
	@PutMapping("/change-password")
	public ResponseEntity<SaamfiUserPasswordSpeInDTO> changePassword(@RequestBody SaamfiUserChangePassSpeInDTO user) {
		SaamfiUserPasswordSpeInDTO userDTO;
		try {
			userDTO = userService.changePasswordRecovery(user);
		} catch (NoResultException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (NoPasswordsEqualException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Override
	@PostMapping("/generateCode")
	public ResponseEntity<String> generateCode(@PathVariable("instid") long instid,
			@RequestBody SaamfiUserEmailStdInDTO user) {
		try {
			userService.accountRecoveryGenerateCode(instid, user);
		} catch (NoResultException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (IOException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (MessagingException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Enviado correctamente", HttpStatus.OK);
	}

	@Override
	@PostMapping("/validateCode")
	public ResponseEntity<Boolean> validateCode(@RequestBody SaamfiUserCodeDTO user) {
		Boolean validate;
		try {
			validate = userService.accountValidateGenerateCode(user);
		} catch (ExpirationCodeException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (BadCodeException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(validate, HttpStatus.OK);
	}

}
