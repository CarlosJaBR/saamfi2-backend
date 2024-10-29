package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;

public interface OperSaamfiPermissionController {

	/**
	 * @author Alvaro José Escobar Gonzalez
	 * @Method This method receives a request done by the Oper to read all the
	 *         permissions in the system.
	 * @param instid
	 * @Return: Respond a response entity OK if the action was carried out
	 *          correctly. If the person who tries to perform this action does not
	 *          have permissions to do it, this method will respond restricted
	 *          access.
	 */
	public ResponseEntity<?> getAllPermissions(long instid);

}
