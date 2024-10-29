package co.edu.icesi.dev.saamfi.logic.implementation.authorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiUserRoleRepo;

@Service
public class SaamfiUserRoleServiceImpl implements SaamfiUserRoleService {

	public static String TYPE = "text/csv";

	public static String EXTERNAL_ROLE_ID = "ExternalRoleId";

	private SaamfiRoleRepo saamfiRoleRepoo;

	private SaamfiUserRoleRepo saamfiUserRoleRepo;

	private SaamfiRoleServiceImpl rolServiceImpl;

	HashMap<String, String> relationUserPerson = new HashMap<>();

	HashMap<String, ArrayList<Long>> idUserRole = new HashMap<>();

	private SaamfiPermissionRepo permissionnRepo;

	@Autowired
	public SaamfiUserRoleServiceImpl(SaamfiUserRoleRepo personRoleRepo, SaamfiRoleRepo roleeRepo,
			SaamfiRoleServiceImpl rolServiceImpl, SaamfiPermissionRepo permissionnRepo) {
		this.permissionnRepo = permissionnRepo;
		this.saamfiUserRoleRepo = personRoleRepo;
		this.saamfiRoleRepoo = roleeRepo;
		this.rolServiceImpl = rolServiceImpl;
	}

	@Override
	public void addPersonRole(SaamfiUserRole personrole) {
		saamfiUserRoleRepo.save(personrole);
	}

	@Override
	public boolean addRoleToUser(long instId, long userId, long roleId) {
		SaamfiRole role = null;
		try {
			role = saamfiRoleRepoo.findById(roleId).get();
		} catch (Exception e) {
			return false;
		}
		if (userId != 0 && role != null) {
			SaamfiUserRole personroleFound = saamfiUserRoleRepo.findBySaamfiUserUserIdAndSaamfiRoleRoleId(userId,
					roleId);
			if (personroleFound == null) {
				SaamfiUserRolePK personRolePk = new SaamfiUserRolePK();
				personRolePk.setUsrUserId(userId);
				personRolePk.setRoleRoleId(roleId);
				SaamfiUserRole personRoleToAdd = new SaamfiUserRole();
				personRoleToAdd.setId(personRolePk);
				saamfiUserRoleRepo.save(personRoleToAdd);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean addRoleToUserAdm(long instId, long sysId, long userId, long roleId) throws Exception {
		SaamfiRole role = null;
		try {
			role = saamfiRoleRepoo.findById(roleId).get();
		} catch (Exception e) {
			throw new Exception("Verifique el documento al cual va a agregar el rol");
		}

		boolean rolInstiId = false;
		List<SaamfiRole> rolInsti = saamfiRoleRepoo
				.getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(instId, sysId, role.getRoleName());
		if (rolInsti.contains(role)) {
			rolInstiId = true;
		}
		if (rolInstiId == true) {
			if (userId != 0 && role != null) {
				SaamfiUserRole personroleFound = saamfiUserRoleRepo.findBySaamfiUserUserIdAndSaamfiRoleRoleId(userId,
						roleId);
				if (personroleFound == null) {
					SaamfiUserRolePK personRolePk = new SaamfiUserRolePK();
					personRolePk.setUsrUserId(userId);
					personRolePk.setRoleRoleId(roleId);
					SaamfiUserRole personRoleToAdd = new SaamfiUserRole();
					personRoleToAdd.setId(personRolePk);
					saamfiUserRoleRepo.save(personRoleToAdd);
					return true;
				} else {
					throw new Exception("El rol ya se encuentra agregado a esta persona");
				}
			} else {
				throw new Exception("Verifique el documento al cual va a agregar el rol");
			}
		} else {
			throw new Exception("Este rol no pertenece a la institucion " + instId);
		}

	}

	@Override
	public boolean addRoleToUserOper(long instId, long sysId, long userId, long roleId) throws Exception {
		List<SaamfiRole> rolesOper = saamfiRoleRepoo.getRolesByPermTypeId(1, instId);
		SaamfiRole roleAdd = saamfiRoleRepoo.findById(roleId).get();
		if (rolesOper.contains(roleAdd)) {
			SaamfiRole role = null;
			try {
				role = saamfiRoleRepoo.findById(roleId).get();
			} catch (Exception e) {
				throw new Exception("Verifique el documento al cual va a agregar el rol");
			}

			boolean rolInstiId = false;
			List<SaamfiRole> rolInsti = saamfiRoleRepoo
					.getRoleBySaamfiInstitutionInstIdAndSaamfiSystemSysIdAndRoleName(instId, sysId, role.getRoleName());
			if (rolInsti.contains(role)) {
				rolInstiId = true;
			}
			if (rolInstiId == true) {
				if (userId != 0 && role != null) {
					SaamfiUserRole personroleFound = saamfiUserRoleRepo
							.findBySaamfiUserUserIdAndSaamfiRoleRoleId(userId, roleId);
					if (personroleFound == null) {
						SaamfiUserRolePK personRolePk = new SaamfiUserRolePK();
						personRolePk.setUsrUserId(userId);
						personRolePk.setRoleRoleId(roleId);
						SaamfiUserRole personRoleToAdd = new SaamfiUserRole();
						personRoleToAdd.setId(personRolePk);
						saamfiUserRoleRepo.save(personRoleToAdd);
						return true;
					} else {
						throw new Exception("El rol ya se encuentra agregado a esta persona");
					}
				} else {
					throw new Exception("Verifique el documento al cual va a agregar el rol");
				}
			} else {
				throw new Exception("Este rol no pertenece a la institucion " + instId);
			}
		} else {
			throw new Exception("No tiene permisos para agregar este rol");
		}
	}

	/**
	 * @author Ivan Goez, Kevin Zarama, John Camilo Sepulveda - method responsible
	 *         for reading a CSV file that contains the information of the people,
	 *         creates the person assigning him a default role.
	 * @param instid - long that contains the id of the institution
	 * @param is     - ImputStream that contains the imput file
	 * @Return returns a list with the people read
	 */
	/*
	 * @Override
	 *
	 * @Transactional public List<PersonSpeInDTO> csvUpload(InputStream is, long
	 * instId) throws NumberFormatException, Exception { HashMap<String, Long>
	 * idDocumentTypes = new HashMap<>(); Iterable<IdDocumentTypeOutDTO> dtos =
	 * idDocumentTypesDelegate.getTdDocumentTypes(instId);
	 * Iterator<IdDocumentTypeOutDTO> iterator = dtos.iterator(); while
	 * (iterator.hasNext()) { IdDocumentTypeOutDTO dto = iterator.next();
	 * idDocumentTypes.put(dto.getIddoctypeName(),
	 * Long.valueOf(dto.getIddoctypeId())); } try (BufferedReader fileReader = new
	 * BufferedReader(new InputStreamReader(is, "UTF-8")); CSVParser csvParser = new
	 * CSVParser(fileReader,
	 * CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
	 * );) { List<PersonSpeInDTO> personList = new ArrayList<>();
	 * Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	 *
	 * long externalRolId = Long
	 * .parseLong(autparameterrepo.findByParamNameAndInstId(EXTERNAL_ROLE_ID,
	 * instId).getParamValue());
	 *
	 * for (CSVRecord csvRecord : csvRecords) { PersonSpeInDTO person = new
	 * PersonSpeInDTO(); person.setPersIddocument(csvRecord.get("PERS_IDDOCUMENT"));
	 * person.setPersName(csvRecord.get("PERS_NAME"));
	 * person.setPersLastname(csvRecord.get("PERS_LASTNAME"));
	 * person.setPersAddress(csvRecord.get("PERS_ADDRESS"));
	 * person.setPersEmail(csvRecord.get("PERS_EMAIL"));
	 * person.setPersContactnumber(csvRecord.get("PERS_CONTACTNUMBER"));
	 * person.setPersIsactive(csvRecord.get("PERS_ISACTIVE"));
	 * person.setIddoctypeName(csvRecord.get("IDDOCTYPE_NAME")); if
	 * (idDocumentTypes.containsKey(person.getIddoctypeName() + "")) {
	 * person.setIddocumenttypeId(idDocumentTypes.get(csvRecord.get("IDDOCTYPE_NAME"
	 * )).longValue()); } person.setPersPoliticsaccepted("N");
	 * relationUserPerson.put(person.getPersIddocument(),
	 * csvRecord.get("USERPERSON_RELATIONSHIP")); String[] roles =
	 * csvRecord.get("ROLE").split(";"); ArrayList<Long> rolesID = new
	 * ArrayList<Long>(); if (csvRecord.get("ROLE").equals("")) {
	 * rolesID.add(externalRolId); } else { for (int i = 0; i < roles.length; i++) {
	 * rolesID.add(Long.parseLong(roles[i])); } } if
	 * (!idUserRole.containsKey(person.getPersIddocument())) {
	 * idUserRole.put(person.getPersIddocument(), rolesID); personList.add(person);
	 * } } return personList; } catch (IOException e) { throw new
	 * RuntimeException("fail to parse CSV file: " + e.getMessage()); } }
	 */

	@Override
	public void deleteAllRolesFromUser(long instid, long sysid, long userId) {
		Iterable<SaamfiUserRole> roles = saamfiUserRoleRepo.findBySaamfiUserUserId(userId);
		Iterator<SaamfiUserRole> rolesIterator = roles.iterator();
		while (rolesIterator.hasNext()) {
			SaamfiUserRole temp = rolesIterator.next();
			saamfiUserRoleRepo.deleteById(temp.getId());
		}
	}

	@Override
	@Transactional
	public Iterable<SaamfiPermissionnStdOutDTO> getActivePermissions(long personid) {
		ArrayList<SaamfiPermission> permissions = permissionnRepo.findPermissionByUserId(personid);
		// ArrayList<Permissionn> permissions = permissionnRepo.findAll();
		ArrayList<SaamfiPermissionnStdOutDTO> permissionsdto = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		int index = 0;
		for (SaamfiPermissionnStdOutDTO permissionnStdOutDTO : permissionsdto) {
			permissionnStdOutDTO
					.setPermissiontypeId(permissions.get(index++).getSaamfiPermissionType().getPermtypeId());
		}
		return permissionsdto;
	}

	@Override
	public Optional<SaamfiUserRole> getFirstUserRoleByRoleId(long roleId) {
		return saamfiUserRoleRepo.findFirstBySaamfiRoleRoleId(roleId);
	}

	@Override
	public Set<SimpleGrantedAuthority> getUserRoles(long userId) {
		Iterable<String> roles = saamfiUserRoleRepo.findPermisionsByUser(userId);

		Set<SimpleGrantedAuthority> personRolesSet = new HashSet<>();
		roles.forEach(role -> {
			personRolesSet.add(new SimpleGrantedAuthority("ROLE_" + role));
		});

		return personRolesSet;
	}

	@Override
	public Set<SimpleGrantedAuthority> getUserRoles(long userId, long instid) {
		Iterable<String> roles = saamfiUserRoleRepo.findPermisionsByUserAndInstId(userId, instid);

		Set<SimpleGrantedAuthority> personRolesSet = new HashSet<>();
		roles.forEach(role -> {
			personRolesSet.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		return personRolesSet;
	}

	@Override
	public Set<SimpleGrantedAuthority> getUserRolesSystemInstitution(String username, long sysid, long inst) {
		Iterable<String> roles = saamfiUserRoleRepo.findPermisionsByUserUserameAndSystIdAndInst(username, sysid, inst);

		Set<SimpleGrantedAuthority> personRolesSet = new HashSet<>();
		roles.forEach(role -> {
			personRolesSet.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		return personRolesSet;
	}

	@Override
	public Set<SimpleGrantedAuthority> getUserRoles(String username, long instid) {
		Iterable<String> roles = saamfiUserRoleRepo.findPermisionsByUserUserameAndInstId(username, instid);

		Set<SimpleGrantedAuthority> personRolesSet = new HashSet<>();
		roles.forEach(role -> {
			personRolesSet.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		return personRolesSet;
	}

	@Override
	public Iterable<SaamfiUserRole> getUserRolesAll(long userId) {
		return saamfiUserRoleRepo.findBySaamfiUserUserId(userId);
	}

	@Override
	public SaamfiRolesSpeOutDTO getUserRolesById(long userId) {
		List<SaamfiRole> rolees = saamfiRoleRepoo.getRolesByPersonId(userId);
		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE.asRoleeStdOutDTO(rolees);
		SaamfiRolesSpeOutDTO rolesSpeOutDTO = new SaamfiRolesSpeOutDTO();
		rolesSpeOutDTO.setRoles(rolesDTO);
		return rolesSpeOutDTO;
	}

	@Override
	public boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			return true;
		}
		return false;
	}

	public boolean isSuperAdminRol(long instId, long rol) throws Exception {
		List<SaamfiRoleeStdOutDTO> rolList = rolServiceImpl.getRolesGlobal(instId).getRoles();
		boolean isEquals = false;
		for (int i = 0; i < rolList.size() && !isEquals; i++) {
			if (rolList.get(i).getRoleId() == rol) {
				isEquals = true;
			}
		}

		if (!isEquals) {
			throw new Exception("The user is being assigned a super administrador role.");
		}

		return isEquals;
	}

}
