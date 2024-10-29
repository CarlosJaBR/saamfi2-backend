package co.edu.icesi.dev.saamfi.logic.interfaces.authorization;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;

public interface SaamfiUserRoleService {

	void addPersonRole(SaamfiUserRole personrole);

	boolean addRoleToUser(long instId, long userId, long roleId);

	boolean addRoleToUserAdm(long instId, long sysId, long userId, long roleId) throws Exception;

	boolean addRoleToUserOper(long instId, long sysId, long userId, long roleId) throws Exception;

	// List<PersonSpeInDTO> csvUpload(InputStream is, long instId) throws
	// NumberFormatException, Exception;

	public void deleteAllRolesFromUser(long instid, long sysid, long persid);

	Iterable<SaamfiPermissionnStdOutDTO> getActivePermissions(long personId);

	public Optional<SaamfiUserRole> getFirstUserRoleByRoleId(long roleId);

	Set<SimpleGrantedAuthority> getUserRoles(long userId);

	Set<SimpleGrantedAuthority> getUserRoles(long userId, long instid);

	Set<SimpleGrantedAuthority> getUserRoles(String username, long instid);

	Set<SimpleGrantedAuthority> getUserRolesSystemInstitution(String username, long sysid, long inst);

	public Iterable<SaamfiUserRole> getUserRolesAll(long persId);

	SaamfiRolesSpeOutDTO getUserRolesById(long personid);

	boolean hasCSVFormat(MultipartFile file);

}
