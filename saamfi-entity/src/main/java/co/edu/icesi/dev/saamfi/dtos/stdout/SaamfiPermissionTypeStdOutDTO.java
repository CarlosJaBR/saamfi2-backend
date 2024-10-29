package co.edu.icesi.dev.saamfi.dtos.stdout;

import java.io.Serializable;

public class SaamfiPermissionTypeStdOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private long permtypeId;

	private String permtypeDisplayname;

	private String permtypeIsactive;

	private String permtypeDoubleFactor;

	private String permtypeName;

	private Long permtypeIndex;

	private String permtypeType;

	private long sysId;

	public String getPermtypeDisplayname() {
		return permtypeDisplayname;
	}

	public String getPermtypeDoubleFactor() {
		return permtypeDoubleFactor;
	}

	public long getPermtypeId() {
		return permtypeId;
	}

	public Long getPermtypeIndex() {
		return permtypeIndex;
	}

	public String getPermtypeIsactive() {
		return permtypeIsactive;
	}

	public String getPermtypeName() {
		return permtypeName;
	}

	public String getPermtypeType() {
		return permtypeType;
	}

	public long getSysId() {
		return sysId;
	}

	public void setPermtypeDisplayname(String permtypeDisplayname) {
		this.permtypeDisplayname = permtypeDisplayname;
	}

	public void setPermtypeDoubleFactor(String permtypeDoubleFactor) {
		this.permtypeDoubleFactor = permtypeDoubleFactor;
	}

	public void setPermtypeId(long permtypeId) {
		this.permtypeId = permtypeId;
	}

	public void setPermtypeIndex(Long permtypeIndex) {
		this.permtypeIndex = permtypeIndex;
	}

	public void setPermtypeIsactive(String permtypeIsactive) {
		this.permtypeIsactive = permtypeIsactive;
	}

	public void setPermtypeName(String permtypeName) {
		this.permtypeName = permtypeName;
	}

	public void setPermtypeType(String permtypeType) {
		this.permtypeType = permtypeType;
	}

	public void setSysId(long sysId) {
		this.sysId = sysId;
	}

}