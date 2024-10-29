package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;

public interface InternalSaamfiUserRoleController {

	public SaamfiRolesSpeOutDTO getPersonRoles(long instid, long personid);

}
