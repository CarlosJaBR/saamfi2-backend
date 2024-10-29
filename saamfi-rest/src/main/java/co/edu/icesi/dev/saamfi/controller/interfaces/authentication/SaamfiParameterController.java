package co.edu.icesi.dev.saamfi.controller.interfaces.authentication;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.speout.ParameterSpecOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;

public interface SaamfiParameterController {

	ResponseEntity<ParameterSpecOutDTO> findParameterById(long paramid);

	ResponseEntity<List<ParameterSpecOutDTO>> findAllParametersByInstitution(long instid);

	ResponseEntity<ParameterSpecOutDTO> editParameter(SaamfiParameter parameter, long paramid, long instid);

	ResponseEntity<ParameterSpecOutDTO> saveParameter(SaamfiParameter parameter, long instid)
			throws BadRequestDataException, NoResultException, Exception;

	ResponseEntity<String> deleteParameter(long paramid, long instid);

}
