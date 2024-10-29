package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;

public interface SaamfiPermissionRepo extends CrudRepository<SaamfiPermission, Long> {

	@Override
	public ArrayList<SaamfiPermission> findAll();

	@Query(value = "SELECT p.* FROM Saamfi_permission p "
			+ "	INNER JOIN saamfi_permission_type pt ON pt.permtype_id = p.permtype_permtype_id "
			+ "WHERE permtype_type <> 'S'", nativeQuery = true)
	public ArrayList<SaamfiPermission> findPermissionByPermissiontype();

	@Query(value = "SELECT p.* FROM saamfi_permission p, saamfi_role_perm rp WHERE rp.role_role_id = ?1 AND rp.perm_perm_id = p.perm_id", nativeQuery = true)
	public List<SaamfiPermission> findPermissionByRoleId(long roleId);

	@Query(value = "SELECT DISTINCT p.* FROM saamfi_permission p "
			+ " INNER JOIN saamfi_role_perm rp ON rp.perm_perm_id = p.perm_id "
			+ " INNER JOIN saamfi_user_role ur ON rp.role_role_id = ur.role_role_id "
			+ " WHERE ur.usr_user_id = ?1 AND p.perm_isactive = 'Y' order by p.perm_index", nativeQuery = true)
	public ArrayList<SaamfiPermission> findPermissionByUserId(Long userId);

	@Query(value = "Select DISTINCT p.* From saamfi_permission p "
			+ "		Inner Join saamfi_permission_type pt ON p.permtype_permtype_id = pt.permtype_id "
			+ " 	WHERE pt.systm_sys_id = ?1", nativeQuery = true)
	public List<SaamfiPermission> findPermissionBySystem(long sysid);

	@Query(value = "SELECT DISTINCT p.* FROM saamfi_permission p "
			+ " INNER JOIN saamfi_role_perm rp ON rp.perm_perm_id = p.perm_id "
			+ " INNER JOIN saamfi_role r ON r.role_id = rp.role_role_id "
			+ " INNER JOIN saamfi_user_role ur ON r.role_id = ur.role_role_id "
			+ "	INNER JOIN saamfi_user u ON u.user_id = ur.usr_user_id "
			+ " WHERE u.user_username = ?1 AND p.perm_isactive = 'Y' "
			+ "		AND r.systm_sys_id = ?2 AND r.inst_inst_id = ?3  order by p.perm_index", nativeQuery = true)
	public ArrayList<SaamfiPermission> findPermissionByUsernameInSystemInst(String username, Long sysId, Long inst);

	@Query(value = "SELECT DISTINCT p.* FROM saamfi_permission p "
			+ " INNER JOIN saamfi_role_perm rp ON rp.perm_perm_id = p.perm_id "
			+ " INNER JOIN saamfi_role r ON r.role_id = rp.role_role_id "
			+ " INNER JOIN saamfi_user_role ur ON r.role_id = ur.role_role_id "
			+ "	INNER JOIN saamfi_user u ON u.user_id = ur.usr_user_id "
			+ " WHERE u.user_username = ?1 AND p.perm_isactive = 'Y' "
			+ "		AND r.inst_inst_id = ?2  order by p.perm_index", nativeQuery = true)
	public ArrayList<SaamfiPermission> findPermissionByUsernameInInst(String name, long instid);
};
