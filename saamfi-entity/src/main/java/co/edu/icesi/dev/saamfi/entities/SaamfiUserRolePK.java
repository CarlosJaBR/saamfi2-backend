package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SAAMFI_USER_ROLE database table.
 * 
 */
@Embeddable
public class SaamfiUserRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USR_USER_ID", insertable=false, updatable=false)
	private long usrUserId;

	@Column(name="ROLE_ROLE_ID", insertable=false, updatable=false)
	private long roleRoleId;

	public SaamfiUserRolePK() {
	}
	public long getUsrUserId() {
		return this.usrUserId;
	}
	public void setUsrUserId(long usrUserId) {
		this.usrUserId = usrUserId;
	}
	public long getRoleRoleId() {
		return this.roleRoleId;
	}
	public void setRoleRoleId(long roleRoleId) {
		this.roleRoleId = roleRoleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SaamfiUserRolePK)) {
			return false;
		}
		SaamfiUserRolePK castOther = (SaamfiUserRolePK)other;
		return 
			(this.usrUserId == castOther.usrUserId)
			&& (this.roleRoleId == castOther.roleRoleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.usrUserId ^ (this.usrUserId >>> 32)));
		hash = hash * prime + ((int) (this.roleRoleId ^ (this.roleRoleId >>> 32)));
		
		return hash;
	}
}