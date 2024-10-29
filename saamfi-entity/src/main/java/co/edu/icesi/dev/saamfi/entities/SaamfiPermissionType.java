package co.edu.icesi.dev.saamfi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the SAAMFI_PERMISSION_TYPE database table.
 *
 */
@Entity
@Table(name = "SAAMFI_PERMISSION_TYPE")
@NamedQuery(name = "SaamfiPermissionType.findAll", query = "SELECT s FROM SaamfiPermissionType s")
public class SaamfiPermissionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_PERMISSION_TYPE_PERMTYPEID_GENERATOR", sequenceName = "SAAMFI_PERMISSION_TYPE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_PERMISSION_TYPE_PERMTYPEID_GENERATOR")
	@Column(name = "PERMTYPE_ID")
	private long permtypeId;

	@Column(name = "PERMTYPE_DISPLAYNAME")
	private String permtypeDisplayname;

	@Column(name = "PERMTYPE_DOUBLE_FACTOR")
	private String permtypeDoubleFactor;

	@Column(name = "PERMTYPE_INDEX")
	private Long permtypeIndex;

	@Column(name = "PERMTYPE_ISACTIVE")
	private String permtypeIsactive;

	@Column(name = "PERMTYPE_NAME")
	private String permtypeName;

	@Column(name = "PERMTYPE_TYPE")
	private String permtypeType;

	// bi-directional many-to-one association to AllowPtypeNewusr
	@OneToMany(mappedBy = "saamfiPermissionType")
	private List<AllowPtypeNewusr> allowPtypeNewusrs;

	// bi-directional many-to-one association to SaamfiPermission
	@OneToMany(mappedBy = "saamfiPermissionType")
	private List<SaamfiPermission> saamfiPermissions;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID")
	private SaamfiSystem saamfiSystem;

	public SaamfiPermissionType() {
	}

	public AllowPtypeNewusr addAllowPtypeNewusr(AllowPtypeNewusr allowPtypeNewusr) {
		getAllowPtypeNewusrs().add(allowPtypeNewusr);
		allowPtypeNewusr.setSaamfiPermissionType(this);

		return allowPtypeNewusr;
	}

	public SaamfiPermission addSaamfiPermission(SaamfiPermission saamfiPermission) {
		getSaamfiPermissions().add(saamfiPermission);
		saamfiPermission.setSaamfiPermissionType(this);

		return saamfiPermission;
	}

	public List<AllowPtypeNewusr> getAllowPtypeNewusrs() {
		return this.allowPtypeNewusrs;
	}

	public String getPermtypeDisplayname() {
		return this.permtypeDisplayname;
	}

	public String getPermtypeDoubleFactor() {
		return this.permtypeDoubleFactor;
	}

	public long getPermtypeId() {
		return this.permtypeId;
	}

	public Long getPermtypeIndex() {
		return this.permtypeIndex;
	}

	public String getPermtypeIsactive() {
		return this.permtypeIsactive;
	}

	public String getPermtypeName() {
		return this.permtypeName;
	}

	public String getPermtypeType() {
		return this.permtypeType;
	}

	public List<SaamfiPermission> getSaamfiPermissions() {
		return this.saamfiPermissions;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public AllowPtypeNewusr removeAllowPtypeNewusr(AllowPtypeNewusr allowPtypeNewusr) {
		getAllowPtypeNewusrs().remove(allowPtypeNewusr);
		allowPtypeNewusr.setSaamfiPermissionType(null);

		return allowPtypeNewusr;
	}

	public SaamfiPermission removeSaamfiPermission(SaamfiPermission saamfiPermission) {
		getSaamfiPermissions().remove(saamfiPermission);
		saamfiPermission.setSaamfiPermissionType(null);

		return saamfiPermission;
	}

	public void setAllowPtypeNewusrs(List<AllowPtypeNewusr> allowPtypeNewusrs) {
		this.allowPtypeNewusrs = allowPtypeNewusrs;
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

	public void setSaamfiPermissions(List<SaamfiPermission> saamfiPermissions) {
		this.saamfiPermissions = saamfiPermissions;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

}