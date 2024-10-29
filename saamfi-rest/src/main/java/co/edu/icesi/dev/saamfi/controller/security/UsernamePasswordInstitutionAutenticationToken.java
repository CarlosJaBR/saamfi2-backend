package co.edu.icesi.dev.saamfi.controller.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernamePasswordInstitutionAutenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private long instId;
	private long sysId;

	public UsernamePasswordInstitutionAutenticationToken(Object principal, Object credentials, long instId,
			long sysid) {
		super(principal, credentials);
		this.instId = instId;
		this.sysId = sysid;
	}

	public long getInstId() {
		return instId;
	}

	public long getSysId() {
		return sysId;
	}

}
