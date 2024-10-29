
package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;

public interface OperSaamfiRoleController {

	public ResponseEntity<String> createRole(long instid, SaamfiRoleeStdInDTO roleDto);

	/**
	 * This controller method is in charge of handling the delete action for a role
	 * when dealing with Operator users.
	 *
	 * @author Juan David Carvajal
	 * @param instid Long that identifies an institution in the database
	 * @param roleid The roleId that identifies the Role to be deleted
	 * @return A String response entity notifying the result of the process. The
	 *         response can be OK, FORBIDDEN or CONFLICT.
	 */
	public ResponseEntity<String> DeleteRole(@PathVariable long instid, @PathVariable long roleid);

	/**
	 * This method is in charge of getting all the roles without those ones
	 *
	 * @author Alejandro Narvaez who has permType 1
	 * @param SaamfiRolesSpeOutDTO has the id of the role that is associated with
	 *                             the
	 *                             permission and the id of the permission to delete
	 * @param instid               is the id of the institutions whose permissions
	 *                             will
	 *                             removed
	 * @return return OK, NOT_FOUND or FORBIDDEN depending of the result of the
	 *         method in the moment to delete
	 */
	public ResponseEntity<SaamfiRolesSpeOutDTO> getExistingRolee(long instid);

	/**
	 * Get the roles associated with a permission (Oper)
	 *
	 * @author Christian Felipe López
	 * @param permissionId id of the permission on which you want to consult the
	 *                     roles to which it has been associated
	 * @param instid       id of the institution to which the permission belongs
	 * @return return OK, BAD_REQUEST or FORBIDDEN depending on the result of the
	 *         query
	 */
	public ResponseEntity<?> getRolesAssociatedWithPermission(long instid, long permissionId);

	public ResponseEntity<String> updateRole(long instid, SaamfiRoleeStdInDTO roleDto, long idRoleToUpdate);

}
