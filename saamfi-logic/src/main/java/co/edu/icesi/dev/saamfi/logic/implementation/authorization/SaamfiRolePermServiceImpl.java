package co.edu.icesi.dev.saamfi.logic.implementation.authorization;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePerm;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePermPK;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRolePermService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRolePermRepo;

@Service
public class SaamfiRolePermServiceImpl implements SaamfiRolePermService {

	private SaamfiRolePermRepo rolePermissionRepo;

	private SaamfiPermissionRepo permissionRepo;

	@Autowired
	public SaamfiRolePermServiceImpl(SaamfiRolePermRepo rolePermissionRepo, SaamfiPermissionRepo permissionRepo) {
		this.rolePermissionRepo = rolePermissionRepo;
		this.permissionRepo = permissionRepo;
	}

	@Override
	public boolean addRolePermissionAdm(long roleeId, long permissionId) {
		SaamfiRolePerm rolePermissionFound = rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleeId,
				permissionId);
		if (rolePermissionFound == null) {
			SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
			rolePermissionPK.setRoleRoleId(roleeId);
			rolePermissionPK.setPermPermId(permissionId);
			SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
			rolePermissionToAdd.setId(rolePermissionPK);
			rolePermissionRepo.save(rolePermissionToAdd);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addRolePermissionOper(long roleeId, long permissionId) {
		Optional<SaamfiPermission> permissionOpt = permissionRepo.findById(permissionId);
		SaamfiPermission permission = permissionOpt.get();
		if (permission.getSaamfiPermissionType().getPermtypeType().equals("S")) {
			return false;
		}
		SaamfiRolePerm rolePermissionFound = rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleeId,
				permissionId);
		if (rolePermissionFound == null) {
			SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
			rolePermissionPK.setRoleRoleId(roleeId);
			rolePermissionPK.setPermPermId(permissionId);
			SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
			rolePermissionToAdd.setId(rolePermissionPK);
			rolePermissionRepo.save(rolePermissionToAdd);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRolePermissionAdm(long roleId, long permissionId) {
		boolean deleted = false;
		SaamfiRolePerm rolPermission = rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId);
		if (rolPermission != null) {
			rolePermissionRepo.delete(rolPermission);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public boolean deleteRolePermissionOper(long roleId, long permissionId) {
		boolean deleted = false;
		Optional<SaamfiPermission> permissionOpt = permissionRepo.findById(permissionId);
		SaamfiPermission permission = permissionOpt.get();
		if (permission.getSaamfiPermissionType().getPermtypeType().equals("S")) {
			return false;
		} else {
			SaamfiRolePerm rolPermission = rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId);
			if (rolPermission != null) {
				rolePermissionRepo.delete(rolPermission);
				deleted = true;
			} else {
			}
			return deleted;
		}
	}

	@Override
	public List<SaamfiPermissionnStdOutDTO> getPermissionsOfARole(long roleId) {
		List<SaamfiPermission> permissions = permissionRepo.findPermissionByRoleId(roleId);
		List<SaamfiPermissionnStdOutDTO> permissionsDTO = AuthorizationMapper.INSTANCE.asPermissionnStdOutDTO(permissions);
		return permissionsDTO;
	}

}
