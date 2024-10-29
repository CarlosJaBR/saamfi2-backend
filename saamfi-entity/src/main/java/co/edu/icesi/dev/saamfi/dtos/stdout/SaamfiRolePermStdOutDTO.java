package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SaamfiRolePermStdOutDTO {

	private long roleRoleId;

	private String roleName;

	private long permPermId;

	private String permName;

	private long permissiontypeId;

	public long getPermissiontypeId() {
		return permissiontypeId;
	}

	public String getPermName() {
		return permName;
	}

	public long getPermPermId() {
		return permPermId;
	}

	public String getRoleName() {
		return roleName;
	}

	public long getRoleRoleId() {
		return roleRoleId;
	}

	public void setPermissiontypeId(long permissiontypeId) {
		this.permissiontypeId = permissiontypeId;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public void setPermPermId(long permPermId) {
		this.permPermId = permPermId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleRoleId(long roleRoleId) {
		this.roleRoleId = roleRoleId;
	}

}
