package co.edu.icesi.dev.saamfi.dtos.speout;

public class ParameterSpecOutDTO {
	
	private String paramName;

	private String paramType;

	private String paramValue;
	
	private long paramId;
	
	public ParameterSpecOutDTO() {
		
	}
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public long getParamId() {
		return paramId;
	}

	public void setParamId(long paramId) {
		this.paramId = paramId;
	}



}
