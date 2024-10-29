package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_SYSTEM database table.
 *
 */
@Entity
@Table(name = "SAAMFI_SYSTEM")
@NamedQuery(name = "SaamfiSystem.findAll", query = "SELECT s FROM SaamfiSystem s")
public class SaamfiSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_SYSTEM_SYSID_GENERATOR", sequenceName = "SAAMFI_SYSTEM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_SYSTEM_SYSID_GENERATOR")
	@Column(name = "SYS_ID")
	private long sysId;

	@Column(name = "SYS_ALLOW_ALL_INST_USERS_LOGIN")
	private String sysAllowAllInstUsersLogin;

	@Column(name = "SYS_ALLOW_PUBLIC_USER_CREATE")
	private String sysAllowPublicUserCreate;

	@Column(name = "SYS_ALLOW_ROLE_USER_CREATE")
	private String sysAllowRoleUserCreate;

	@Column(name = "SYS_ALLOWED_AUTH")
	private String sysAllowedAuth;

	@Column(name = "SYS_DEFAULT_LDAP_ROLES")
	private String sysDefaultLdapRoles;

	@Column(name = "SYS_DEFAULT_LOCAL_ROLES")
	private String sysDefaultLocalRoles;

	@Column(name = "SYS_DOUBLE_FACTOR_LOGIN")
	private String sysDoubleFactorLogin;

	@Column(name = "SYS_DOUBLE_FACTOR_SPECIAL")
	private String sysDoubleFactorSpecial;

	@Column(name = "SYS_IS_ACTIVE")
	private String sysIsActive;

	@Column(name = "SYS_LOGIN_TOKEN_TIMEOUT")
	private Long sysLoginTokenTimeout;

	@Column(name = "SYS_NAME")
	private String sysName;

	@Column(name = "SYS_NORMAL_TOKEN_TIMEOUT")
	private Long sysNormalTokenTimeout;

	@Column(name = "SYS_REMOTE_CREATE_PERSON_URL")
	private String sysRemoteCreatePersonUrl;

	@Column(name = "SYS_REMOTE_LANDING_PAGE")
	private String sysRemoteLandingPage;

	@Column(name = "SYS_REMOTE_QUERY_PERSON_ATRIB")
	private String sysRemoteQueryPersonAtrib;

	@Column(name = "SYS_REMOTE_QUERY_PERSON_URL")
	private String sysRemoteQueryPersonUrl;

	@Column(name = "SYS_SPECIAL_TOKEN_TIMEOUT")
	private Long sysSpecialTokenTimeout;

	// bi-directional many-to-one association to AllowPtypeNewusr
	@OneToMany(mappedBy = "saamfiSystem")
	private List<AllowPtypeNewusr> allowPtypeNewusrs;

	// bi-directional many-to-one association to SaamfiLoginAttempt
	@OneToMany(mappedBy = "saamfiSystem")
	private List<SaamfiLoginAttempt> saamfiLoginAttempts;

	// bi-directional many-to-one association to SaamfiPermissionType
	@OneToMany(mappedBy = "saamfiSystem")
	private List<SaamfiPermissionType> saamfiPermissionTypes;

	// bi-directional many-to-one association to SaamfiRole
	@OneToMany(mappedBy = "saamfiSystem")
	private List<SaamfiRole> saamfiRoles;

	// bi-directional many-to-many association to SaamfiInstitution
	@ManyToMany
	@JoinTable(name = "SAAMFI_SYSTM_INST", joinColumns = { @JoinColumn(name = "SYSTM_SYS_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "INST_INST_ID") })
	private List<SaamfiInstitution> saamfiInstitutions;

	// bi-directional many-to-one association to SaamfiSystmInst
	@OneToMany(mappedBy = "saamfiSystem")
	private List<SaamfiSystmInst> saamfiSystmInsts;

	// bi-directional many-to-one association to SaamfiSystmUser
	@OneToMany(mappedBy = "saamfiSystem")
	private List<SaamfiSystmUser> saamfiSystmUsers;

	public SaamfiSystem() {
	}

	public AllowPtypeNewusr addAllowPtypeNewusr(AllowPtypeNewusr allowPtypeNewusr) {
		getAllowPtypeNewusrs().add(allowPtypeNewusr);
		allowPtypeNewusr.setSaamfiSystem(this);

		return allowPtypeNewusr;
	}

	public SaamfiLoginAttempt addSaamfiLoginAttempt(SaamfiLoginAttempt saamfiLoginAttempt) {
		getSaamfiLoginAttempts().add(saamfiLoginAttempt);
		saamfiLoginAttempt.setSaamfiSystem(this);

		return saamfiLoginAttempt;
	}

	public SaamfiPermissionType addSaamfiPermissionType(SaamfiPermissionType saamfiPermissionType) {
		getSaamfiPermissionTypes().add(saamfiPermissionType);
		saamfiPermissionType.setSaamfiSystem(this);

		return saamfiPermissionType;
	}

	public SaamfiRole addSaamfiRole(SaamfiRole saamfiRole) {
		getSaamfiRoles().add(saamfiRole);
		saamfiRole.setSaamfiSystem(this);

		return saamfiRole;
	}

	public SaamfiSystmInst addSaamfiSystmInst(SaamfiSystmInst saamfiSystmInst) {
		getSaamfiSystmInsts().add(saamfiSystmInst);
		saamfiSystmInst.setSaamfiSystem(this);

		return saamfiSystmInst;
	}

	public SaamfiSystmUser addSaamfiSystmUser(SaamfiSystmUser saamfiSystmUser) {
		getSaamfiSystmUsers().add(saamfiSystmUser);
		saamfiSystmUser.setSaamfiSystem(this);

		return saamfiSystmUser;
	}

	public List<AllowPtypeNewusr> getAllowPtypeNewusrs() {
		return this.allowPtypeNewusrs;
	}

	public List<SaamfiInstitution> getSaamfiInstitutions() {
		return this.saamfiInstitutions;
	}

	public List<SaamfiLoginAttempt> getSaamfiLoginAttempts() {
		return this.saamfiLoginAttempts;
	}

	public List<SaamfiPermissionType> getSaamfiPermissionTypes() {
		return this.saamfiPermissionTypes;
	}

	public List<SaamfiRole> getSaamfiRoles() {
		return this.saamfiRoles;
	}

	public List<SaamfiSystmInst> getSaamfiSystmInsts() {
		return this.saamfiSystmInsts;
	}

	public List<SaamfiSystmUser> getSaamfiSystmUsers() {
		return this.saamfiSystmUsers;
	}

	public String getSysAllowAllInstUsersLogin() {
		return this.sysAllowAllInstUsersLogin;
	}

	public String getSysAllowedAuth() {
		return this.sysAllowedAuth;
	}

	public String getSysAllowPublicUserCreate() {
		return this.sysAllowPublicUserCreate;
	}

	public String getSysAllowRoleUserCreate() {
		return this.sysAllowRoleUserCreate;
	}

	public String getSysDefaultLdapRoles() {
		return this.sysDefaultLdapRoles;
	}

	public String getSysDefaultLocalRoles() {
		return this.sysDefaultLocalRoles;
	}

	public String getSysDoubleFactorLogin() {
		return this.sysDoubleFactorLogin;
	}

	public String getSysDoubleFactorSpecial() {
		return this.sysDoubleFactorSpecial;
	}

	public long getSysId() {
		return this.sysId;
	}

	public String getSysIsActive() {
		return this.sysIsActive;
	}

	public Long getSysLoginTokenTimeout() {
		return this.sysLoginTokenTimeout;
	}

	public String getSysName() {
		return this.sysName;
	}

	public Long getSysNormalTokenTimeout() {
		return this.sysNormalTokenTimeout;
	}

	public String getSysRemoteCreatePersonUrl() {
		return this.sysRemoteCreatePersonUrl;
	}

	public String getSysRemoteLandingPage() {
		return this.sysRemoteLandingPage;
	}

	public String getSysRemoteQueryPersonAtrib() {
		return this.sysRemoteQueryPersonAtrib;
	}

	public String getSysRemoteQueryPersonUrl() {
		return this.sysRemoteQueryPersonUrl;
	}

	public Long getSysSpecialTokenTimeout() {
		return this.sysSpecialTokenTimeout;
	}

	public AllowPtypeNewusr removeAllowPtypeNewusr(AllowPtypeNewusr allowPtypeNewusr) {
		getAllowPtypeNewusrs().remove(allowPtypeNewusr);
		allowPtypeNewusr.setSaamfiSystem(null);

		return allowPtypeNewusr;
	}

	public SaamfiLoginAttempt removeSaamfiLoginAttempt(SaamfiLoginAttempt saamfiLoginAttempt) {
		getSaamfiLoginAttempts().remove(saamfiLoginAttempt);
		saamfiLoginAttempt.setSaamfiSystem(null);

		return saamfiLoginAttempt;
	}

	public SaamfiPermissionType removeSaamfiPermissionType(SaamfiPermissionType saamfiPermissionType) {
		getSaamfiPermissionTypes().remove(saamfiPermissionType);
		saamfiPermissionType.setSaamfiSystem(null);

		return saamfiPermissionType;
	}

	public SaamfiRole removeSaamfiRole(SaamfiRole saamfiRole) {
		getSaamfiRoles().remove(saamfiRole);
		saamfiRole.setSaamfiSystem(null);

		return saamfiRole;
	}

	public SaamfiSystmInst removeSaamfiSystmInst(SaamfiSystmInst saamfiSystmInst) {
		getSaamfiSystmInsts().remove(saamfiSystmInst);
		saamfiSystmInst.setSaamfiSystem(null);

		return saamfiSystmInst;
	}

	public SaamfiSystmUser removeSaamfiSystmUser(SaamfiSystmUser saamfiSystmUser) {
		getSaamfiSystmUsers().remove(saamfiSystmUser);
		saamfiSystmUser.setSaamfiSystem(null);

		return saamfiSystmUser;
	}

	public void setAllowPtypeNewusrs(List<AllowPtypeNewusr> allowPtypeNewusrs) {
		this.allowPtypeNewusrs = allowPtypeNewusrs;
	}

	public void setSaamfiInstitutions(List<SaamfiInstitution> saamfiInstitutions) {
		this.saamfiInstitutions = saamfiInstitutions;
	}

	public void setSaamfiLoginAttempts(List<SaamfiLoginAttempt> saamfiLoginAttempts) {
		this.saamfiLoginAttempts = saamfiLoginAttempts;
	}

	public void setSaamfiPermissionTypes(List<SaamfiPermissionType> saamfiPermissionTypes) {
		this.saamfiPermissionTypes = saamfiPermissionTypes;
	}

	public void setSaamfiRoles(List<SaamfiRole> saamfiRoles) {
		this.saamfiRoles = saamfiRoles;
	}

	public void setSaamfiSystmInsts(List<SaamfiSystmInst> saamfiSystmInsts) {
		this.saamfiSystmInsts = saamfiSystmInsts;
	}

	public void setSaamfiSystmUsers(List<SaamfiSystmUser> saamfiSystmUsers) {
		this.saamfiSystmUsers = saamfiSystmUsers;
	}

	public void setSysAllowAllInstUsersLogin(String sysAllowAllInstUsersLogin) {
		this.sysAllowAllInstUsersLogin = sysAllowAllInstUsersLogin;
	}

	public void setSysAllowedAuth(String sysAllowedAuth) {
		this.sysAllowedAuth = sysAllowedAuth;
	}

	public void setSysAllowPublicUserCreate(String sysAllowPublicUserCreate) {
		this.sysAllowPublicUserCreate = sysAllowPublicUserCreate;
	}

	public void setSysAllowRoleUserCreate(String sysAllowRoleUserCreate) {
		this.sysAllowRoleUserCreate = sysAllowRoleUserCreate;
	}

	public void setSysDefaultLdapRoles(String sysDefaultLdapRoles) {
		this.sysDefaultLdapRoles = sysDefaultLdapRoles;
	}

	public void setSysDefaultLocalRoles(String sysDefaultLocalRoles) {
		this.sysDefaultLocalRoles = sysDefaultLocalRoles;
	}

	public void setSysDoubleFactorLogin(String sysDoubleFactorLogin) {
		this.sysDoubleFactorLogin = sysDoubleFactorLogin;
	}

	public void setSysDoubleFactorSpecial(String sysDoubleFactorSpecial) {
		this.sysDoubleFactorSpecial = sysDoubleFactorSpecial;
	}

	public void setSysId(long sysId) {
		this.sysId = sysId;
	}

	public void setSysIsActive(String sysIsActive) {
		this.sysIsActive = sysIsActive;
	}

	public void setSysLoginTokenTimeout(Long sysLoginTokenTimeout) {
		this.sysLoginTokenTimeout = sysLoginTokenTimeout;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public void setSysNormalTokenTimeout(Long sysNormalTokenTimeout) {
		this.sysNormalTokenTimeout = sysNormalTokenTimeout;
	}

	public void setSysRemoteCreatePersonUrl(String sysRemoteCreatePersonUrl) {
		this.sysRemoteCreatePersonUrl = sysRemoteCreatePersonUrl;
	}

	public void setSysRemoteLandingPage(String sysRemoteLandingPage) {
		this.sysRemoteLandingPage = sysRemoteLandingPage;
	}

	public void setSysRemoteQueryPersonAtrib(String sysRemoteQueryPersonAtrib) {
		this.sysRemoteQueryPersonAtrib = sysRemoteQueryPersonAtrib;
	}

	public void setSysRemoteQueryPersonUrl(String sysRemoteQueryPersonUrl) {
		this.sysRemoteQueryPersonUrl = sysRemoteQueryPersonUrl;
	}

	public void setSysSpecialTokenTimeout(Long sysSpecialTokenTimeout) {
		this.sysSpecialTokenTimeout = sysSpecialTokenTimeout;
	}

}