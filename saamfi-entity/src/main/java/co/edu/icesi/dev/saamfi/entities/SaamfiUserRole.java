package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_USER_ROLE database table.
 *
 */
@Entity
@Table(name = "SAAMFI_USER_ROLE")
@NamedQuery(name = "SaamfiUserRole.findAll", query = "SELECT s FROM SaamfiUserRole s")
public class SaamfiUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaamfiUserRolePK id;

	private String dumy;

	// bi-directional many-to-one association to SaamfiRole
	@ManyToOne
	@JoinColumn(name = "ROLE_ROLE_ID", insertable = false, updatable = false)
	private SaamfiRole saamfiRole;

	// bi-directional many-to-one association to SaamfiUser
	@ManyToOne
	@JoinColumn(name = "USR_USER_ID", insertable = false, updatable = false)
	private SaamfiUser saamfiUser;

	public SaamfiUserRole() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public SaamfiUserRolePK getId() {
		return this.id;
	}

	public SaamfiRole getSaamfiRole() {
		return this.saamfiRole;
	}

	public SaamfiUser getSaamfiUser() {
		return this.saamfiUser;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setId(SaamfiUserRolePK id) {
		this.id = id;
	}

	public void setSaamfiRole(SaamfiRole saamfiRole) {
		this.saamfiRole = saamfiRole;
	}

	public void setSaamfiUser(SaamfiUser saamfiUser) {
		this.saamfiUser = saamfiUser;
	}

}