package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public interface AdmSaamfiRolPermController {

	/**
	 * This method add a permission to a role (Admin)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission that is added to the role
	 * @param roleeId      id of the role to which the permission is added
	 * @param instid       id of the institution to which the role belongs
	 * @return return OK, BAD_REQUEST or FORBIDDEN depending on the result of the
	 *         query
	 */
	public ResponseEntity<?> addPermissionToRole(long instid, long roleeId, long permissionId);

	/**
	 * This method is in charge of delete a permission that has a role
	 *
	 * @author Juan sebastian cardona (Admin)
	 * @param roleeId      is the id of the role that is associated with the
	 *                     permission
	 * @param permissionId is the id of the permission to delete
	 * @param instid       is the id of the institutions whose permissions will
	 *                     removed
	 * @return return OK, BAD_REQUEST or FORBIDDEN depending of the result of the
	 *         method in the moment to delete
	 */
	public ResponseEntity<?> deletePermissionToRole(long instid, long sysId, long roleeId, long permissionId);

	public List<SaamfiPermissionnStdOutDTO> queryPermissionsOfARole(long roleId, long instId);

}
