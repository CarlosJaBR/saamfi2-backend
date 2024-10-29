package co.edu.icesi.dev.saamfi.logic.interfaces.authorization;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiPermissionsSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;

public interface SaamfiPermissionService {

	/**
	 * @author Alvaro José Escobar Gonzalez
	 * @Method Method to read the list of all the permissions in the system
	 * @Return: PermissionsSpeOutDTO
	 */
	public SaamfiPermissionsSpeOutDTO getExistingPermissionsAdm();

	/**
	 * @author Alvaro José Escobar Gonzalez
	 * @Method Method to read the list of permissions in the system except the Admin
	 *         permissions
	 * @Return: PermissionsSpeOutDTO
	 */
	public SaamfiPermissionsSpeOutDTO getExistingPermissionsOper();

	/**
	 * This method gets the roles associated with a permission (Admin)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission that is added to the role
	 * @param instid       id of the institution to which the role belongs
	 * @return return returns a list with the roles associated with the permission
	 */
	public List<SaamfiRoleeStdOutDTO> getRolesAssociatedWithPermissionAdm(long instId, long permissionId);

	/**
	 * This method gets the roles associated with a permission (Oper)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission that is added to the role
	 * @param instid       id of the institution to which the role belongs
	 * @return return @return return returns a list with the roles associated with
	 *         the permission
	 */
	public List<SaamfiRoleeStdOutDTO> getRolesAssociatedWithPermissionOper(long instId, long permissionId);

	public List<SaamfiPermission> getPermissionsBySystem(long sysid);

	public boolean userHasPermissionInSystemAndInst(String name, long sysid, long instId, String string);

	public boolean userHasPermissionInInst(String name, long instid, String string);

	public SaamfiPermission savePermission(SaamfiPermissionnStdOutDTO permData, long sysid);

	public boolean updatePermission(SaamfiPermissionnStdOutDTO obj, long sysid);

	public boolean deletePermission(long sysid, long permId);

	public List<SaamfiPermission> getPermissionsByRole(long roleId, long instid, long sysid);

}
