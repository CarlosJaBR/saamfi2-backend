package co.edu.icesi.dev.saamfi.logic.implementation.authentication;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import co.edu.icesi.dev.saamfi.dtos.spein.ReCaptchaInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.ReCaptchaResponse;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.ReCaptchaService;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiParameterRepo;

@Service
public class ReCaptchaServiceImpl implements ReCaptchaService {

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private SaamfiParameterRepo autParameterRepo;

	public ReCaptchaServiceImpl(SaamfiParameterRepo autParameterRepo) {
		this.autParameterRepo = autParameterRepo;
	}

	/**
	 * @author Angie Cordoba, Alejandra Gonzalez
	 * @Method responsible of get from DB the reCaptcha public key.
	 * @Param: instid is the id of the institution to which the public key belongs
	 */
	@Override
	public String getPublicKey(long instid) {
		SaamfiParameter publicKey = autParameterRepo.findByParamNameAndSaamfiInstitutionInstId("PublicKeyCaptcha",
				instid);
		return publicKey.getParamValue();
	}

	/**
	 * @author Angie Cordoba, Alejandra Gonzalez
	 * @Method This method is in charge of make the request to google to determine
	 *         if a person is a robot or not .
	 * @Param: instid is the id of the institution to which the person who wants to
	 *         register belongs
	 * @Param: reCaptchaResponse is the information provided by google
	 */
	@Override
	public boolean validate(ReCaptchaInDTO reCaptchaResponse, long instid) {

		SaamfiParameter privateKey = autParameterRepo.findByParamNameAndSaamfiInstitutionInstId("PrivateKeyCaptcha",
				instid);

		SaamfiParameter url = autParameterRepo.findByParamNameAndSaamfiInstitutionInstId("CaptchaVerifyURL", instid);

		SaamfiParameter score = autParameterRepo.findByParamNameAndSaamfiInstitutionInstId("CaptchaScore", instid);

		URI verifyUri = URI.create(String.format(url.getParamValue(), privateKey.getParamValue(),
				reCaptchaResponse.getReCaptchaResponse(), request.getRemoteAddr()));

		try {
			ReCaptchaResponse response = restTemplate.getForObject(verifyUri, ReCaptchaResponse.class);

			if (response.isSuccess() && response.getScore() >= Double.parseDouble(score.getParamValue())) {
				return true;
			} else {
				return false;
			}

		} catch (Exception ignored) {
			// log.error("", ignored);
			// ignore when google services are not available
			// maybe add some sort of logging or trigger that'll alert the administrator

			System.out.println("ERROR google services are not available");
		}

		return false;
	}

}
