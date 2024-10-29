package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_SYSTM_USER database table.
 *
 */
@Entity
@Table(name = "SAAMFI_SYSTM_USER")
@NamedQuery(name = "SaamfiSystmUser.findAll", query = "SELECT s FROM SaamfiSystmUser s")
public class SaamfiSystmUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaamfiSystmUserPK id;

	private String dumy;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID", insertable = false, updatable = false)
	private SaamfiSystem saamfiSystem;

	// bi-directional many-to-one association to SaamfiUser
	@ManyToOne
	@JoinColumn(name = "USR_USER_ID", insertable = false, updatable = false)
	private SaamfiUser saamfiUser;

	public SaamfiSystmUser() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public SaamfiSystmUserPK getId() {
		return this.id;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public SaamfiUser getSaamfiUser() {
		return this.saamfiUser;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setId(SaamfiSystmUserPK id) {
		this.id = id;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

	public void setSaamfiUser(SaamfiUser saamfiUser) {
		this.saamfiUser = saamfiUser;
	}

}