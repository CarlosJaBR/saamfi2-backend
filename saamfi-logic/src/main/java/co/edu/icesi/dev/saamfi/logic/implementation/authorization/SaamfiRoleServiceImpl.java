
package co.edu.icesi.dev.saamfi.logic.implementation.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRoleService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiInstitutionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRolePermRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystemRepo;

@Service
public class SaamfiRoleServiceImpl implements SaamfiRoleService {

	private static final Logger logger = LoggerFactory.getLogger(SaamfiRoleServiceImpl.class);

	private SaamfiRoleRepo roleeRepo;
	@Autowired
	private SaamfiInstitutionRepo institutionRepo;
	@Autowired
	private SaamfiSystemRepo systemRepo;
	@Autowired
	private SaamfiRolePermRepo rolePermRepo;

	@Autowired
	public SaamfiRoleServiceImpl(SaamfiRoleRepo roleeRepo) {
		this.roleeRepo = roleeRepo;
	}

	@Override
	@Transactional
	public boolean canDeleteRoleOper(long instid, long roleid) {
		Iterable<SaamfiRole> adminRoles = getRolesByPermissionType(1, instid);
		boolean allowed = false;
		for (SaamfiRole role : adminRoles) {
			if (role.getSaamfiInstitution().getInstId() == instid && role.getRoleId() == roleid) {
				allowed = true;
				break;
			}
		}
		return allowed;
	}

	public List<SaamfiRole> convertListRolee(Iterable<SaamfiRole> rolees) {
		List<SaamfiRole> result = new ArrayList<SaamfiRole>();
		for (SaamfiRole entity : rolees) {
			result.add(entity);
		}
		return result;
	}

	@Override
	public boolean createRole(SaamfiRoleeStdInDTO roleDto, long instid, long sysid) {
		if (!roleDto.getRoleName().isEmpty() && !roleDto.getRoleDescription().isEmpty()) {
			List<SaamfiRole> roleSearchedByName = roleeRepo
					.getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(instid, sysid,
							roleDto.getRoleName());
			if (roleSearchedByName == null || roleSearchedByName.size() == 0) {
				SaamfiRole role = new SaamfiRole();
				role.setRoleDescription(roleDto.getRoleDescription());
				role.setRoleName(roleDto.getRoleName());
				Optional<SaamfiInstitution> inst = institutionRepo.findById(instid);
				Optional<SaamfiSystem> sys = systemRepo.findById(sysid);
				if (inst.isPresent() && sys.isPresent()) {
					role.setSaamfiInstitution(inst.get());
					role.setSaamfiSystem(sys.get());
					roleeRepo.save(role);
					return true;
				}

			}
		}
		return false;
	}

	@Override
	@Transactional
	public long deleteById(long roleId) {
		rolePermRepo.deleteBySaamfiRoleRoleId(roleId);

		return this.roleeRepo.removeByRoleId(roleId);
	}

	@Override
	public SaamfiRolesSpeOutDTO getRolesAdmin(long instid) {
		Iterable<SaamfiRole> rolees = roleeRepo.findBySaamfiInstitutionInstId(instid);
		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE
				.asRoleeStdOutDTO(this.convertListRolee(rolees));
		SaamfiRolesSpeOutDTO rolesSpeOutDTO = new SaamfiRolesSpeOutDTO();
		rolesSpeOutDTO.setRoles(rolesDTO);
		return rolesSpeOutDTO;

	}

	@Override
	public List<SaamfiRole> getAllRoles() {
		return roleeRepo.findAll();
	}

	@Override
	public List<SaamfiRole> getRolesBySys(long sysid) {
		return roleeRepo.findBySaamfiSystemSysId(sysid);
	}

	@Override
	public List<SaamfiRole> getRolesBySysAndInst(long sysid, long instid) {
		return roleeRepo.findBySaamfiInstitutionInstIdAndSaamfiSystemSysId(instid, sysid);
	}

	@Override
	public Iterable<SaamfiRole> getRolesByPermissionType(long permissionType, long instID) {
		return roleeRepo.getRolesByPermTypeId(permissionType, instID);
	}

	@Override
	public SaamfiRolesSpeOutDTO getRolesGlobal(long instid) {
		Iterable<SaamfiRole> rolees = roleeRepo.getRolesByPermTypeId(1, instid);
		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE
				.asRoleeStdOutDTO(this.convertListRolee(rolees));
		SaamfiRolesSpeOutDTO rolesSpeOutDTO = new SaamfiRolesSpeOutDTO();
		rolesSpeOutDTO.setRoles(rolesDTO);
		return rolesSpeOutDTO;
	}

	@Override
	public Long getRolIdByInstIdAndSysIdAndRoleName(String roleName, long instId, long sysId) {
		List<SaamfiRole> m = this.roleeRepo.getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(instId,
				sysId, roleName);
		Long n = null;
		try {
			if (m != null && m.size() > 0) {
				n = m.get(0).getRoleId();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return n;
	}

	@Override
	public boolean updateRole(SaamfiRoleeStdInDTO rolee, long instid, long sysid, long idRoleToUpdate) {

		if (!rolee.getRoleName().isEmpty() && !rolee.getRoleDescription().isEmpty()) {
			List<SaamfiRole> roleSearchedByName = roleeRepo
					.getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(instid, sysid,
							rolee.getRoleName());

			if (roleSearchedByName == null || roleSearchedByName.size() < 2) {
				if (roleSearchedByName.size() == 1 && roleSearchedByName.get(0).getRoleId() != idRoleToUpdate) {
					return false;
				}
				SaamfiRole roleToUpdate = roleeRepo.findById(idRoleToUpdate).get();
				roleToUpdate.setRoleName(rolee.getRoleName());
				roleToUpdate.setRoleDescription(rolee.getRoleDescription());
				roleeRepo.save(roleToUpdate);
				return true;
			}
		}
		return false;

	}
}
