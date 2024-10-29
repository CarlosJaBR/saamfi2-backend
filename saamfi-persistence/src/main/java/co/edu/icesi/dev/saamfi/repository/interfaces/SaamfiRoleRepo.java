package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiRole;

public interface SaamfiRoleRepo extends CrudRepository<SaamfiRole, Long> {

	List<SaamfiRole> findAll();

	public SaamfiRole findByRoleName(String roleName);

	public SaamfiRole findByRoleNameAndRoleDescription(String roleName, String rolDesc);

	List<SaamfiRole> findBySaamfiSystemSysId(Long sysid);

	List<SaamfiRole> findBySaamfiInstitutionInstId(Long instId);

	// @Query(value = "SELECT * from Saamfi_role r where INST_INST_ID=?1 AND
	// ROLE_NAME=?2", nativeQuery = true)
	List<SaamfiRole> getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(long instId, long sysId,
			String roleName);

	@Query(value = "Select r.* FROM Saamfi_role r, Saamfi_role_permission rp WHERE rp.perm_perm_id = ?1 AND rp.role_role_id = r.role_id", nativeQuery = true)
	List<SaamfiRole> getRolesByPermissionId(long permissionId);

	@Query(value = "Select r.* FROM Saamfi_role r, Saamfi_role_perm rp WHERE rp.perm_perm_id = ?2 AND rp.role_role_id = r.role_id AND r.inst_inst_id = ?1", nativeQuery = true)
	List<SaamfiRole> getRolesByInstiAndPermissionId(long instId, long permissionId);

	@Query(value = "SELECT DISTINCT r.* FROM Saamfi_role r WHERE 1 NOT IN ( select per.permtype_permtype_id from saamfi_permission per join saamfi_role_perm rp on rp.perm_perm_id = per.perm_id where r.role_id = rp.role_role_id ) and ?2 in ( select rp2.perm_perm_id from saamfi_role_perm rp2 where rp2.role_role_id = r.role_id) AND r.inst_inst_id = ?1", nativeQuery = true)
	List<SaamfiRole> getRolesByPermissionIdOper(long instId, long permissionId);

	@Query(value = "select r.* from Saamfi_role r where ?1 not in (select per.permtype_permtype_id from saamfi_permission per join role_permission rp on rp.perm_perm_id = per.perm_id where r.role_id = rp.role_role_id) and r.inst_inst_id = ?2", nativeQuery = true)
	List<SaamfiRole> getRolesByPermTypeId(long permtypeid, long instid);

	@Query(value = "SELECT rolee.* from Saamfi_role INNER JOIN saamfi_user_role ON saamfi_user_role.role_role_id = rolee.role_id WHERE saamfi_user_role.usr_user_id = ?1", nativeQuery = true)
	List<SaamfiRole> getRolesByPersonId(long personid);

	/**
	 * @author Juan David Carvajal CrudRepository function to delete a Rolee from
	 *         the database by id.
	 * @param roleId Long id of the rolee to be deleted
	 * @return The number of records deleted.
	 */
	public long removeByRoleId(long roleId);

	@Query(value = "select r.* from Saamfi_role r where ?1 and exists(select * from saamfi_role_perm rp join saamfi_permission p on rp.perm_perm_id = p.perm_id where rp.role_role_id=r.role_id and p.permtype_permtype_id = 1)", nativeQuery = true)
	List<SaamfiRole> roleHasPermissionTypeOne(long roleId);

	List<SaamfiRole> findBySaamfiInstitutionInstIdAndSaamfiSystemSysId(long instid, long sysid);

}