package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_ROLE_PERM database table.
 *
 */
@Entity
@Table(name = "SAAMFI_ROLE_PERM")
@NamedQuery(name = "SaamfiRolePerm.findAll", query = "SELECT s FROM SaamfiRolePerm s")
public class SaamfiRolePerm implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaamfiRolePermPK id;

	private String dumy;

	// bi-directional many-to-one association to SaamfiPermission
	@ManyToOne
	@JoinColumn(name = "PERM_PERM_ID", insertable = false, updatable = false)
	private SaamfiPermission saamfiPermission;

	// bi-directional many-to-one association to SaamfiRole
	@ManyToOne
	@JoinColumn(name = "ROLE_ROLE_ID", insertable = false, updatable = false)
	private SaamfiRole saamfiRole;

	public SaamfiRolePerm() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public SaamfiRolePermPK getId() {
		return this.id;
	}

	public SaamfiPermission getSaamfiPermission() {
		return this.saamfiPermission;
	}

	public SaamfiRole getSaamfiRole() {
		return this.saamfiRole;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setId(SaamfiRolePermPK id) {
		this.id = id;
	}

	public void setSaamfiPermission(SaamfiPermission saamfiPermission) {
		this.saamfiPermission = saamfiPermission;
	}

	public void setSaamfiRole(SaamfiRole saamfiRole) {
		this.saamfiRole = saamfiRole;
	}

}