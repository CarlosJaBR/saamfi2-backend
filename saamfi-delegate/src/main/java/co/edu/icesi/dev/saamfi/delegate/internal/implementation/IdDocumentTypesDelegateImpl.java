package co.edu.icesi.dev.saamfi.delegate.internal.implementation;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.saamfi.delegate.internal.interfaces.IdDocumentTypesDelegate;
import co.edu.icesi.dev.saamfi.delegate.utils.HttpUtils;
import co.edu.icesi.dev.saamfi.dtos.stdout.IdDocumentTypeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.IdDocumentTypesOutDTO;

@Component
public class IdDocumentTypesDelegateImpl implements IdDocumentTypesDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonDelegateImpl.class);

	private static String PUBLICINSTITUTIONPATH = "/users/public/institutions/";

	RestTemplate restTemplate;

	@Value("${proxybalancer.url}")
	private String proxyBalancerUrl;

	@Value("${proxybalancer.path}")
	private String proxybalancerPath;

	public IdDocumentTypesDelegateImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Iterable<IdDocumentTypeOutDTO> getTdDocumentTypes(long instid) {
		String url = proxyBalancerUrl + proxybalancerPath + PUBLICINSTITUTIONPATH + instid + "/documenttypes/internal";
		Iterable<IdDocumentTypeOutDTO> callmeResponse = null;
		Map<String, String> headers = new HashMap<>();
		ResponseEntity<IdDocumentTypesOutDTO> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(HttpUtils.createHeaders(headers)), IdDocumentTypesOutDTO.class, callmeResponse);
		callmeResponse = response.getBody().getIdDocumentTypesDTO();
		LOGGER.info("Response: {}", callmeResponse);
		return callmeResponse;
	}

}
