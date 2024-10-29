package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;

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

/**
 * The persistent class for the SAAMFI_PARAMETER database table.
 *
 */
@Entity
@Table(name = "SAAMFI_PARAMETER")
@NamedQuery(name = "SaamfiParameter.findAll", query = "SELECT s FROM SaamfiParameter s")
public class SaamfiParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_PARAMETER_PARAMID_GENERATOR", sequenceName = "SAAMFI_PARAMETER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_PARAMETER_PARAMID_GENERATOR")
	@Column(name = "PARAM_ID")
	private long paramId;

	@Column(name = "PARAM_NAME")
	private String paramName;

	@Column(name = "PARAM_TYPE")
	private String paramType;

	@Column(name = "PARAM_VALUE")
	private String paramValue;

	// bi-directional many-to-one association to SaamfiInstitution
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID")
	private SaamfiInstitution saamfiInstitution;

	public SaamfiParameter() {
	}

	public long getParamId() {
		return this.paramId;
	}

	public String getParamName() {
		return this.paramName;
	}

	public String getParamType() {
		return this.paramType;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public SaamfiInstitution getSaamfiInstitution() {
		return this.saamfiInstitution;
	}

	public void setParamId(long paramId) {
		this.paramId = paramId;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public void setSaamfiInstitution(SaamfiInstitution saamfiInstitution) {
		this.saamfiInstitution = saamfiInstitution;
	}

}