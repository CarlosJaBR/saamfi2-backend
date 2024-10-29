package co.edu.icesi.dev.saamfi.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiSystemFullSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiSystemStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiSystemStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;

@Mapper
public interface SystemMapper {
	SystemMapper INSTANCE = Mappers.getMapper(SystemMapper.class);
	
	SaamfiSystemStdOutDTO asSystemStdOutDTO(SaamfiSystem sys);
	SaamfiSystemFullSpeOutDTO asSystemFullSpeOutDTO(SaamfiSystem sys);
	List<SaamfiSystemStdOutDTO> asListSystemStdOutDTO(List<SaamfiSystem> systems);
	List<SaamfiSystemFullSpeOutDTO> asListSystemFullSpeOutDTO(List<SaamfiSystem> systems);
	SaamfiSystem asSaamfiSystem(SaamfiSystemStdInDTO system);
}
