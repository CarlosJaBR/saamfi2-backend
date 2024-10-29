package co.edu.icesi.dev.saamfi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.intin.BannerPersonIntInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SammfiLoginMinSpeOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;

@Mapper
public interface RegisterMapper {

	RegisterMapper INSTANCE = Mappers.getMapper(RegisterMapper.class);

	SammfiLoginMinSpeOutDTO asPersonStdOutDTO(BannerPersonIntInDTO userPersondto);
	SaamfiUser asUserrEntity(SaamfiUserStInDTO userPersondto);
}
