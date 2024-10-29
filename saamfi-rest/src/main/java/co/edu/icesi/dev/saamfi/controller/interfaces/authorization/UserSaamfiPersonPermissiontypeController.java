package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;

public interface UserSaamfiPersonPermissiontypeController {

	Iterable<SaamfiPermissionTypeStdOutDTO> getActivePermissiontypes(long instid, long sysid, long personId);

}
