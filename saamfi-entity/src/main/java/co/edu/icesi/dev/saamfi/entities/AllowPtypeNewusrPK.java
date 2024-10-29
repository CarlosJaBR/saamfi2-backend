package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ALLOW_PTYPE_NEWUSR database table.
 * 
 */
@Embeddable
public class AllowPtypeNewusrPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "SYSTM_SYS_ID", insertable = false, updatable = false)
	private long systmSysId;

	@Column(name = "PERMTYPE_PERMTYPE_ID", insertable = false, updatable = false)
	private long permtypePermtypeId;

	public AllowPtypeNewusrPK() {
	}

	public long getSystmSysId() {
		return this.systmSysId;
	}

	public void setSystmSysId(long systmSysId) {
		this.systmSysId = systmSysId;
	}

	public long getPermtypePermtypeId() {
		return this.permtypePermtypeId;
	}

	public void setPermtypePermtypeId(long permtypePermtypeId) {
		this.permtypePermtypeId = permtypePermtypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AllowPtypeNewusrPK)) {
			return false;
		}
		AllowPtypeNewusrPK castOther = (AllowPtypeNewusrPK) other;
		return (this.systmSysId == castOther.systmSysId)
				&& (this.permtypePermtypeId == castOther.permtypePermtypeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.systmSysId ^ (this.systmSysId >>> 32)));
		hash = hash * prime + ((int) (this.permtypePermtypeId ^ (this.permtypePermtypeId >>> 32)));

		return hash;
	}
}