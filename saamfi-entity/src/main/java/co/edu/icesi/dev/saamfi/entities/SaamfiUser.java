package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SAAMFI_USER database table.
 *
 */
@Entity
@Table(name = "SAAMFI_USER")
@NamedQuery(name = "SaamfiUser.findAll", query = "SELECT s FROM SaamfiUser s")
public class SaamfiUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_USER_USERID_GENERATOR", sequenceName = "SAAMFI_USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_USER_USERID_GENERATOR")
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "USER_ACTIVE_DOUBLE_FACTOR")
	private String userActiveDoubleFactor;

	@Column(name = "USER_ACTIVE_FACTOR_EXP_DATIME")
	private Timestamp  userActiveFactorExpDatime;

	@Column(name = "USER_DOCUMENT_ID")
	private String userDocumentId;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "USER_EXT_ID")
	private String userExtId;

	@Column(name = "USER_IS_ACTIVE")
	private String userIsActive;

	@Column(name = "USER_IS_LOCKEDOUT")
	private String userIsLockedout;

	@Column(name = "USER_LASTNAME")
	private String userLastname;

	@Temporal(TemporalType.DATE)
	@Column(name = "USER_LOCKOUT_DATETIME")
	private Date userLockoutDatetime;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@Column(name = "USER_PHONE")
	private String userPhone;

	@Column(name = "USER_TYPE_ACTIVE_FACTOR")
	private String userTypeActiveFactor;

	@Column(name = "USER_USERNAME")
	private String userUsername;

	// bi-directional many-to-one association to SaamfiInstitution
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID")
	private SaamfiInstitution saamfiInstitution;

	// bi-directional many-to-one association to SaamfiUserRole
	@OneToMany(mappedBy = "saamfiUser")
	private List<SaamfiUserRole> saamfiUserRoles;

	// bi-directional many-to-one association to SaamfiSystmUser
	@OneToMany(mappedBy = "saamfiUser")
	private List<SaamfiSystmUser> saamfiSystmUsers;

	public SaamfiUser() {
	}

	public SaamfiSystmUser addSaamfiSystmUser(SaamfiSystmUser saamfiSystmUser) {
		getSaamfiSystmUsers().add(saamfiSystmUser);
		saamfiSystmUser.setSaamfiUser(this);

		return saamfiSystmUser;
	}

	public SaamfiUserRole addSaamfiUserRole(SaamfiUserRole saamfiUserRole) {
		getSaamfiUserRoles().add(saamfiUserRole);
		saamfiUserRole.setSaamfiUser(this);

		return saamfiUserRole;
	}

	public SaamfiInstitution getSaamfiInstitution() {
		return this.saamfiInstitution;
	}

	public List<SaamfiSystmUser> getSaamfiSystmUsers() {
		return this.saamfiSystmUsers;
	}

	public List<SaamfiUserRole> getSaamfiUserRoles() {
		return this.saamfiUserRoles;
	}

	public String getUserActiveDoubleFactor() {
		return this.userActiveDoubleFactor;
	}

	public Date getUserActiveFactorExpDatime() {
		return this.userActiveFactorExpDatime;
	}

	public String getUserDocumentId() {
		return this.userDocumentId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public String getUserExtId() {
		return this.userExtId;
	}

	public long getUserId() {
		return this.userId;
	}

	public String getUserIsActive() {
		return this.userIsActive;
	}

	public String getUserIsLockedout() {
		return this.userIsLockedout;
	}

	public String getUserLastname() {
		return this.userLastname;
	}

	public Date getUserLockoutDatetime() {
		return this.userLockoutDatetime;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public String getUserTypeActiveFactor() {
		return this.userTypeActiveFactor;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public SaamfiSystmUser removeSaamfiSystmUser(SaamfiSystmUser saamfiSystmUser) {
		getSaamfiSystmUsers().remove(saamfiSystmUser);
		saamfiSystmUser.setSaamfiUser(null);

		return saamfiSystmUser;
	}

	public SaamfiUserRole removeSaamfiUserRole(SaamfiUserRole saamfiUserRole) {
		getSaamfiUserRoles().remove(saamfiUserRole);
		saamfiUserRole.setSaamfiUser(null);

		return saamfiUserRole;
	}

	public void setSaamfiInstitution(SaamfiInstitution saamfiInstitution) {
		this.saamfiInstitution = saamfiInstitution;
	}

	public void setSaamfiSystmUsers(List<SaamfiSystmUser> saamfiSystmUsers) {
		this.saamfiSystmUsers = saamfiSystmUsers;
	}

	public void setSaamfiUserRoles(List<SaamfiUserRole> saamfiUserRoles) {
		this.saamfiUserRoles = saamfiUserRoles;
	}

	public void setUserActiveDoubleFactor(String userActiveDoubleFactor) {
		this.userActiveDoubleFactor = userActiveDoubleFactor;
	}

	public void setUserActiveFactorExpDatime(Timestamp userActiveFactorExpDatime) {
		this.userActiveFactorExpDatime = userActiveFactorExpDatime;
	}

	public void setUserDocumentId(String userDocumentId) {
		this.userDocumentId = userDocumentId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserExtId(String userExtId) {
		this.userExtId = userExtId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserIsActive(String userIsActive) {
		this.userIsActive = userIsActive;
	}

	public void setUserIsLockedout(String userIsLockedout) {
		this.userIsLockedout = userIsLockedout;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public void setUserLockoutDatetime(Date userLockoutDatetime) {
		this.userLockoutDatetime = userLockoutDatetime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public void setUserTypeActiveFactor(String userTypeActiveFactor) {
		this.userTypeActiveFactor = userTypeActiveFactor;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}