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
 * The persistent class for the SAAMFI_PERMISSION database table.
 *
 */
@Entity
@Table(name = "SAAMFI_PERMISSION")
@NamedQuery(name = "SaamfiPermission.findAll", query = "SELECT s FROM SaamfiPermission s")
public class SaamfiPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_PERMISSION_PERMID_GENERATOR", sequenceName = "SAAMFI_PERMISSION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_PERMISSION_PERMID_GENERATOR")
	@Column(name = "PERM_ID")
	private long permId;

	@Column(name = "PERM_DISPLAYNAME")
	private String permDisplayname;

	@Column(name = "PERM_INDEX")
	private Long permIndex;

	@Column(name = "PERM_ISACTIVE")
	private String permIsactive;

	@Column(name = "PERM_NAME")
	private String permName;

	@Column(name = "PERM_TECHNICALDESCRIPTION")
	private String permTechnicaldescription;

	@Column(name = "PERM_USERDESCRIPTION")
	private String permUserdescription;

	@Column(name = "PERM_WEBPATH")
	private String permWebpath;

	@Column(name = "PERMTYPE_DOUBLE_FACTOR")
	private String permtypeDoubleFactor;

	// bi-directional many-to-one association to SaamfiPermissionType
	@ManyToOne
	@JoinColumn(name = "PERMTYPE_PERMTYPE_ID")
	private SaamfiPermissionType saamfiPermissionType;

	// bi-directional many-to-one association to SaamfiRolePerm
	@OneToMany(mappedBy = "saamfiPermission")
	private List<SaamfiRolePerm> saamfiRolePerms;

	public SaamfiPermission() {
	}

	public SaamfiRolePerm addSaamfiRolePerm(SaamfiRolePerm saamfiRolePerm) {
		getSaamfiRolePerms().add(saamfiRolePerm);
		saamfiRolePerm.setSaamfiPermission(this);

		return saamfiRolePerm;
	}

	public String getPermDisplayname() {
		return this.permDisplayname;
	}

	public long getPermId() {
		return this.permId;
	}

	public Long getPermIndex() {
		return this.permIndex;
	}

	public String getPermIsactive() {
		return this.permIsactive;
	}

	public String getPermName() {
		return this.permName;
	}

	public String getPermTechnicaldescription() {
		return this.permTechnicaldescription;
	}

	public String getPermtypeDoubleFactor() {
		return this.permtypeDoubleFactor;
	}

	public String getPermUserdescription() {
		return this.permUserdescription;
	}

	public String getPermWebpath() {
		return this.permWebpath;
	}

	public SaamfiPermissionType getSaamfiPermissionType() {
		return this.saamfiPermissionType;
	}

	public List<SaamfiRolePerm> getSaamfiRolePerms() {
		return this.saamfiRolePerms;
	}

	public SaamfiRolePerm removeSaamfiRolePerm(SaamfiRolePerm saamfiRolePerm) {
		getSaamfiRolePerms().remove(saamfiRolePerm);
		saamfiRolePerm.setSaamfiPermission(null);

		return saamfiRolePerm;
	}

	public void setPermDisplayname(String permDisplayname) {
		this.permDisplayname = permDisplayname;
	}

	public void setPermId(long permId) {
		this.permId = permId;
	}

	public void setPermIndex(Long permIndex) {
		this.permIndex = permIndex;
	}

	public void setPermIsactive(String permIsactive) {
		this.permIsactive = permIsactive;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public void setPermTechnicaldescription(String permTechnicaldescription) {
		this.permTechnicaldescription = permTechnicaldescription;
	}

	public void setPermtypeDoubleFactor(String permtypeDoubleFactor) {
		this.permtypeDoubleFactor = permtypeDoubleFactor;
	}

	public void setPermUserdescription(String permUserdescription) {
		this.permUserdescription = permUserdescription;
	}

	public void setPermWebpath(String permWebpath) {
		this.permWebpath = permWebpath;
	}

	public void setSaamfiPermissionType(SaamfiPermissionType saamfiPermissionType) {
		this.saamfiPermissionType = saamfiPermissionType;
	}

	public void setSaamfiRolePerms(List<SaamfiRolePerm> saamfiRolePerms) {
		this.saamfiRolePerms = saamfiRolePerms;
	}

}