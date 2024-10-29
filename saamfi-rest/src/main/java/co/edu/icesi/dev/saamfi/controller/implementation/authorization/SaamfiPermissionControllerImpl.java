package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.PermissionnController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiPermissionsSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/permissions")
public class SaamfiPermissionControllerImpl implements PermissionnController {

	private SaamfiPermissionService permissionService;

	public SaamfiPermissionControllerImpl(SaamfiPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@Override
	@GetMapping("/")
	@PreAuthorize("hasAnyRole('Admin-permissions-glob','Admin-permissions-sys','Admin-permissions-inst')")
	public ResponseEntity<?> getPermissionsBySystem(@PathVariable long instid, @PathVariable long sysid) {
		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

		List<SaamfiPermission> permissions = new ArrayList<>();
		if (hasPermission) {
			permissions = permissionService.getPermissionsBySystem(sysid);

			SaamfiPermissionsSpeOutDTO permissionSpeDTO = getPermissionListResponse(permissions);
			return ResponseEntity.ok(permissionSpeDTO);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	public SaamfiPermissionsSpeOutDTO getPermissionListResponse(List<SaamfiPermission> permissions) {
		List<SaamfiPermissionnStdOutDTO> permissionDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		int index = 0;
		for (SaamfiPermission perm : permissions) {
			permissionDTO.get(index)
					.setPermtypeDisplayname(perm.getSaamfiPermissionType().getPermtypeDisplayname());
			permissionDTO.get(index).setPermissiontypeId(perm.getSaamfiPermissionType().getPermtypeId());

			index++;
		}

		SaamfiPermissionsSpeOutDTO permissionSpeDTO = new SaamfiPermissionsSpeOutDTO();
		permissionSpeDTO.setPermissions(permissionDTO);
		return permissionSpeDTO;
	}

	@Override
	@PostMapping("/")
	@PreAuthorize("hasAnyRole('Admin-permissions-glob','Admin-permissions-sys','Admin-permissions-inst')")
	public ResponseEntity<?> savePermissionsBySystem(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiPermissionnStdOutDTO obj) {
		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

		if (hasPermission) {
			SaamfiPermission permission = permissionService.savePermission(obj, sysid);
			if (permission != null) {
				SaamfiPermissionnStdOutDTO res = AuthorizationMapper.INSTANCE.asPermissionnStdOutDTO(permission);
				res.setPermissiontypeId(permission.getSaamfiPermissionType().getPermtypeId());
				res.setPermtypeDisplayname(permission.getSaamfiPermissionType().getPermtypeDisplayname());
				return ResponseEntity.ok(res);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/")
	@PreAuthorize("hasAnyRole('Admin-permissions-glob','Admin-permissions-sys','Admin-permissions-inst')")
	public ResponseEntity<?> updatePermission(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiPermissionnStdOutDTO obj) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

		if (hasPermission) {
			boolean update = permissionService.updatePermission(obj, sysid);
			if (update) {
				return ResponseEntity.ok().build();
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("/{permId}")
	@PreAuthorize("hasAnyRole('Admin-permissions-glob','Admin-permissions-sys','Admin-permissions-inst')")
	public ResponseEntity<?> deletePermission(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long permId) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

		if (hasPermission) {
			boolean deleted = permissionService.deletePermission(sysid, permId);
			if (deleted) {
				return ResponseEntity.ok().build();
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/roles/{roleId}")
	@PreAuthorize("hasAnyRole('Admin-roles-glob','Admin-permissions-glob','Admin-roles-inst','Admin-permissions-inst')")
	public ResponseEntity<?> getPermissionByRole(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long roleId) {
		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

		if (hasPermission) {
			List<SaamfiPermission> permissions = permissionService.getPermissionsByRole(roleId, instid, sysid);
			if (permissions == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			SaamfiPermissionsSpeOutDTO outData = getPermissionListResponse(permissions);

			return ResponseEntity.ok(outData);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

}
