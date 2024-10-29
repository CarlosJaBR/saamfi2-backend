package co.edu.icesi.dev.saamfi.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserrStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	List<SaamfiUserrStdOutDTO> asListUserrStdOutDTO(List<SaamfiUser> userDto);

	List<SaamfiUserSpeOutDTO> asListUsersSpecOutDTO(List<SaamfiUser> userSpectDto);

	SaamfiUserrStdOutDTO asUserrStdOutDTO(SaamfiUser user);

	void updateUser(SaamfiUserStInDTO userDto, @MappingTarget SaamfiUser user);

	SaamfiNewuserSdtInDTO asNewUserStdDTO(SaamfiUser user);

	SaamfiUser asNewSaamfiUser(SaamfiNewuserSdtInDTO user);

	void updateSaamfiUser(SaamfiNewuserSdtInDTO userDto, @MappingTarget SaamfiUser user);
}
