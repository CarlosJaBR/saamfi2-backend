package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.AdmSaamfiRolPermController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRolePermService;

/**
 * This class is in charge of management the Role-Permission for Admin Use
 *
 * @author Juan sebastian cardona
 * @author Christian Lopez
 */
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('Admin-roles-glob','Admin-permissions-glob','Admin-roles-inst','Admin-permissions-inst')")
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/roles/{roleId}/permissions")
public class AdmRolPermissionnControllerImpl implements AdmSaamfiRolPermController {

	private SaamfiRolePermService rolePermissionService;

	public AdmRolPermissionnControllerImpl(SaamfiRolePermService rolePermissionService) {
		this.rolePermissionService = rolePermissionService;
	}

	@Override
	@PostMapping("/{permissionId}")
	public ResponseEntity<?> addPermissionToRole(@PathVariable long instid, @PathVariable long roleId,
			@PathVariable long permissionId) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);

		if (hasPermission) {
			boolean added = rolePermissionService.addRolePermissionAdm(roleId, permissionId);
			if (added) {
				return ResponseEntity.ok("Agregado correctamente");
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@Override
	@GetMapping("/")
	public List<SaamfiPermissionnStdOutDTO> queryPermissionsOfARole(@PathVariable long roleeId,
			@PathVariable long instid) {
		if (ControllerAuthorizationValidator.validateInstitution(instid)) {
			return rolePermissionService.getPermissionsOfARole(roleeId);
		} else {
			return null;
		}

	}

	@DeleteMapping("/{permId}")
	public ResponseEntity<String> deletePermissionToRole(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long roleId, @PathVariable long permId) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);

		if (hasPermission) {
			boolean deleted = rolePermissionService.deleteRolePermissionAdm(roleId, permId);
			if (deleted) {
				return ResponseEntity.ok("Eliminado correctamente");
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

}
