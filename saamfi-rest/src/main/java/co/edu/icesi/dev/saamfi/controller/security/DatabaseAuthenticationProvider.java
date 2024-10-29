package co.edu.icesi.dev.saamfi.controller.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiInstitutionService;

/**
 * Class to handle UCCare own database authentication.
 *
 * @author Luis Miguel Paz Velasquez
 *
 */
@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

	/**
	 * User service interface that provides methods to access user data.
	 */
	private SaamfiUserService userService;

	/**
	 * @param userService the userService to set
	 */
	@Autowired
	public void setUserService(SaamfiUserService userService) {
		this.userService = userService;
	}

	/**
	 * Person Role interface that provides methods to access Person Role data
	 */
	private SaamfiUserRoleService personRoleService;

	/**
	 * @param personRoleService the personRoleService to set
	 */
	@Autowired
	public void setPersonRoleService(SaamfiUserRoleService personRoleService) {
		this.personRoleService = personRoleService;
	}

	private SaamfiInstitutionService institutionService;

	/**
	 * @param institutionService the institutionService to set
	 */
	@Autowired
	public void setInstitutionService(SaamfiInstitutionService institutionService) {
		this.institutionService = institutionService;
	}

	/**
	 * Implementation of the authenticate method from the interface
	 * AuthenticationProvider
	 *
	 * This method uses the userService to validate a given username and password,
	 * if the validation succed, it returns an UsernamePasswordAuthenticationToken.
	 * Otherwise, the method throws a BadCredentialsException
	 */
	@Override
	@SuppressWarnings("all")
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
		String param1 = auths.stream().filter(key -> key.getAuthority().contains("instid:")).findFirst().get()
				.toString();
		String param2 = auths.stream().filter(key -> key.getAuthority().contains("sysid:")).findFirst().get()
				.toString();

		long instid = Long.parseLong(param1.split(":")[1]);
		long sysId = Long.parseLong(param2.split(":")[1]);

		String credentials = authentication.getCredentials().toString().trim();
		String password = ControllerAuthorizationValidator
				.getPasswordHash(credentials);

		if (userService.validateUser(username, password, instid, sysId)
				|| authenticateLDAP(username, credentials, instid, sysId)) {
			Set<SimpleGrantedAuthority> roles = personRoleService.getUserRolesSystemInstitution(username, sysId,
					instid);
			if (roles == null) {
				roles = Collections.EMPTY_SET;
			}
			return new UsernamePasswordAuthenticationToken(username, password, roles);
		} else {
			throw new BadCredentialsException("External system authentication failed");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public boolean authenticateLDAP(String username, String password, long instId, long sysId) {

		boolean validate = userService.validateUser(username, instId, sysId);
		if (!validate) {
			return false;
		}
		SaamfiInstitution institution = institutionService.findById(instId);

		LdapContextSource contextSource = new LdapContextSource();

		contextSource.setUrl(institution.getInstLdapurl());
		contextSource.setBase(institution.getInstLdapbasedn());
		contextSource.setUserDn(institution.getInstLdapusername());
		contextSource.setPassword(institution.getInstLdappassword());
		contextSource.afterPropertiesSet();

		LdapTemplate template = new LdapTemplate(contextSource);
		try {
			boolean isValid = template.authenticate(institution.getInstLdapusersearchbase(),
					institution.getInstLdapusersearchfilter() + "=" + username, password);
			return isValid;

		} catch (Exception e) {
			return false;
		}
	}

}
