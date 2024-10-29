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
 * The persistent class for the SAAMFI_ROLE database table.
 *
 */
@Entity
@Table(name = "SAAMFI_ROLE")
@NamedQuery(name = "SaamfiRole.findAll", query = "SELECT s FROM SaamfiRole s")
public class SaamfiRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SAAMFI_ROLE_ROLEID_GENERATOR", sequenceName = "SAAMFI_ROLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAAMFI_ROLE_ROLEID_GENERATOR")
	@Column(name = "ROLE_ID")
	private long roleId;

	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@Column(name = "ROLE_NAME")
	private String roleName;

	// bi-directional many-to-one association to SaamfiInstitution
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID")
	private SaamfiInstitution saamfiInstitution;

	// bi-directional many-to-one association to SaamfiSystem
	@ManyToOne
	@JoinColumn(name = "SYSTM_SYS_ID")
	private SaamfiSystem saamfiSystem;

	// bi-directional many-to-one association to SaamfiRolePerm
	@OneToMany(mappedBy = "saamfiRole")
	private List<SaamfiRolePerm> saamfiRolePerms;

	// bi-directional many-to-one association to SaamfiUserRole
	@OneToMany(mappedBy = "saamfiRole")
	private List<SaamfiUserRole> saamfiUserRoles;

	public SaamfiRole() {
	}

	public SaamfiRolePerm addSaamfiRolePerm(SaamfiRolePerm saamfiRolePerm) {
		getSaamfiRolePerms().add(saamfiRolePerm);
		saamfiRolePerm.setSaamfiRole(this);

		return saamfiRolePerm;
	}

	public SaamfiUserRole addSaamfiUserRole(SaamfiUserRole saamfiUserRole) {
		getSaamfiUserRoles().add(saamfiUserRole);
		saamfiUserRole.setSaamfiRole(this);

		return saamfiUserRole;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public long getRoleId() {
		return this.roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public SaamfiInstitution getSaamfiInstitution() {
		return this.saamfiInstitution;
	}

	public List<SaamfiRolePerm> getSaamfiRolePerms() {
		return this.saamfiRolePerms;
	}

	public SaamfiSystem getSaamfiSystem() {
		return this.saamfiSystem;
	}

	public List<SaamfiUserRole> getSaamfiUserRoles() {
		return this.saamfiUserRoles;
	}

	public SaamfiRolePerm removeSaamfiRolePerm(SaamfiRolePerm saamfiRolePerm) {
		getSaamfiRolePerms().remove(saamfiRolePerm);
		saamfiRolePerm.setSaamfiRole(null);

		return saamfiRolePerm;
	}

	public SaamfiUserRole removeSaamfiUserRole(SaamfiUserRole saamfiUserRole) {
		getSaamfiUserRoles().remove(saamfiUserRole);
		saamfiUserRole.setSaamfiRole(null);

		return saamfiUserRole;
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

	public void setSaamfiInstitution(SaamfiInstitution saamfiInstitution) {
		this.saamfiInstitution = saamfiInstitution;
	}

	public void setSaamfiRolePerms(List<SaamfiRolePerm> saamfiRolePerms) {
		this.saamfiRolePerms = saamfiRolePerms;
	}

	public void setSaamfiSystem(SaamfiSystem saamfiSystem) {
		this.saamfiSystem = saamfiSystem;
	}

	public void setSaamfiUserRoles(List<SaamfiUserRole> saamfiUserRoles) {
		this.saamfiUserRoles = saamfiUserRoles;
	}

}