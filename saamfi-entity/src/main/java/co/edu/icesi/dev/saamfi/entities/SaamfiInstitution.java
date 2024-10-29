package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_INSTITUTION database table.
 *
 */
@Entity
@Table(name = "SAAMFI_INSTITUTION")
@NamedQuery(name = "SaamfiInstitution.findAll", query = "SELECT s FROM SaamfiInstitution s")
public class SaamfiInstitution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_INSTITUTION_INSTID_GENERATOR", sequenceName = "SAAMFI_INSTITUTION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_INSTITUTION_INSTID_GENERATOR")
	@Column(name = "INST_ID")
	private long instId;

	@Column(name = "INST_ACADEMICSERVERURL")
	private String instAcademicserverurl;

	@Column(name = "INST_ACADLOGINPASSWORD")
	private String instAcadloginpassword;

	@Column(name = "INST_ACADLOGINURL")
	private String instAcadloginurl;

	@Column(name = "INST_ACADLOGINUSERNAME")
	private String instAcadloginusername;

	@Column(name = "INST_ACADPERSONINFODOCURL")
	private String instAcadpersoninfodocurl;

	@Column(name = "INST_ACADPERSONINFOIDUR")
	private String instAcadpersoninfoidur;

	@Column(name = "INST_LDAPBASEDN")
	private String instLdapbasedn;

	@Column(name = "INST_LDAPPASSWORD")
	private String instLdappassword;

	@Column(name = "INST_LDAPURL")
	private String instLdapurl;

	@Column(name = "INST_LDAPUSERNAME")
	private String instLdapusername;

	@Column(name = "INST_LDAPUSERSEARCHBASE")
	private String instLdapusersearchbase;

	@Column(name = "INST_LDAPUSERSEARCHFILTER")
	private String instLdapusersearchfilter;

	@Column(name = "INST_NAME")
	private String instName;

	// bi-directional many-to-one association to SaamfiParameter
	@OneToMany(mappedBy = "saamfiInstitution")
	private List<SaamfiParameter> saamfiParameters;

	// bi-directional many-to-one association to SaamfiRole
	@OneToMany(mappedBy = "saamfiInstitution")
	private List<SaamfiRole> saamfiRoles;

	// bi-directional many-to-one association to SaamfiSystmInst
	@OneToMany(mappedBy = "saamfiInstitution")
	private List<SaamfiSystmInst> saamfiSystmInsts;

	// bi-directional many-to-one association to SaamfiUser
	@OneToMany(mappedBy = "saamfiInstitution")
	private List<SaamfiUser> saamfiUsers;

	public SaamfiInstitution() {
	}

	public SaamfiParameter addSaamfiParameter(SaamfiParameter saamfiParameter) {
		getSaamfiParameters().add(saamfiParameter);
		saamfiParameter.setSaamfiInstitution(this);

		return saamfiParameter;
	}

	public SaamfiRole addSaamfiRole(SaamfiRole saamfiRole) {
		getSaamfiRoles().add(saamfiRole);
		saamfiRole.setSaamfiInstitution(this);

		return saamfiRole;
	}

	public SaamfiSystmInst addSaamfiSystmInst(SaamfiSystmInst saamfiSystmInst) {
		getSaamfiSystmInsts().add(saamfiSystmInst);
		saamfiSystmInst.setSaamfiInstitution(this);

		return saamfiSystmInst;
	}

	public SaamfiUser addSaamfiUser(SaamfiUser saamfiUser) {
		getSaamfiUsers().add(saamfiUser);
		saamfiUser.setSaamfiInstitution(this);

		return saamfiUser;
	}

	public String getInstAcademicserverurl() {
		return this.instAcademicserverurl;
	}

	public String getInstAcadloginpassword() {
		return this.instAcadloginpassword;
	}

	public String getInstAcadloginurl() {
		return this.instAcadloginurl;
	}

	public String getInstAcadloginusername() {
		return this.instAcadloginusername;
	}

	public String getInstAcadpersoninfodocurl() {
		return this.instAcadpersoninfodocurl;
	}

	public String getInstAcadpersoninfoidur() {
		return this.instAcadpersoninfoidur;
	}

	public long getInstId() {
		return this.instId;
	}

	public String getInstLdapbasedn() {
		return this.instLdapbasedn;
	}

	public String getInstLdappassword() {
		return this.instLdappassword;
	}

	public String getInstLdapurl() {
		return this.instLdapurl;
	}

	public String getInstLdapusername() {
		return this.instLdapusername;
	}

	public String getInstLdapusersearchbase() {
		return this.instLdapusersearchbase;
	}

	public String getInstLdapusersearchfilter() {
		return this.instLdapusersearchfilter;
	}

	public String getInstName() {
		return this.instName;
	}

	public List<SaamfiParameter> getSaamfiParameters() {
		return this.saamfiParameters;
	}

	public List<SaamfiRole> getSaamfiRoles() {
		return this.saamfiRoles;
	}

	public List<SaamfiSystmInst> getSaamfiSystmInsts() {
		return this.saamfiSystmInsts;
	}

	public List<SaamfiUser> getSaamfiUsers() {
		return this.saamfiUsers;
	}

	public SaamfiParameter removeSaamfiParameter(SaamfiParameter saamfiParameter) {
		getSaamfiParameters().remove(saamfiParameter);
		saamfiParameter.setSaamfiInstitution(null);

		return saamfiParameter;
	}

	public SaamfiRole removeSaamfiRole(SaamfiRole saamfiRole) {
		getSaamfiRoles().remove(saamfiRole);
		saamfiRole.setSaamfiInstitution(null);

		return saamfiRole;
	}

	public SaamfiSystmInst removeSaamfiSystmInst(SaamfiSystmInst saamfiSystmInst) {
		getSaamfiSystmInsts().remove(saamfiSystmInst);
		saamfiSystmInst.setSaamfiInstitution(null);

		return saamfiSystmInst;
	}

	public SaamfiUser removeSaamfiUser(SaamfiUser saamfiUser) {
		getSaamfiUsers().remove(saamfiUser);
		saamfiUser.setSaamfiInstitution(null);

		return saamfiUser;
	}

	public void setInstAcademicserverurl(String instAcademicserverurl) {
		this.instAcademicserverurl = instAcademicserverurl;
	}

	public void setInstAcadloginpassword(String instAcadloginpassword) {
		this.instAcadloginpassword = instAcadloginpassword;
	}

	public void setInstAcadloginurl(String instAcadloginurl) {
		this.instAcadloginurl = instAcadloginurl;
	}

	public void setInstAcadloginusername(String instAcadloginusername) {
		this.instAcadloginusername = instAcadloginusername;
	}

	public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
		this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
	}

	public void setInstAcadpersoninfoidur(String instAcadpersoninfoidur) {
		this.instAcadpersoninfoidur = instAcadpersoninfoidur;
	}

	public void setInstId(long instId) {
		this.instId = instId;
	}

	public void setInstLdapbasedn(String instLdapbasedn) {
		this.instLdapbasedn = instLdapbasedn;
	}

	public void setInstLdappassword(String instLdappassword) {
		this.instLdappassword = instLdappassword;
	}

	public void setInstLdapurl(String instLdapurl) {
		this.instLdapurl = instLdapurl;
	}

	public void setInstLdapusername(String instLdapusername) {
		this.instLdapusername = instLdapusername;
	}

	public void setInstLdapusersearchbase(String instLdapusersearchbase) {
		this.instLdapusersearchbase = instLdapusersearchbase;
	}

	public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
		this.instLdapusersearchfilter = instLdapusersearchfilter;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public void setSaamfiParameters(List<SaamfiParameter> saamfiParameters) {
		this.saamfiParameters = saamfiParameters;
	}

	public void setSaamfiRoles(List<SaamfiRole> saamfiRoles) {
		this.saamfiRoles = saamfiRoles;
	}

	public void setSaamfiSystmInsts(List<SaamfiSystmInst> saamfiSystmInsts) {
		this.saamfiSystmInsts = saamfiSystmInsts;
	}

	public void setSaamfiUsers(List<SaamfiUser> saamfiUsers) {
		this.saamfiUsers = saamfiUsers;
	}

}