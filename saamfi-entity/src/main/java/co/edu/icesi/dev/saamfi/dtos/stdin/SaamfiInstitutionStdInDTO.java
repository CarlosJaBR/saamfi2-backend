package co.edu.icesi.dev.saamfi.dtos.stdin;

import java.util.List;

import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;

public class SaamfiInstitutionStdInDTO {
	
	private String instAcademicserverurl;

	private String instAcadloginpassword;

	private String instAcadloginurl;

	private String instAcadloginusername;

	private String instAcadpersoninfodocurl;

	private String instAcadpersoninfoidur;

	private String instLdapbasedn;

	private String instLdappassword;

	private String instLdapurl;

	private String instLdapusername;

	private String instLdapusersearchbase;

	private String instLdapusersearchfilter;

	private String instName;
	
	private List<Long> systemsId;
	
	private List<Long> rolesId;
	
	private List<SaamfiParameter> parameters;
	
	
	public SaamfiInstitutionStdInDTO() {
		
	}
	public String getInstAcademicserverurl() {
		return instAcademicserverurl;
	}

	public void setInstAcademicserverurl(String instAcademicserverurl) {
		this.instAcademicserverurl = instAcademicserverurl;
	}

	public String getInstAcadloginpassword() {
		return instAcadloginpassword;
	}

	public void setInstAcadloginpassword(String instAcadloginpassword) {
		this.instAcadloginpassword = instAcadloginpassword;
	}

	public String getInstAcadloginurl() {
		return instAcadloginurl;
	}

	public void setInstAcadloginurl(String instAcadloginurl) {
		this.instAcadloginurl = instAcadloginurl;
	}

	public String getInstAcadloginusername() {
		return instAcadloginusername;
	}

	public void setInstAcadloginusername(String instAcadloginusername) {
		this.instAcadloginusername = instAcadloginusername;
	}

	public String getInstAcadpersoninfodocurl() {
		return instAcadpersoninfodocurl;
	}

	public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
		this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
	}

	public String getInstAcadpersoninfoidur() {
		return instAcadpersoninfoidur;
	}

	public void setInstAcadpersoninfoidur(String instAcadpersoninfoidur) {
		this.instAcadpersoninfoidur = instAcadpersoninfoidur;
	}

	public String getInstLdapbasedn() {
		return instLdapbasedn;
	}

	public void setInstLdapbasedn(String instLdapbasedn) {
		this.instLdapbasedn = instLdapbasedn;
	}

	public String getInstLdappassword() {
		return instLdappassword;
	}

	public void setInstLdappassword(String instLdappassword) {
		this.instLdappassword = instLdappassword;
	}

	public String getInstLdapurl() {
		return instLdapurl;
	}

	public void setInstLdapurl(String instLdapurl) {
		this.instLdapurl = instLdapurl;
	}

	public String getInstLdapusername() {
		return instLdapusername;
	}

	public void setInstLdapusername(String instLdapusername) {
		this.instLdapusername = instLdapusername;
	}

	public String getInstLdapusersearchbase() {
		return instLdapusersearchbase;
	}

	public void setInstLdapusersearchbase(String instLdapusersearchbase) {
		this.instLdapusersearchbase = instLdapusersearchbase;
	}

	public String getInstLdapusersearchfilter() {
		return instLdapusersearchfilter;
	}

	public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
		this.instLdapusersearchfilter = instLdapusersearchfilter;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}
	
	public List<Long> getSystemsId() {
		return systemsId;
	}
	
	public List<Long> getRolesId() {
		return rolesId;
	}
	private SaamfiInstitution setParamsToSaamfiInstitution() {
		SaamfiInstitution institution=new SaamfiInstitution();
		institution.setInstName(instName);
		institution.setInstAcademicserverurl(instAcademicserverurl != null ? instAcademicserverurl : "");
		institution.setInstAcadloginpassword(instAcadloginpassword != null ? instAcadloginpassword : "");
		institution.setInstAcadloginurl(instAcadloginurl != null ? instAcadloginurl : "");
		institution.setInstAcadloginusername(instAcadloginusername != null ? instAcadloginusername : "");
		institution.setInstAcadpersoninfodocurl(instAcadpersoninfodocurl != null ? instAcadpersoninfodocurl : "");
		institution.setInstAcadpersoninfoidur(instAcadpersoninfoidur != null ? instAcadpersoninfoidur : "");
		institution.setInstLdapbasedn(instLdapbasedn != null ? instLdapbasedn : "");
		institution.setInstLdappassword(instLdappassword != null ? instLdappassword : "");
		institution.setInstLdapurl(instLdapurl != null ? instLdapurl : "");
		institution.setInstLdapusername(instLdapusername != null ? instLdapusername : "");
		institution.setInstLdapusersearchbase(instLdapusersearchbase != null ? instLdapusersearchbase : "");
		institution.setInstLdapusersearchfilter(instLdapusersearchfilter != null ? instLdapusersearchfilter : "");
		return institution;

	}
	public SaamfiInstitution getParamsToSaamfiInstitution() {
		SaamfiInstitution institution=setParamsToSaamfiInstitution();
		return institution;
	}
	
	public List<SaamfiParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<SaamfiParameter> parameters) {
		this.parameters = parameters;
	}
}
