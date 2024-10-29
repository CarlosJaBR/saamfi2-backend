package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SAAMFI_LOGIN_ATTEMPT database table.
 *
 */
@Entity
@Table(name = "SAAMFI_LOGIN_ATTEMPT")
@NamedQuery(name = "SaamfiLoginAttempt.findAll", query = "SELECT s FROM SaamfiLoginAttempt s")
public class SaamfiLoginAttempt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_LOGIN_ATTEMPT_LOGATID_GENERATOR", sequenceName = "SAAMFI_LOGIN_ATTEMPT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_LOGIN_ATTEMPT_LOGATID_GENERATOR")
	@Column(name = "LOGAT_ID")
	private long logatId;

	@Temporal(TemporalType.DATE)
	@Column(name = "LOGAT_DATETIME")
	private Date logatDatetime;

	@Column(name = "LOGAT_RESPONSE")
	private String logatResponse;

	@Column(name = "LOGAT_USERNAME")
	private String logatUsername;

	@Column(name = "SYS_ID")
	private long sysId;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID")
	private SaamfiSystem saamfiSystem;

	public SaamfiLoginAttempt() {
	}

	public Date getLogatDatetime() {
		return this.logatDatetime;
	}

	public long getLogatId() {
		return this.logatId;
	}

	public String getLogatResponse() {
		return this.logatResponse;
	}

	public String getLogatUsername() {
		return this.logatUsername;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public long getSysId() {
		return this.sysId;
	}

	public void setLogatDatetime(Date logatDatetime) {
		this.logatDatetime = logatDatetime;
	}

	public void setLogatId(long logatId) {
		this.logatId = logatId;
	}

	public void setLogatResponse(String logatResponse) {
		this.logatResponse = logatResponse;
	}

	public void setLogatUsername(String logatUsername) {
		this.logatUsername = logatUsername;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

	public void setSysId(long sysId) {
		this.sysId = sysId;
	}

}