package co.edu.icesi.dev.saamfi.logic.implementation.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiPermissionsSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionTypeRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRolePermRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;

@Service
public class SaamfiPermissionServiceImpl implements SaamfiPermissionService {

	private SaamfiRoleRepo roleRepo;

	private SaamfiPermissionRepo permissionRepo;

	@Autowired
	private SaamfiPermissionTypeRepo permissionTypeRepo;

	@Autowired
	private SaamfiRolePermRepo rolePermRepo;

	@Autowired
	public SaamfiPermissionServiceImpl(SaamfiRoleRepo roleeRepo, SaamfiPermissionRepo permissionRepo) {
		this.roleRepo = roleeRepo;
		this.permissionRepo = permissionRepo;
	}

	@Override
	public SaamfiPermissionsSpeOutDTO getExistingPermissionsAdm() {
		List<SaamfiPermission> permissions = permissionRepo.findAll();
		List<SaamfiPermissionnStdOutDTO> permissionDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		SaamfiPermissionsSpeOutDTO permissionSpeDTO = new SaamfiPermissionsSpeOutDTO();
		permissionSpeDTO.setPermissions(permissionDTO);
		return permissionSpeDTO;
	}

	@Override
	public SaamfiPermissionsSpeOutDTO getExistingPermissionsOper() {
		List<SaamfiPermission> permissions = permissionRepo.findPermissionByPermissiontype();
		List<SaamfiPermissionnStdOutDTO> permissionDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		SaamfiPermissionsSpeOutDTO permissionSpeDTO = new SaamfiPermissionsSpeOutDTO();
		permissionSpeDTO.setPermissions(permissionDTO);
		return permissionSpeDTO;
	}

	@Override
	public List<SaamfiRoleeStdOutDTO> getRolesAssociatedWithPermissionAdm(long instId, long permissionId) {
		List<SaamfiRole> roles = roleRepo.getRolesByInstiAndPermissionId(instId, permissionId);
		if (roles == null) {
			return null;
		} else {
			List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE.asRoleeStdOutDTO(roles);
			for (int i = 0; i < roles.size(); i++) {
				rolesDTO.get(i).setSystem(roles.get(i).getSaamfiSystem().getSysName());
				rolesDTO.get(i).setInst(roles.get(i).getSaamfiInstitution().getInstName());

			}
			return rolesDTO;
		}
	}

	@Override
	public List<SaamfiRoleeStdOutDTO> getRolesAssociatedWithPermissionOper(long instId, long permissionId) {
		List<SaamfiRole> roles = roleRepo.getRolesByPermissionIdOper(instId, permissionId);
		if (roles == null) {
			return null;
		} else {
			List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE.asRoleeStdOutDTO(roles);
			return rolesDTO;
		}

	}

	@Override
	public List<SaamfiPermission> getPermissionsBySystem(long sysid) {
		List<SaamfiPermission> permissions = permissionRepo.findPermissionBySystem(sysid);
		return permissions;
	}

	@Override
	public boolean userHasPermissionInSystemAndInst(String name, long sysid, long instId, String permName) {
		ArrayList<SaamfiPermission> permissions = permissionRepo.findPermissionByUsernameInSystemInst(name, sysid,
				instId);
		boolean hasPermission = permissions.stream().anyMatch(p -> p.getPermName().equals(permName));
		return hasPermission;

	}

	@Override
	public boolean userHasPermissionInInst(String name, long instid, String permName) {
		ArrayList<SaamfiPermission> permissions = permissionRepo.findPermissionByUsernameInInst(name, instid);
		boolean hasPermission = permissions.stream().anyMatch(p -> p.getPermName().equals(permName));
		return hasPermission;
	}

	@Override
	public SaamfiPermission savePermission(SaamfiPermissionnStdOutDTO permData, long sysid) {

		SaamfiPermission newPerm = AuthorizationMapper.INSTANCE.asPermisionnEntity(permData);

		Optional<SaamfiPermissionType> type = permissionTypeRepo.findById(permData.getPermissiontypeId());
		if (type.isEmpty() || type.get().getSaamfiSystem().getSysId() != sysid) {
			return null;
		}

		newPerm.setSaamfiPermissionType(type.get());
		newPerm.setPermtypeDoubleFactor("A");
		newPerm = permissionRepo.save(newPerm);
		return newPerm;

	}

	@Override
	public boolean updatePermission(SaamfiPermissionnStdOutDTO obj, long sysid) {

		Optional<SaamfiPermission> perm = permissionRepo.findById(obj.getPermId());
		if (perm.isEmpty()) {
			return false;
		}
		SaamfiPermission permission = perm.get();
		Optional<SaamfiPermissionType> type = permissionTypeRepo.findById(obj.getPermissiontypeId());
		if (type.isEmpty() || type.get().getSaamfiSystem().getSysId() != sysid) {
			return false;
		}
		permission.setSaamfiPermissionType(type.get());
		AuthorizationMapper.INSTANCE.updatePermission(obj, permission);
		permission = permissionRepo.save(permission);

		return true;
	}

	@Override
	@Transactional
	public boolean deletePermission(long sysid, long permId) {

		Optional<SaamfiPermission> perm = permissionRepo.findById(permId);
		if (perm.isEmpty()) {
			return false;
		}
		SaamfiPermission permission = perm.get();
		SaamfiPermissionType type = permission.getSaamfiPermissionType();
		if (type == null || type.getSaamfiSystem().getSysId() != sysid) {
			return false;
		}

		rolePermRepo.deleteBySaamfiPermissionPermId(permId);
		permissionRepo.delete(permission);

		return true;
	}

	@Override
	public List<SaamfiPermission> getPermissionsByRole(long roleId, long instid, long sysid) {
		Optional<SaamfiRole> role = roleRepo.findById(roleId);
		if (role.isEmpty()) {
			return null;
		}
		SaamfiRole rol = role.get();

		if (rol.getSaamfiInstitution().getInstId() != instid || rol.getSaamfiSystem().getSysId() != sysid) {
			return null;

		}

		List<SaamfiPermission> permissions = permissionRepo.findPermissionByRoleId(roleId);
		return permissions;
	}

}
