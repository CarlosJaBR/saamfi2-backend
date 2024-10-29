package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the ALLOW_PTYPE_NEWUSR database table.
 *
 */
@Entity
@Table(name = "ALLOW_PTYPE_NEWUSR")
@NamedQuery(name = "AllowPtypeNewusr.findAll", query = "SELECT a FROM AllowPtypeNewusr a")
public class AllowPtypeNewusr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AllowPtypeNewusrPK id;

	private String dumy;

	// bi-directional many-to-one association to SaamfiPermissionType
	@ManyToOne
	@JoinColumn(name = "PERMTYPE_PERMTYPE_ID", insertable = false, updatable = false)
	private SaamfiPermissionType saamfiPermissionType;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID", insertable = false, updatable = false)
	private SaamfiSystem saamfiSystem;

	public AllowPtypeNewusr() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public AllowPtypeNewusrPK getId() {
		return this.id;
	}

	public SaamfiPermissionType getSaamfiPermissionType() {
		return this.saamfiPermissionType;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setId(AllowPtypeNewusrPK id) {
		this.id = id;
	}

	public void setSaamfiPermissionType(SaamfiPermissionType saamfiPermissionType) {
		this.saamfiPermissionType = saamfiPermissionType;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

}