package co.edu.icesi.dev.saamfi.delegate.internal.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.saamfi.delegate.internal.interfaces.PersonDelegate;
import co.edu.icesi.dev.saamfi.delegate.utils.HttpUtils;
import co.edu.icesi.dev.saamfi.dtos.intin.BannerPersonIntInDTO;
import co.edu.icesi.dev.saamfi.dtos.intin.SystemPersonStdInDTO;

@Component
public class PersonDelegateImpl implements PersonDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonDelegateImpl.class);

	private static String INTERNALINSTITUTIONPATH = "/users/internal/institutions/";

	RestTemplate restTemplate;

	@Value("${proxybalancer.url}")
	private String proxyBalancerUrl;

	@Value("${proxybalancer.path}")
	private String proxybalancerPath;

	public PersonDelegateImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/*
	 * @Override public long createPerson(long instId, PersonSpeInDTO visitor) {
	 * String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH +
	 * instId + "/persons/"; long callmeResponse = restTemplate.postForObject(url,
	 * visitor, Long.class); LOGGER.info("Response: {}", callmeResponse); return
	 * callmeResponse; }
	 */

	/*
	 * @Override public void deletePerson(long instid, long persid) {
	 * 
	 * String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH +
	 * instid + "/persons/" + persid; restTemplate.delete(url);
	 * 
	 * }
	 */
	@Override
	public boolean existPersonById(long instId, long persId) {
		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instId + "/persons/person/"
				+ persId;
		boolean callmeResponse = restTemplate.getForObject(url, Boolean.class);
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;
	}

	@Override
	public BannerPersonIntInDTO getPersonByDocument(long instid, String personDocument) {
		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instid
				+ "/persons/filters?persIddocument=" + personDocument;
		BannerPersonIntInDTO callmeResponse = restTemplate.getForObject(url, BannerPersonIntInDTO.class);
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;
	}

	@Override
	// TODO usar el llamado internal
	public BannerPersonIntInDTO getPersonById(long instid, long personid, String token) {
		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instid + "/persons/" + personid;
		BannerPersonIntInDTO callmeResponse = null;
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpUtils.AUTHORIZATION_HEADER, HttpUtils.getAuthorizationValue(token));

		ResponseEntity<BannerPersonIntInDTO> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(HttpUtils.createHeaders(headers)), BannerPersonIntInDTO.class, callmeResponse);
		callmeResponse = response.getBody();

		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;
	}

	@Override
	public SystemPersonStdInDTO getPersonByIdInternal(long instid, long personid) {
		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instid + "/persons/internal/"
				+ personid;
		SystemPersonStdInDTO callmeResponse = restTemplate.getForObject(url, SystemPersonStdInDTO.class);
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;
	}

	@Override
	public List<SystemPersonStdInDTO> getPersons(long instid) {

		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instid + "/persons/";
		List<SystemPersonStdInDTO> callmeResponse = restTemplate.getForObject(url, List.class);
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;

	}

	@Override
	public SystemPersonStdInDTO getPersonsByUserId(long persid, long instid) {

		String url = proxyBalancerUrl + proxybalancerPath + INTERNALINSTITUTIONPATH + instid + "/persons/" + persid;
		SystemPersonStdInDTO callmeResponse = restTemplate.getForObject(url, SystemPersonStdInDTO.class);
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;

	}

	/*
	 * @Override public void modifyPerson(long instid, long persid, PersonSpeInDTO2
	 * userPerson) { String url = proxyBalancerUrl + proxybalancerPath +
	 * INTERNALINSTITUTIONPATH + instid + "/persons/edit/" + persid;
	 *
	 * restTemplate.put(url, userPerson); }
	 *
	 * @Override public String sendEmailRecovey(long instid, PersonEmailStdInDTO
	 * userPerson) { String url = proxyBalancerUrl + proxybalancerPath +
	 * INTERNALINSTITUTIONPATH + instid + "/persons/account-email"; String response
	 * = restTemplate.postForObject(url, userPerson, String.class); return response;
	 *
	 * }
	 */
}