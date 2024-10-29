
package co.edu.icesi.dev.saamfi.logic.interfaces.authorization;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;

public interface SaamfiRoleService {

	/**
	 * This function dictates if a given role for a given institution is able to be
	 * deleted by an oper user.
	 *
	 * @author Juan David Carvajal
	 * @param instid id of the institution
	 * @param roleid id of the role
	 * @return A boolean, true if the user can delete the role, false otherwise.
	 */
	public boolean canDeleteRoleOper(long instid, long roleid);

	boolean createRole(SaamfiRoleeStdInDTO rolee, long instid, long sysid);

	/**
	 * This function is in charge of the deletion of a role from the database.
	 *
	 * @author Juan David Carvajal
	 * @param roleId id of the role to delete
	 * @return Returns the number of rows deleted by the operation
	 */
	public long deleteById(long roleId);

	/**
	 * This method is in charge of getting all the roles
	 *
	 * @author Alejandro Narvaez
	 * @param SaamfiRolesSpeOutDTO has the id of the role that is associated with
	 *                             the permission and the id of the permission to
	 *                             delete
	 * @param instid               is the id of the institutions whose permissions
	 *                             will removed
	 * @return return OK, NOT_FOUND or FORBIDDEN depending of the result of the
	 *         method in the moment to delete
	 */
	public SaamfiRolesSpeOutDTO getRolesAdmin(long instid);

	public Iterable<SaamfiRole> getRolesByPermissionType(long permissionType, long instID);

	/**
	 * This method is in charge of getting all the roles without those ones who has
	 * permType 1
	 *
	 * @author Alejandro Narvaez
	 * @param SaamfiRolesSpeOutDTO has the id of the role that is associated with
	 *                             the permission and the id of the permission to
	 *                             delete
	 * @param instid               is the id of the institutions whose permissions
	 *                             will removed
	 * @return return OK, NOT_FOUND or FORBIDDEN depending of the result of the
	 *         method in the moment to delete
	 */
	public SaamfiRolesSpeOutDTO getRolesGlobal(long instid);

	/**
	 *
	 */
	public Long getRolIdByInstIdAndSysIdAndRoleName(String roleName, long instId, long sysId);

	boolean updateRole(SaamfiRoleeStdInDTO rolee, long instid, long sysid, long idRoleToUpdate);

	public List<SaamfiRole> getAllRoles();

	public List<SaamfiRole> getRolesBySys(long sysid);

	public List<SaamfiRole> getRolesBySysAndInst(long sysid, long instid);

}
