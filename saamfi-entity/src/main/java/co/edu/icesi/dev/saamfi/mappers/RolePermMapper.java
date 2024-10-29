package co.edu.icesi.dev.saamfi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRolePermPKStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePerm;

@Mapper
public interface RolePermMapper {
	RolePermMapper INSTANCE = Mappers.getMapper(RolePermMapper.class);

	SaamfiRolePerm asRolePermission(SaamfiRolePermPKStdInDTO rolePermissionPKStdInDTO);
}
