package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;

public interface SaamfiUserRoleRepo extends CrudRepository<SaamfiUserRole, SaamfiUserRolePK> {

	public List<SaamfiUserRole> findBySaamfiUserUserId(long userId);

	public SaamfiUserRole findBySaamfiUserUserIdAndSaamfiRoleRoleId(long userId, long roleRoleId);

	public Optional<SaamfiUserRole> findFirstBySaamfiRoleRoleId(long roleId);

	// TODO fixme
	@Query(value = "SELECT n.perm_name FROM Saamfi_permission n INNER JOIN (SELECT perm_perm_id FROM saamfi_role_perm r "
			+ "INNER JOIN (SELECT role_role_id FROM saamfi_user_role p WHERE p.usr_user_id = ?1) t "
			+ "ON r.role_role_id = t.role_role_id) o ON n.perm_id = o.perm_perm_id", nativeQuery = true)
	public Iterable<String> findPermisionsByUser(long userId);

	@Query(value = "SELECT n.perm_name FROM Saamfi_permission n INNER JOIN (SELECT perm_perm_id FROM saamfi_role_perm r "
			+ "INNER JOIN (SELECT role_role_id FROM saamfi_user_role p WHERE p.usr_user_id = "
			+ "(SELECT a.user_id FROM saamfi_user a WHERE a.user_document_id = ?1 AND a.inst_inst_id = ?2)) t"
			+ " ON r.role_role_id = t.role_role_id) o ON n.perm_id = o.perm_perm_id", nativeQuery = true)
	public Iterable<String> findPermisionsByUserAndInstId(long userId, long instid);

	@Query(value = "SELECT n.perm_name FROM Saamfi_permission n INNER JOIN (SELECT perm_perm_id FROM saamfi_role_perm r "
			+ "			INNER JOIN (SELECT role_role_id FROM saamfi_user_role p WHERE p.usr_user_id = "
			+ "			(SELECT a.user_id FROM saamfi_user a WHERE a.user_username = ?1 "
			+ "            AND ( a.inst_inst_id  = ?2 or ?2 is null))) t"
			+ "			ON r.role_role_id = t.role_role_id) o ON n.perm_id = o.perm_perm_id", nativeQuery = true)
	public Iterable<String> findPermisionsByUserUserameAndInstId(String username, Long instid);

	@Query(value = "SELECT n.perm_name FROM Saamfi_permission n INNER JOIN "
			+ "			(SELECT perm_perm_id FROM saamfi_role_perm r INNER JOIN "
			+ "				(SELECT role_id FROM saamfi_role l, "
			+ "					(SELECT role_role_id FROM saamfi_user_role p WHERE p.usr_user_id = "
			+ "						(SELECT a.user_id FROM saamfi_user a WHERE a.user_username = ?1 ) "
			+ "					) t WHERE t.role_role_id = l.role_id AND l.systm_sys_id = ?2 AND l.inst_inst_id = ?3"
			+ "				) k ON r.role_role_id = k.role_id "
			+ "			) o ON n.perm_id = o.perm_perm_id", nativeQuery = true)
	public Iterable<String> findPermisionsByUserUserameAndSystIdAndInst(String username, Long sysId, Long instId);
};