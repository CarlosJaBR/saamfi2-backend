package co.edu.icesi.dev.saamfi.logic.interfaces.baseentities;

import java.util.List;


import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiInstitutionStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;



public interface SaamfiInstitutionService {

    public List<InstitutionSpecOutDTO> getInstitutions() throws NoResultException;
	InstitutionSpecOutDTO deleteInstitution(long idinstitution) throws NoResultException, BadRequestDataException;
	InstitutionSpecOutDTO getInstitutionById(long id) throws NoResultException, BadRequestDataException;

	InstitutionSpecOutDTO editInstitution(SaamfiInstitution institution, long instid)
			throws NoResultException, BadRequestDataException;


	InstitutionSpecOutDTO getInstitutionBySystemId(long sysid, long instid) throws Exception;


	InstitutionSpecOutDTO saveInstitution(SaamfiInstitutionStdInDTO institution) throws BadRequestDataException, NoResultException;
	
	SaamfiInstitution findById(long id);

	List<InstitutionSpecOutDTO> findInstitutionsByAdminInstitutions(Long userId) throws NoResultException;


}
