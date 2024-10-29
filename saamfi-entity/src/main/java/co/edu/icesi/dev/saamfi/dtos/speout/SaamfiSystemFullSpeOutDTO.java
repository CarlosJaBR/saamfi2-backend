package co.edu.icesi.dev.saamfi.dtos.speout;

import java.util.List;

public class SaamfiSystemFullSpeOutDTO {
	
	private Long sysId;
	private String sysName;
	
	private String sysRemoteCreatePersonUrl;
	private String sysRemoteQueryPersonUrl;
	private String sysRemoteLandingPage;
	
	private String sysAllowedAuth;
	
	private Long sysLoginTokenTimeout;
	private Long sysNormalTokenTimeout;
	private Long sysSpecialTokenTimeout;
	
	private String sysAllowAllInstUsersLogin;
	private String sysAllowPublicUserCreate;
	private String sysAllowRoleUserCreate;
	
	private String sysDoubleFactorLogin;
	private String sysDoubleFactorSpecial;

	private String sysDefaultLdapRoles;
	private String sysDefaultLocalRoles;
	
	private String sysRemoteQueryPersonAtrib;

	private String sysIsActive;
	
	private List<Long> sysInstitutions;

	public Long getSysId() {
		return sysId;
	}

	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysRemoteCreatePersonUrl() {
		return sysRemoteCreatePersonUrl;
	}

	public void setSysRemoteCreatePersonUrl(String sysRemoteCreatePersonUrl) {
		this.sysRemoteCreatePersonUrl = sysRemoteCreatePersonUrl;
	}

	public String getSysRemoteQueryPersonUrl() {
		return sysRemoteQueryPersonUrl;
	}

	public void setSysRemoteQueryPersonUrl(String sysRemoteQueryPersonUrl) {
		this.sysRemoteQueryPersonUrl = sysRemoteQueryPersonUrl;
	}

	public String getSysRemoteLandingPage() {
		return sysRemoteLandingPage;
	}

	public void setSysRemoteLandingPage(String sysRemoteLandingPage) {
		this.sysRemoteLandingPage = sysRemoteLandingPage;
	}

	public String getSysAllowedAuth() {
		return sysAllowedAuth;
	}

	public void setSysAllowedAuth(String sysAllowedAuth) {
		this.sysAllowedAuth = sysAllowedAuth;
	}

	public Long getSysLoginTokenTimeout() {
		return sysLoginTokenTimeout;
	}

	public void setSysLoginTokenTimeout(Long sysLoginTokenTimeout) {
		this.sysLoginTokenTimeout = sysLoginTokenTimeout;
	}

	public Long getSysNormalTokenTimeout() {
		return sysNormalTokenTimeout;
	}

	public void setSysNormalTokenTimeout(Long sysNormalTokenTimeout) {
		this.sysNormalTokenTimeout = sysNormalTokenTimeout;
	}

	public Long getSysSpecialTokenTimeout() {
		return sysSpecialTokenTimeout;
	}

	public void setSysSpecialTokenTimeout(Long sysSpecialTokenTimeout) {
		this.sysSpecialTokenTimeout = sysSpecialTokenTimeout;
	}

	public String getSysAllowAllInstUsersLogin() {
		return sysAllowAllInstUsersLogin;
	}

	public void setSysAllowAllInstUsersLogin(String sysAllowAllInstUsersLogin) {
		this.sysAllowAllInstUsersLogin = sysAllowAllInstUsersLogin;
	}

	public String getSysAllowPublicUserCreate() {
		return sysAllowPublicUserCreate;
	}

	public void setSysAllowPublicUserCreate(String sysAllowPublicUserCreate) {
		this.sysAllowPublicUserCreate = sysAllowPublicUserCreate;
	}

	public String getSysAllowRoleUserCreate() {
		return sysAllowRoleUserCreate;
	}

	public void setSysAllowRoleUserCreate(String sysAllowRoleUserCreate) {
		this.sysAllowRoleUserCreate = sysAllowRoleUserCreate;
	}

	public String getSysDoubleFactorLogin() {
		return sysDoubleFactorLogin;
	}

	public void setSysDoubleFactorLogin(String sysDoubleFactorLogin) {
		this.sysDoubleFactorLogin = sysDoubleFactorLogin;
	}

	public String getSysDoubleFactorSpecial() {
		return sysDoubleFactorSpecial;
	}

	public void setSysDoubleFactorSpecial(String sysDoubleFactorSpecial) {
		this.sysDoubleFactorSpecial = sysDoubleFactorSpecial;
	}

	public String getSysDefaultLdapRoles() {
		return sysDefaultLdapRoles;
	}

	public void setSysDefaultLdapRoles(String sysDefaultLdapRoles) {
		this.sysDefaultLdapRoles = sysDefaultLdapRoles;
	}

	public String getSysDefaultLocalRoles() {
		return sysDefaultLocalRoles;
	}

	public void setSysDefaultLocalRoles(String sysDefaultLocalRoles) {
		this.sysDefaultLocalRoles = sysDefaultLocalRoles;
	}

	public String getSysRemoteQueryPersonAtrib() {
		return sysRemoteQueryPersonAtrib;
	}

	public void setSysRemoteQueryPersonAtrib(String sysRemoteQueryPersonAtrib) {
		this.sysRemoteQueryPersonAtrib = sysRemoteQueryPersonAtrib;
	}

	public String getSysIsActive() {
		return sysIsActive;
	}

	public void setSysIsActive(String sysIsActive) {
		this.sysIsActive = sysIsActive;
	}

	public List<Long> getSysInstitutions() {
		return sysInstitutions;
	}

	public void setSysInstitutions(List<Long> sysInstitutions) {
		this.sysInstitutions = sysInstitutions;
	}
}
