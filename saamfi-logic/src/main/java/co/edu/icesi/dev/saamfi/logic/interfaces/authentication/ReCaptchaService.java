package co.edu.icesi.dev.saamfi.logic.interfaces.authentication;

import co.edu.icesi.dev.saamfi.dtos.spein.ReCaptchaInDTO;

public interface ReCaptchaService {

	public String getPublicKey(long instid);

	public boolean validate(ReCaptchaInDTO reCaptchaResponse, long instid);

}
