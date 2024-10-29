package co.edu.icesi.dev.saamfi.logic.interfaces.baseentities;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.speout.ParameterSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiParameterStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;

public interface SaamfiParameterService {
	
	
    public List<ParameterSpecOutDTO> getParametersByInstitution(long instid) throws NoResultException;

    SaamfiParameter deleteParameter(long instid) throws BadRequestDataException, NoResultException;

    ParameterSpecOutDTO getParameterById(long id) throws BadRequestDataException, NoResultException;


	ParameterSpecOutDTO editParameter(SaamfiParameter parameter, long paramid, long instid)
			throws NoResultException, BadRequestDataException;



	ParameterSpecOutDTO saveParameter(SaamfiParameter parameter, long instid)
			throws BadRequestDataException, NoResultException;

	List<ParameterSpecOutDTO> saveParameters(List<SaamfiParameterStdInDTO> parameters, long instid)
			throws BadRequestDataException, NoResultException;


}
