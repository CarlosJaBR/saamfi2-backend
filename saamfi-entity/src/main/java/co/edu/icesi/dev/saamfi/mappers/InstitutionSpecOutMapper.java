package co.edu.icesi.dev.saamfi.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;

@Mapper
public interface InstitutionSpecOutMapper {
    InstitutionSpecOutMapper INSTANCE = Mappers.getMapper(InstitutionSpecOutMapper.class);

    List<InstitutionSpecOutDTO> asInstitutionsStdOutDTO(List<SaamfiInstitution> institution);
    
    InstitutionSpecOutDTO saamfiInstitutionToInstitutionSpecOutDTO(SaamfiInstitution institution);

}