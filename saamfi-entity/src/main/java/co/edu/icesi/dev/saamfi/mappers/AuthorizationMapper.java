package co.edu.icesi.dev.saamfi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiPermissionnStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiPermissiontypeStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserRoleStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRolePermStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePermPK;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;

/**
 * @author Juan Carlos Muñoz
 *
 */
@Mapper
public interface AuthorizationMapper {

	AuthorizationMapper INSTANCE = Mappers.getMapper(AuthorizationMapper.class);

	// Rolee Entity

	// Permissionn Entity
	SaamfiPermission asPermisionnEntity(SaamfiPermissionnStdInDTO permissiondto);

	SaamfiPermission asPermisionnEntity(SaamfiPermissionnStdOutDTO permissiondto);

	// PermissionType Entity
	SaamfiPermissionType asPermisionntypeEntity(SaamfiPermissiontypeStdInDTO permissiontypedto);

	// Permissionn Entity
	ArrayList<SaamfiPermissionnStdOutDTO> asPermissionnStdOutDTO(Iterable<SaamfiPermission> permissions);

	// Permissionn Entity
	SaamfiPermissionnStdOutDTO asPermissionnStdOutDTO(SaamfiPermission permissionn);

	// PermissionType Entity
	Iterable<SaamfiPermissionTypeStdOutDTO> asPermissiontypesStdOutDTO(Iterable<SaamfiPermissionType> permissions);

	List<SaamfiPermissionTypeStdOutDTO> asPermissiontypesStdOutDTO(List<SaamfiPermissionType> permissions);

	// PersonRole Entity
	SaamfiUserRoleStdOutDTO asPersonRoleStdOutDTO(SaamfiUserRolePK personrole, SaamfiRole role);

	// Rolee Entity
	SaamfiRole asRoleeEntity(SaamfiRoleeStdInDTO roleedto);

	List<SaamfiRoleeStdOutDTO> asRoleeStdOutDTO(List<SaamfiRole> rolees);

	// RolePermission Entity
	SaamfiRolePermStdOutDTO asRolePermissionStdOutDTO(SaamfiRolePermPK rolepermission, SaamfiRole role,
			SaamfiPermission permission);

	// Permissionn Entity
	void updatePermission(SaamfiPermissionnStdOutDTO permissiondto, @MappingTarget SaamfiPermission permission);

	// Permissionn Entity
	// PersonRole Entity
	void updatePermissiontype(SaamfiPermissiontypeStdInDTO permissiontypedto,
			@MappingTarget SaamfiPermissionType permissiontype);

	// Rolee Entity
	void updateRolee(SaamfiRoleeStdInDTO resourceInput, @MappingTarget SaamfiRole rolee);

}
