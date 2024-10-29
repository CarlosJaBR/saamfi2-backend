package co.edu.icesi.dev.saamfi.repository.interfaces;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiRolePerm;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePermPK;

public interface SaamfiRolePermRepo extends CrudRepository<SaamfiRolePerm, SaamfiRolePermPK> {

	public SaamfiRolePerm findByIdRoleRoleIdAndIdPermPermId(long roleRoleId, long permPermId);

	@Modifying
	@Query(value = "DELETE from saamfi_role_perm rp where rp.perm_perm_id = ?1 ", nativeQuery = true)
	public void deleteBySaamfiPermissionPermId(long permId);

	@Modifying
	@Query(value = "DELETE from saamfi_role_perm rp where rp.role_role_id = ?1 ", nativeQuery = true)
	public void deleteBySaamfiRoleRoleId(long roleid);

};
