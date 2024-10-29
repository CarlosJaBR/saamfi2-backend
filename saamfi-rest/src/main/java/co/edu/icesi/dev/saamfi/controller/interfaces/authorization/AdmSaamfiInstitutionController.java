package co.edu.icesi.dev.saamfi.controller.interfaces.authorization;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiInstitutionStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;

public interface AdmSaamfiInstitutionController {

	ResponseEntity<String> deleteInstitution(long id) throws NoResultException, BadRequestDataException;

	ResponseEntity<InstitutionSpecOutDTO> findInstitutionById(long id)
			throws NoResultException, BadRequestDataException;

	ResponseEntity<InstitutionSpecOutDTO> editInstitution(long instid, SaamfiInstitution institution)
			throws NoResultException, BadRequestDataException;

	ResponseEntity<InstitutionSpecOutDTO> saveInstitution(SaamfiInstitutionStdInDTO institution)
			throws BadRequestDataException, NoResultException, Exception;
}
