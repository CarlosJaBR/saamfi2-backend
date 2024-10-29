package co.edu.icesi.dev.saamfi.dtos.stdin;

public class SaamfiPermissionnStdInDTO {

	private String permName;

	private long permissiontypeId;

	public long getPermissiontypeId() {
		return permissiontypeId;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermissiontypeId(long permissiontypeId) {
		this.permissiontypeId = permissiontypeId;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

}