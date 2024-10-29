package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SaamfiRoleeStdOutDTO {

	private long roleId;

	private String roleDescription;

	private String roleName;

	private String system;

	private String inst;

	/**
	 * @return the system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * @return the inst
	 */
	public String getInst() {
		return inst;
	}

	/**
	 * @param inst the inst to set
	 */
	public void setInst(String inst) {
		this.inst = inst;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
