package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SAAMFI_SYSTM_INST database table.
 * 
 */
@Embeddable
public class SaamfiSystmInstPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="INST_INST_ID", insertable=false, updatable=false)
	private long instInstId;

	@Column(name="SYSTM_SYS_ID", insertable=false, updatable=false)
	private long systmSysId;

	public SaamfiSystmInstPK() {
	}
	public long getInstInstId() {
		return this.instInstId;
	}
	public void setInstInstId(long instInstId) {
		this.instInstId = instInstId;
	}
	public long getSystmSysId() {
		return this.systmSysId;
	}
	public void setSystmSysId(long systmSysId) {
		this.systmSysId = systmSysId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SaamfiSystmInstPK)) {
			return false;
		}
		SaamfiSystmInstPK castOther = (SaamfiSystmInstPK)other;
		return 
			(this.instInstId == castOther.instInstId)
			&& (this.systmSysId == castOther.systmSysId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.instInstId ^ (this.instInstId >>> 32)));
		hash = hash * prime + ((int) (this.systmSysId ^ (this.systmSysId >>> 32)));
		
		return hash;
	}
}