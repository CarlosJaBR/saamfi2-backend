package co.edu.icesi.dev.saamfi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	SaamfiRole asRolee(SaamfiRoleeStdInDTO roleeDTO);

}
