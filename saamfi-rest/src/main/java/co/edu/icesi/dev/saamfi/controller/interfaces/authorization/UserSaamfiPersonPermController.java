package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;

public interface UserSaamfiPersonPermController {

	Iterable<SaamfiPermissionnStdOutDTO> getActivePermissions(long instid, long sysid, long personid);

}
