package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_SYSTM_INST database table.
 *
 */
@Entity
@Table(name = "SAAMFI_SYSTM_INST")
@NamedQuery(name = "SaamfiSystmInst.findAll", query = "SELECT s FROM SaamfiSystmInst s")
public class SaamfiSystmInst implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaamfiSystmInstPK id;

	private String dumy;

	// bi-directional many-to-one association to SaamfiInstitution
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID", insertable = false, updatable = false)
	private SaamfiInstitution saamfiInstitution;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID", insertable = false, updatable = false)
	private SaamfiSystem saamfiSystem;

	public SaamfiSystmInst() {
	}

	public String getDumy() {
		return this.dumy;
	}

	public SaamfiSystmInstPK getId() {
		return this.id;
	}

	public SaamfiInstitution getSaamfiInstitution() {
		return this.saamfiInstitution;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public void setDumy(String dumy) {
		this.dumy = dumy;
	}

	public void setId(SaamfiSystmInstPK id) {
		this.id = id;
	}

	public void setSaamfiInstitution(SaamfiInstitution saamfiInstitution) {
		this.saamfiInstitution = saamfiInstitution;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

}