package co.edu.icesi.dev.saamfi.logic.implementation.authorization;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionTypeService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionTypeRepo;

@Service
public class SaamfiPermissionTypeServiceImpl implements SaamfiPermissionTypeService {

	private SaamfiPermissionTypeRepo permissiontypeRepo;

	public SaamfiPermissionTypeServiceImpl(SaamfiPermissionTypeRepo permissiontypeRepo) {
		this.permissiontypeRepo = permissiontypeRepo;
	}

	@Override
	public Iterable<SaamfiPermissionTypeStdOutDTO> getActivePermissiontype(long personid) {
		Iterable<SaamfiPermissionType> permissions = permissiontypeRepo.findPermissiontypesByPersonid(personid);
		// Iterable<Permissiontype> permissions = permissiontypeRepo.findAll();
		return AuthorizationMapper.INSTANCE.asPermissiontypesStdOutDTO(permissions);
	}

	@Override
	public List<SaamfiPermissionType> getPermTypeBySystem(long sysid) {

		return permissiontypeRepo.findBySaamfiSystemSysId(sysid);
	}

}
