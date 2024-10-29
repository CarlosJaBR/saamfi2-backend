package co.edu.icesi.dev.saamfi.mappers;

import java.util.ArrayList;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.spein.JwtAuthenticationRespInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserChangePassSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiLoginFullSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserrStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;

/**
 * @author Juan Carlos Muñoz
 *
 */
@Mapper
public interface AuthenticationMapper {

	AuthenticationMapper INSTANCE = Mappers.getMapper(AuthenticationMapper.class);

	// Person+Userr Entity + JWT authentication Response
	/**
	 * Method for mapping PersonIntInDTO, SaamfiUser and JwtAuthenticationRespInDTO
	 * in one class
	 *
	 * @param persondto instance
	 * @param user      instance
	 * @param response  instance
	 * @return
	 */
	SaamfiLoginFullSpeOutDTO asLoginSpeOutDTO(SaamfiUser persondto, JwtAuthenticationRespInDTO response);

	// Userr Entity
	SaamfiUser asUserEntity(SaamfiUserChangePassSpeInDTO userdtp);

	ArrayList<SaamfiUserrStdOutDTO> asUserrStdOutDTO(ArrayList<SaamfiUser> userrs);

	SaamfiUserrStdOutDTO asUserrStdOutDTO(SaamfiUser user);

	void updateUserr(SaamfiUserChangePassSpeInDTO userdtp, @MappingTarget SaamfiUser contact);

}
