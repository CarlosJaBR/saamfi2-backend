package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SAAMFI_ROLE_PERM database table.
 * 
 */
@Embeddable
public class SaamfiRolePermPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ROLE_ID", insertable=false, updatable=false)
	private long roleRoleId;

	@Column(name="PERM_PERM_ID", insertable=false, updatable=false)
	private long permPermId;

	public SaamfiRolePermPK() {
	}
	public long getRoleRoleId() {
		return this.roleRoleId;
	}
	public void setRoleRoleId(long roleRoleId) {
		this.roleRoleId = roleRoleId;
	}
	public long getPermPermId() {
		return this.permPermId;
	}
	public void setPermPermId(long permPermId) {
		this.permPermId = permPermId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SaamfiRolePermPK)) {
			return false;
		}
		SaamfiRolePermPK castOther = (SaamfiRolePermPK)other;
		return 
			(this.roleRoleId == castOther.roleRoleId)
			&& (this.permPermId == castOther.permPermId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleRoleId ^ (this.roleRoleId >>> 32)));
		hash = hash * prime + ((int) (this.permPermId ^ (this.permPermId >>> 32)));
		
		return hash;
	}
}