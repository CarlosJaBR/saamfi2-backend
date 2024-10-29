package co.edu.icesi.dev.saamfi.dtos.intin;

public class BannerPersonIntInDTO {

	private String id;
	private String nombres;
	private String apellidos;
	private String identificacion;
	private String celular;
	private String correo;

	public String getApellidos() {
		return apellidos;
	}

	public String getCelular() {
		return celular;
	}

	public String getCorreo() {
		return correo;
	}

	public String getId() {
		return id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

}