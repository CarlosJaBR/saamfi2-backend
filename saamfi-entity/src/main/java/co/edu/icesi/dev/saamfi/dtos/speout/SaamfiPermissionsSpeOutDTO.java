package co.edu.icesi.dev.saamfi.dtos.speout;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public class SaamfiPermissionsSpeOutDTO {
	
	private List<SaamfiPermissionnStdOutDTO> permissions;

	public List<SaamfiPermissionnStdOutDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SaamfiPermissionnStdOutDTO> permissions) {
		this.permissions = permissions;
	}
}
