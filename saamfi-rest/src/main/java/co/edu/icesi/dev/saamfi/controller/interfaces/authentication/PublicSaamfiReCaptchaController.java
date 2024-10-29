package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import co.edu.icesi.dev.saamfi.dtos.spein.ReCaptchaInDTO;

public interface PublicSaamfiReCaptchaController {

	String getPublicKey(long instid);

	boolean IsValidate(long instid, ReCaptchaInDTO reCaptchaResponse);

}
