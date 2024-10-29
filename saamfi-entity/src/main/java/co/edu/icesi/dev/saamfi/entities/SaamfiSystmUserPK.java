package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SAAMFI_SYSTM_USER database table.
 * 
 */
@Embeddable
public class SaamfiSystmUserPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SYSTM_SYS_ID", insertable=false, updatable=false)
	private long systmSysId;

	@Column(name="USR_USER_ID", insertable=false, updatable=false)
	private long usrUserId;

	public SaamfiSystmUserPK() {
	}
	public long getSystmSysId() {
		return this.systmSysId;
	}
	public void setSystmSysId(long systmSysId) {
		this.systmSysId = systmSysId;
	}
	public long getUsrUserId() {
		return this.usrUserId;
	}
	public void setUsrUserId(long usrUserId) {
		this.usrUserId = usrUserId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SaamfiSystmUserPK)) {
			return false;
		}
		SaamfiSystmUserPK castOther = (SaamfiSystmUserPK)other;
		return 
			(this.systmSysId == castOther.systmSysId)
			&& (this.usrUserId == castOther.usrUserId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.systmSysId ^ (this.systmSysId >>> 32)));
		hash = hash * prime + ((int) (this.usrUserId ^ (this.usrUserId >>> 32)));
		
		return hash;
	}
}