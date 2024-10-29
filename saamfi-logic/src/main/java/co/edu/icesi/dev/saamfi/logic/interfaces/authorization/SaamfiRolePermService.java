package co.edu.icesi.dev.saamfi.logic.interfaces.authorization;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public interface SaamfiRolePermService {

	/**
	 * This method add a permission to a role (Admin)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission that is added to the role
	 * @param roleeId      id of the role to which the permission is added
	 * @return return true or false depending on the result of the query
	 */
	public boolean addRolePermissionAdm(long roleeId, long permissionId);

	/**
	 * This method add a permission to a role (Admin)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission that is added to the role
	 * @param roleeId      id of the role to which the permission is added
	 * @return return true or false depending on the result of the query
	 */
	public boolean addRolePermissionOper(long roleeId, long permissionId);

	/**
	 * This method is in charge of delete a permission that has a role (Global)
	 *
	 * @author Juan Sebastian Cardona
	 * @param roleId       is the id of the role that is associated with the
	 *                     permission
	 * @param permissionId is the id of the permission to delete
	 * @return if the permission was deleted correctly return true bell return
	 *         false.
	 */
	public boolean deleteRolePermissionAdm(long roleId, long permissionId);

	/**
	 * This method is in charge of delete a permission that has a role (Admin)
	 *
	 * @author Juan Sebastian Cardona
	 * @param roleId       is the id of the role that is associated with the
	 *                     permission
	 * @param permissionId is the id of the permission to delete
	 * @return if the permission was deleted correctly return true bell return
	 *         false.
	 */
	public boolean deleteRolePermissionOper(long roleId, long permissionId);

	/**
	 * This method is in charge of get the list of permissions that has a role.
	 *
	 * @author Anderson Ramírez
	 *
	 * @param roleId is the id of the role that we want to get the list of
	 *               permissions.
	 * @return return the list of permissions of a specific role.
	 */
	public List<SaamfiPermissionnStdOutDTO> getPermissionsOfARole(long roleId);

}
