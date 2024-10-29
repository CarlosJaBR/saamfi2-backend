package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;

public interface SaamfiPermissionTypeRepo extends CrudRepository<SaamfiPermissionType, Long> {

	@Query(value = "SELECT distinct pt.* FROM saamfi_permission_type pt"
			+ "     JOIN saamfi_permission p ON p.permtype_permtype_id = pt.permtype_id "
			+ "     JOIN saamfi_role_perm rp ON rp.perm_perm_id = p.perm_id "
			+ "     JOIN saamfi_user_role ur ON rp.role_role_id = ur.role_role_id "
			+ "WHERE  ur.usr_user_id = ?1 AND pt.permtype_isactive = 'Y' "
			+ "AND p.perm_isactive = 'Y' order by pt.permtype_index", nativeQuery = true)
	public Iterable<SaamfiPermissionType> findPermissiontypesByPersonid(long personid);

	public List<SaamfiPermissionType> findBySaamfiSystemSysId(long sysid);

};