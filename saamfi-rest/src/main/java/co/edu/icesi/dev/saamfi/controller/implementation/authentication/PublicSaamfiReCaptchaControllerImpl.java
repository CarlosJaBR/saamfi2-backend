package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.PublicSaamfiReCaptchaController;
import co.edu.icesi.dev.saamfi.dtos.spein.ReCaptchaInDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.ReCaptchaService;

@RestController
@RequestMapping("/public/institutions/{instid}/recaptcha")
@CrossOrigin(origins = "*")
public class PublicSaamfiReCaptchaControllerImpl implements PublicSaamfiReCaptchaController {

	private ReCaptchaService reCaptchaService;

	public PublicSaamfiReCaptchaControllerImpl(ReCaptchaService reCaptchaService) {
		this.reCaptchaService = reCaptchaService;
	}

	/**
	 * <b>Name:</b> getPublicKey<br>
	 * This method is in charge of consulting the database for the reCaptcha public
	 * key <br>
	 * <b>pre:</b> The public key exists in the database <br>
	 *
	 * @param instid: Id of the institution to which the public key is asociated<br>
	 * @return
	 */
	@Override
	@GetMapping("/public-key")
	public String getPublicKey(@PathVariable long instid) {

		return reCaptchaService.getPublicKey(instid);
	}

	/**
	 * <b>Name:</b> IsValidate<br>
	 * This method is in charge of consult in google services if the person is a
	 * robot or not <br>
	 * <br>
	 *
	 * @param instid:            Id of the institution to which the person who wants
	 *                           to register belongs<br>
	 * @param reCaptchaResponse: DTO with the data of the reCaptcha response from
	 *                           google
	 * @return
	 */
	@Override
	@PostMapping("/")
	public boolean IsValidate(@PathVariable long instid, @RequestBody ReCaptchaInDTO reCaptchaResponse) {

		if (reCaptchaResponse.getReCaptchaResponse() == null || reCaptchaResponse.getReCaptchaResponse().isEmpty()) {
			return false;
		}
		return reCaptchaService.validate(reCaptchaResponse, instid);
	}

}
