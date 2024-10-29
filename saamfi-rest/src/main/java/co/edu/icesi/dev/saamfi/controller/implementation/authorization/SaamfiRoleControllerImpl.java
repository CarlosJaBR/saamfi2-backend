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

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.AdmSaamfiRoleController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRoleService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;

//TODO implement and organize in appropriate controllers
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/roles")
@PreAuthorize("hasAnyRole('Admin-roles-sys','Admin-roles-glob','Admin-roles-inst')")
public class SaamfiRoleControllerImpl implements AdmSaamfiRoleController {

	private SaamfiRoleService roleService;

	private SaamfiUserRoleService userRoleService;

	private SaamfiPermissionService permissionService;

	public SaamfiRoleControllerImpl(SaamfiRoleService roleService, SaamfiUserRoleService personRoleService,
			SaamfiPermissionService permissionService) {
		this.roleService = roleService;
		this.userRoleService = personRoleService;
		this.permissionService = permissionService;
	}

	@Override
	@PostMapping("/")
	public ResponseEntity<String> createRole(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiRoleeStdInDTO roleDto) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);

		if (hasPermission) {
			boolean created = roleService.createRole(roleDto, instid, sysid);
			if (created) {
				return new ResponseEntity<String>("El rol ha sido creado correctamente.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No se pudo crear el rol. Intentelo de nuevo",
						HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@Override
	@DeleteMapping("/{roleid}")
	public ResponseEntity<String> DeleteRole(@PathVariable long instid, @PathVariable long sysid,
			@PathVariable long roleid) {
		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-roles-sys", instid, sysid);

		if (!hasPermission) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No está autorizado");
		}

		if (this.userRoleService.getFirstUserRoleByRoleId(roleid).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("No se puede eliminar un rol asignado a algún usuario.");
		}

		roleService.deleteById(roleid);
		return ResponseEntity.ok("Eliminado con éxito");
	};

	@Override
	@GetMapping("/")
	public ResponseEntity<SaamfiRolesSpeOutDTO> getRolees(@PathVariable long instid, @PathVariable long sysid) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-roles-sys", instid, sysid);

		List<SaamfiRole> roles = new ArrayList<>();
		if (hasPermission) {
			roles = roleService.getRolesBySysAndInst(sysid, instid);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE
				.asRoleeStdOutDTO(roles);
		SaamfiRolesSpeOutDTO rolesSpeOutDTO = new SaamfiRolesSpeOutDTO();
		rolesSpeOutDTO.setRoles(rolesDTO);

		return ResponseEntity.ok(rolesSpeOutDTO);

	}

	@Override
	@GetMapping("/permissions/{permissionId}")
	public ResponseEntity<?> getRolesAssociatedWithPermission(@PathVariable long instid,
			@PathVariable long permissionId) {

		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);

		if (hasPermission) {
			List<SaamfiRoleeStdOutDTO> roles = permissionService.getRolesAssociatedWithPermissionAdm(instid,
					permissionId);
			SaamfiRolesSpeOutDTO rolesSpeOutDTO = new SaamfiRolesSpeOutDTO();
			rolesSpeOutDTO.setRoles(roles);
			return new ResponseEntity<>(rolesSpeOutDTO, HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@Override
	@PutMapping("/{idRoleToUpdate}")
	public ResponseEntity<String> updateRole(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiRoleeStdInDTO roleDto,
			@PathVariable long idRoleToUpdate) {
		boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-roles-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
		hasPermission = hasPermission || ControllerAuthorizationValidator.hasPermission("Admin-roles-inst", instid);
		hasPermission = hasPermission
				|| ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);

		if (hasPermission) {
			boolean updated = roleService.updateRole(roleDto, instid, sysid, idRoleToUpdate);
			if (updated) {
				return new ResponseEntity<>("El rol ha sido actualizado correctamente", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("El rol no se ha podido actualizar correctamente", HttpStatus.BAD_REQUEST);

			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
