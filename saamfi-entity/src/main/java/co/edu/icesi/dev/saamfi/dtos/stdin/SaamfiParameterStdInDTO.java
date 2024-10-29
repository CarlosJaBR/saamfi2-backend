package co.edu.icesi.dev.saamfi.dtos.stdin;


public class SaamfiParameterStdInDTO {
	
	private String paramName;
	

	private String paramType;

	private String paramValue;
	


	public SaamfiParameterStdInDTO() {
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


}
