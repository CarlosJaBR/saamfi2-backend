package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;

public interface AdmSaamfiRoleController {

	public ResponseEntity<String> createRole(long instid, long sysid, SaamfiRoleeStdInDTO roleDto);

	/**
	 * This controller method is in charge of handling the delete action for a role
	 * when dealing with Administrator users.
	 *
	 * @author Juan David Carvajal
	 * @param instid Long that identifies an institution in the database
	 * @param roleid The roleId that identifies the Role to be deleted
	 * @return A String response entity notifying the result of the process. The
	 *         response can be OK, FORBIDDEN or CONFLICT.
	 */
	public ResponseEntity<String> DeleteRole(long instid, long sysid, long roleid);

	/**
	 * this method is in charge of getting all the roles
	 *
	 * @author Alejandro Narvaez
	 * @param SaamfiRolesSpeOutDTO has the id of the role that is associated with
	 *                             the
	 *                             permission and the id of the permission to delete
	 * @param instid               is the id of the institutions whose permissions
	 *                             will
	 *                             removed
	 * @return return OK, NOT_FOUND or FORBIDDEN depending of the result of the
	 *         method in the moment to delete
	 */
	public ResponseEntity<SaamfiRolesSpeOutDTO> getRolees(long instid, long sysid);

	/**
	 * get the roles associated with a permission (Admin)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission on which you want to consult the
	 *                     roles to which it has been associated
	 * @param instid       id of the institution to which the permission belongs
	 * @return return OK, BAD_REQUEST or FORBIDDEN depending on the result of the
	 *         query
	 */
	public ResponseEntity<?> getRolesAssociatedWithPermission(long instid, long permissionId);

	public ResponseEntity<String> updateRole(long instid, long sysid, SaamfiRoleeStdInDTO roleDto, long idRoleToUpdate);

}
