package co.edu.icesi.dev.saamfi.logic.implementation.baseentities;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.saamfi.dtos.speout.ParameterSpecOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiParameterService;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiInstitutionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiParameterRepo;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiParameterStdInDTO;

@Service
public class SaamfiParameterServiceImp implements SaamfiParameterService{
	
	 @Autowired
	 private SaamfiParameterRepo saamfiParameterRepo;
	 @Autowired
	 private SaamfiInstitutionRepo institutionRepo;

		@Override
		public ParameterSpecOutDTO saveParameter(SaamfiParameter parameter, long instid) throws BadRequestDataException, NoResultException {
			if(instid<0 || parameter.getParamName().isEmpty() || parameter.getParamType().isEmpty() || parameter.getParamValue().isEmpty()) {
				throw new BadRequestDataException();
			}
			SaamfiInstitution inst=institutionRepo.findById(instid).get();	
			if(inst!=null) {
				parameter.setSaamfiInstitution(inst);
				SaamfiParameter param= saamfiParameterRepo.save(parameter);
				ParameterSpecOutDTO paramDTO =new ParameterSpecOutDTO();
				paramDTO.setParamName(param.getParamName());
				paramDTO.setParamType(param.getParamType());
				paramDTO.setParamValue(param.getParamValue());
				paramDTO.setParamId(param.getParamId());
				return paramDTO;
			}else {
				throw new NoResultException();
			}
		}
		
	@Override
	public List<ParameterSpecOutDTO> saveParameters(List<SaamfiParameterStdInDTO> parameters, long instid) throws BadRequestDataException, NoResultException {
		List<ParameterSpecOutDTO> params = new ArrayList<ParameterSpecOutDTO>();
		for (SaamfiParameterStdInDTO parameter : parameters) {
			if(instid<0 || parameter.getParamName().isEmpty() || parameter.getParamType().isEmpty() || parameter.getParamValue().isEmpty()) {
				throw new BadRequestDataException();
			}
		}
		SaamfiInstitution inst=institutionRepo.findById(instid).get();	
		if(inst!=null) {
			for (SaamfiParameterStdInDTO parameter : parameters) {
				SaamfiParameter parameterNew=new SaamfiParameter();
				parameterNew.setParamName(parameter.getParamName());
				parameterNew.setParamType(parameter.getParamType());
				parameterNew.setParamValue(parameter.getParamValue());
				parameterNew.setSaamfiInstitution(inst);
				SaamfiParameter param= saamfiParameterRepo.save(parameterNew);
				ParameterSpecOutDTO paramDTO =new ParameterSpecOutDTO();
				paramDTO.setParamName(param.getParamName());
				paramDTO.setParamType(param.getParamType());
				paramDTO.setParamValue(param.getParamValue());
				paramDTO.setParamId(param.getParamId());
				params.add(paramDTO);
			}
			return params;
		}else {
			throw new NoResultException();
		}
	}

	@Override
	public ParameterSpecOutDTO editParameter(SaamfiParameter parameter,long paramid,long instid) throws NoResultException, BadRequestDataException {
		if(paramid<0 || parameter.getParamName().isEmpty() || parameter.getParamType().isEmpty() || parameter.getParamValue().isEmpty()) {
			throw new BadRequestDataException();
		}
		SaamfiInstitution inst=institutionRepo.findById(instid).get();	
		SaamfiParameter param=saamfiParameterRepo.findById(paramid).get();	
		if(param!=null && inst!=null) {
			param.setParamId(paramid);
			param.setParamName(parameter.getParamName());
			param.setParamType(parameter.getParamType());
			param.setParamValue(parameter.getParamValue());
			param.setSaamfiInstitution(inst);
					
			param=saamfiParameterRepo.save(param);
			ParameterSpecOutDTO paramDTO =new ParameterSpecOutDTO();
			paramDTO.setParamName(param.getParamName());
			paramDTO.setParamType(param.getParamType());
			paramDTO.setParamValue(param.getParamValue());
			paramDTO.setParamId(param.getParamId());
			return paramDTO;
		}else {
			throw new NoResultException();
		}
	}

	@Override
	public List<ParameterSpecOutDTO> getParametersByInstitution(long instid) throws NoResultException {
		List<SaamfiParameter> list;
		List<ParameterSpecOutDTO> params=new ArrayList<ParameterSpecOutDTO>();
		list=saamfiParameterRepo.findBySaamfiInstitutionInstId(instid);
		if(!list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				ParameterSpecOutDTO paramDTO=new ParameterSpecOutDTO();
				paramDTO.setParamName(list.get(i).getParamName());
				paramDTO.setParamType(list.get(i).getParamType());
				paramDTO.setParamValue(list.get(i).getParamValue());
				paramDTO.setParamId(list.get(i).getParamId());
				params.add(paramDTO);
			}
			return params;
		}else {
			throw new NoResultException();
		}
	}

	@Override
	public SaamfiParameter deleteParameter(long paramid) throws BadRequestDataException, NoResultException {
		if(paramid<0) {
			throw new BadRequestDataException();
		}else {
			Optional<SaamfiParameter> param=saamfiParameterRepo.findById(paramid);
			if(!param.isEmpty()) {
				saamfiParameterRepo.deleteById(paramid);
				return param.get();
			}else {
				throw new NoResultException();
			}
		}
	}

	@Override
	public ParameterSpecOutDTO getParameterById(long paramid) throws BadRequestDataException, NoResultException {
		if(paramid<0) {
			throw new BadRequestDataException();
		}else {
			Optional<SaamfiParameter> param=saamfiParameterRepo.findById(paramid);
			if(!param.isEmpty()) {
				ParameterSpecOutDTO paramDTO =new ParameterSpecOutDTO();
				paramDTO.setParamName(param.get().getParamName());
				paramDTO.setParamType(param.get().getParamType());
				paramDTO.setParamValue(param.get().getParamValue());
				paramDTO.setParamId(param.get().getParamId());
				return paramDTO;
			}else {
				throw new NoResultException();
			}
		}
	}

}
