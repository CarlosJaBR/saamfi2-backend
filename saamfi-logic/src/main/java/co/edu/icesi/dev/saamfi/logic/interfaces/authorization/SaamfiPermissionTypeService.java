package co.edu.icesi.dev.saamfi.logic.interfaces.authorization;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;

public interface SaamfiPermissionTypeService {

	Iterable<SaamfiPermissionTypeStdOutDTO> getActivePermissiontype(long personId);

	List<SaamfiPermissionType> getPermTypeBySystem(long sysid);

}
