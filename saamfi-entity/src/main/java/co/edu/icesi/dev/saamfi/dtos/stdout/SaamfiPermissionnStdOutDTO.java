package co.edu.icesi.dev.saamfi.dtos.stdout;

public class SaamfiPermissionnStdOutDTO {

	private long permId;

	private String permDisplayname;

	private String permIsactive;

	private String permWebpath;

	private String permName;

	private String permTechnicaldescription;

	private String permUserdescription;

	private long permissiontypeId;

	private String permtypeDisplayname;

	public String getPermDisplayname() {
		return permDisplayname;
	}

	public long getPermId() {
		return permId;
	}

	public String getPermIsactive() {
		return permIsactive;
	}

	public long getPermissiontypeId() {
		return permissiontypeId;
	}

	public String getPermName() {
		return permName;
	}

	public String getPermTechnicaldescription() {
		return permTechnicaldescription;
	}

	public String getPermUserdescription() {
		return permUserdescription;
	}

	public String getPermWebpath() {
		return permWebpath;
	}

	public void setPermDisplayname(String permDisplayname) {
		this.permDisplayname = permDisplayname;
	}

	public void setPermId(long permId) {
		this.permId = permId;
	}

	public void setPermIsactive(String permIsactive) {
		this.permIsactive = permIsactive;
	}

	public void setPermissiontypeId(long permissiontypeId) {
		this.permissiontypeId = permissiontypeId;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public void setPermTechnicaldescription(String permTechnicaldescription) {
		this.permTechnicaldescription = permTechnicaldescription;
	}

	public void setPermUserdescription(String permUserdescription) {
		this.permUserdescription = permUserdescription;
	}

	public void setPermWebpath(String permWebpath) {
		this.permWebpath = permWebpath;
	}

	/**
	 * @return the permtypeDisplayname
	 */
	public String getPermtypeDisplayname() {
		return permtypeDisplayname;
	}

	/**
	 * @param permtypeDisplayname the permtypeDisplayname to set
	 */
	public void setPermtypeDisplayname(String permtypeDisplayname) {
		this.permtypeDisplayname = permtypeDisplayname;
	}

}