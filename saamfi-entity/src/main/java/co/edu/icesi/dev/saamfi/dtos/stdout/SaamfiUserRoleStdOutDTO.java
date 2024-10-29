package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SaamfiUserRoleStdOutDTO {

	private long usrUserId;

	private long roleRoleId;

	private String roleName;

	private String roleDescription;

	public String getRoleDescription() {
		return roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public long getRoleRoleId() {
		return roleRoleId;
	}

	public long getUsrUserId() {
		return usrUserId;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleRoleId(long roleRoleId) {
		this.roleRoleId = roleRoleId;
	}

	public void setUsrUserId(long usrUserId) {
		this.usrUserId = usrUserId;
	}

}
