package co.edu.icesi.dev.saamfi.logic.implementation.authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserChangePassSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserProfileInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiRolesSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserCodeDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserProfileOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserEmailStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserrStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmUser;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmUserPK;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.ExpirationCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.EmailSenderService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserRoleService3;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiUserRoleService;
import co.edu.icesi.dev.saamfi.mappers.RegisterMapper;
import co.edu.icesi.dev.saamfi.mappers.UserMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiInstitutionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiParameterRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystemRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystmUserRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiUserRepo;

@Service
public class SaamfiUserServiceImpl implements SaamfiUserService {

	public static String VISITOR_ROL = "Externo";
	public static String MI_ROL = "Miembro Institucion";
	/**
	 * Refers to a institution member in the Rolee table from database
	 */
	public static long MI_ROL_LONG = 8;

	@Autowired
	private SaamfiInstitutionRepo institutionRepo;
	@Autowired
	private SaamfiSystemRepo systemRepo;
	@Autowired
	private SaamfiUserRepo userRepo;
	@Autowired
	private SaamfiUserRoleService3 userPersonService;
	@Autowired
	private SaamfiSystmUserRepo saamfiSystmUserRepo;
	@Autowired
	private SaamfiUserRoleService personRoleService;
	private EmailSenderService emailSenderService;
	@Autowired
	private SaamfiParameterRepo saamfiParameterRepo;
	@Autowired
	private SaamfiSystmUserRepo systmUserRepo;

	/**
	 * @param emailSenderService the emailSenderService to set
	 */
	@Autowired
	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	/**
	 * @author Miguel Romero, Angie Cordoba, Alejandra Gonzalez
	 * @Method responsible of creatinr a passwordhash.
	 * @Param: pwd is the password
	 * @Param: String with the new password in the DB
	 */
	public static String getPasswordHash(String pwd) {
		try {
			Random random = new Random();
			random.setSeed(152389161l);
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			String myHash = DatatypeConverter.printHexBinary(hash).toUpperCase();

			return myHash;
		} catch (Exception e) {

			return e.getMessage();
		}
	}

	/*
	 * public SaamfiUserServiceImpl(SaamfiUserRepo userrRepo, SaamfiInstitutionRepo
	 * institutionRepo,
	 * SaamfiSystemRepo systemRepo, SaamfiSystmUserRepo saamfiSystmUserRepo,
	 * SaamfiUserRoleService personRoleService, SaamfiUserRoleServiceImpl3
	 * userPersonService,EmailSenderService emailSenderService,SaamfiParameterRepo
	 * saamfiParameterRepo ) {
	 * this.userRepo = userrRepo;
	 * this.institutionRepo = institutionRepo;
	 * this.saamfiSystmUserRepo = saamfiSystmUserRepo;
	 * this.systemRepo = systemRepo;
	 * this.personRoleService = personRoleService;
	 * this.userPersonService = userPersonService;
	 * this.emailSenderService=emailSenderService;
	 * this.saamfiParameterRepo=saamfiParameterRepo;
	 * }
	 */

	/**
	 * @author Miguel Romero, Angie Cordoba, Alejandra Gonzalez
	 * @throws IOException
	 * @throws NoResultException
	 * @throws MessagingException
	 * @throws NoSuchAlgorithmException
	 * @Method responsible of sending an email to a visitor to recover the acces to
	 *         the account and change the password in the DB.
	 * @Param: instid is the id of the institution to which the person belongs
	 * @Param: PersonEmailStdInDTO is the Person whom I will send the password
	 */
	@Override
	@Transactional
	public void accountRecoveryGenerateCode(long instid, SaamfiUserEmailStdInDTO user)
			throws IOException, NoResultException, MessagingException {
		SaamfiUser userFound = userRepo.findByUserUsername(user.getUserUsername());
		Integer timeExpiration = 15;
		if (userFound != null) {
			String key = generateKey(userFound.getUserDocumentId().length());
			userFound.setUserActiveDoubleFactor(key);
			System.out.println(instid);
			SaamfiParameter param = saamfiParameterRepo.findCodeExpirationParameterByInstitutionId(instid);
			if (param != null) {
				timeExpiration = Integer.parseInt(param.getParamValue());
			}
			LocalDateTime currentDate = LocalDateTime.now();
			LocalDateTime expirationDate = currentDate.plusMinutes(timeExpiration);
			Timestamp expirationTimestamp = Timestamp.valueOf(expirationDate);
			userFound.setUserActiveFactorExpDatime(expirationTimestamp);
			userRepo.save(userFound);

			emailSenderService.sendEmail(userFound.getUserEmail(), key, param.getParamValue());
		}
	}

	@Override
	public Boolean accountValidateGenerateCode(SaamfiUserCodeDTO user)
			throws ExpirationCodeException, BadCodeException {
		SaamfiUser userFound = userRepo.findByUserEmail(user.getUserEmail());
		if (userFound != null) {
			if (userFound.getUserActiveDoubleFactor().equals(user.getCodeValidePassword())) {
				Timestamp currentDate = new Timestamp(System.currentTimeMillis());
				if (userFound.getUserActiveFactorExpDatime().after(currentDate)) {
					return true;
				} else {
					throw new ExpirationCodeException();
				}
			} else {
				throw new BadCodeException();
			}

		}
		return false;

	}

	@Override
	public String generateKey(int number) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < number; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public List<SaamfiUser> findAllUsersByInsAndSys(long instid, long sysid) {
		List<SaamfiUser> list = userRepo.findAllBySaamfiInstitutionAndSaamfiSystem(instid, sysid);
		return list;
	}

	@Override
	public List<SaamfiUser> findAllUsersByIns(long instid) {
		List<SaamfiUser> list = userRepo.findAllBySaamfiInstitution(instid);
		return list;
	}

	@Override
	public void addRolesToUserLong(long persId, ArrayList<Long> roles) {
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				SaamfiRolesSpeOutDTO it = personRoleService.getUserRolesById(persId);
				boolean saveIt = true;
				for (int k = 0; k < it.getRoles().size(); k++) {
					if (it.getRoles().get(k).getRoleId() == roles.get(i)) {
						saveIt = false;
						break;
					}
				}
				if (saveIt) {
					SaamfiUserRole perRole = new SaamfiUserRole();
					SaamfiUserRolePK pk = new SaamfiUserRolePK();
					pk.setUsrUserId(persId);
					pk.setRoleRoleId(roles.get(i));
					perRole.setId(pk);
					personRoleService.addPersonRole(perRole);
				}
			}
		}
	}

	@Override
	public void addRolesToUserString(long persId, ArrayList<String> roles) {
		ArrayList<Long> rolesReady = new ArrayList<Long>();
		for (int i = 0; i < roles.size(); i++) {
			rolesReady.add(Long.parseLong(roles.get(i)));
		}
		addRolesToUserLong(persId, rolesReady);
	}

	/**
	 * @author Miguel Romero, Angie Cordoba, Alejandra Gonzalez
	 * @throws NoResultException
	 * @throws NoPasswordsEqualException
	 * @Method responsible of changind the password for a visitor.
	 * @Param: instid is the id of the institution to which the person belongs
	 * @Param: PersonPasswordStdInDTO is the Person whom I will change the password
	 */

	@Override
	public SaamfiUserPasswordSpeInDTO changePasswordRecovery(SaamfiUserChangePassSpeInDTO user)
			throws NoResultException, NoPasswordsEqualException {
		SaamfiUser userFound = userRepo.findByUserUsername(user.getUserUsername());
		if (userFound != null) {
			if (user.getUserPassword().equals(user.getUserPasswordConfirmation())) {
				String password = getPasswordHash(user.getUserPassword());
				userFound.setUserPassword(password);
				userRepo.save(userFound);
			} else {
				throw new NoPasswordsEqualException();
			}
		} else {
			throw new NoResultException();
		}
		SaamfiUserPasswordSpeInDTO userModified = new SaamfiUserPasswordSpeInDTO();
		userModified.setUserId(userFound.getUserId());
		userModified.setUserPassword(userFound.getUserPassword());
		return userModified;
	}

	/**
	 * <b>Name:</b> createUserr<br>
	 * This method is in charge of create a new user in the database and call the
	 * delegate for create a person <br>
	 * <b>pre:</b> The userName and persIddocument doesn't exists in the database
	 * <br>
	 *
	 * @param instid:     Id of the institution to which the person and will be
	 *                    associated<br>
	 * @param userPerson: DTO with the data of the new person and user
	 * @param source      TODO validate perrmissions type. when required
	 */
	@Override
	@Transactional
	public long createUser(long instId, long sysId, SaamfiUserStInDTO userPerson, String source) {

		if (userPerson.getUserName() == null || userPerson.getUserEmail() == null) {
			throw new IllegalArgumentException("No se puede crear un usuario con valores nulos");
		}

		if (userPerson.getUserUsername().isEmpty() || userPerson.getUserEmail().isEmpty()
				|| userPerson.getUserName().isEmpty())
			throw new IllegalArgumentException("No se puede crear un usuario con valores vacios");

		if (userRepo.existsByUserUsername(userPerson.getUserName().trim())) {
			throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
		}

		Optional<SaamfiInstitution> instOp = institutionRepo.findById(instId);
		if (instOp.isEmpty())
			throw new IllegalArgumentException("No existe esta institución");
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysId);
		if (sysOp.isEmpty())
			throw new IllegalArgumentException("No existe este sistema");

		String idRoles = this.systemRepo.findById(sysId).get().getSysDefaultLocalRoles();
		RegisterMapper mapper = RegisterMapper.INSTANCE;

		SaamfiUser user = mapper.asUserrEntity(userPerson);
		String username = user.getUserName().trim();
		user.setUserName(username);
		user.setUserIsLockedout("N");
		user.setUserIsActive("admin".equals(source) || "super".equals(source) ? "Y" : "N");
		user.setSaamfiInstitution(instOp.get());

		SaamfiUser newuser = userRepo.save(user);

		SaamfiSystmUser systemUser = new SaamfiSystmUser();
		SaamfiSystmUserPK supk = new SaamfiSystmUserPK();

		supk.setUsrUserId(newuser.getUserId());
		supk.setSystmSysId(sysId);
		systemUser.setId(supk);
		systemUser.setSaamfiSystem(systemRepo.findById(sysId).get());
		systemUser.setSaamfiUser(user);
		saamfiSystmUserRepo.save(systemUser);
		if (idRoles != null) {
			String[] roles = idRoles.split(";");

			for (String roleId : roles) {
				SaamfiUserRole perRole = new SaamfiUserRole();
				SaamfiUserRolePK pk = new SaamfiUserRolePK();
				pk.setUsrUserId(newuser.getUserId());
				pk.setRoleRoleId(Long.parseLong(roleId));
				perRole.setId(pk);
				personRoleService.addPersonRole(perRole);
			}
		}
		return newuser.getUserId();
	}

	@Override
	public long createUser(long instId, SaamfiNewuserSdtInDTO userPerson) {

		if (userPerson.getUserName() == null || userPerson.getUserEmail() == null) {
			throw new IllegalArgumentException("No se puede crear un usuario con valores nulos");
		}

		if (userPerson.getUserUsername().isEmpty() || userPerson.getUserEmail().isEmpty()
				|| userPerson.getUserName().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un usuario con valores vacios");

		}

		SaamfiUser existingUseruserName = userRepo.findByUserUsername(userPerson.getUserUsername().trim());
		if (existingUseruserName != null) {
			throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
		}

		if (userRepo.existsByUserUsername(userPerson.getUserName().trim())) {
			System.out.println("entre");
			throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
		}

		SaamfiUser existingEmailUser = userRepo.findByUserEmail(userPerson.getUserEmail().trim());

		if (existingEmailUser != null) {
			System.out.println("entre");
			throw new IllegalArgumentException("El correo electrónico ya está en uso");
		}

		if (userRepo.existsByUserEmail(userPerson.getUserEmail().trim())) {
			System.out.println("entre");
			throw new IllegalArgumentException("El email del usuario ya esta en uso");

		}

		Optional<SaamfiInstitution> instOp = institutionRepo.findById(instId);
		if (instOp.isEmpty())
			throw new IllegalArgumentException("No existe esta institución");

		UserMapper mapper = UserMapper.INSTANCE;

		SaamfiUser user = mapper.asNewSaamfiUser(userPerson);
		user.setSaamfiInstitution(instOp.get());
		String username = user.getUserName().trim();
		user.setUserName(username);
		user.setUserIsLockedout("Y");
		user.setUserIsActive("N");

		SaamfiUser newuser = userRepo.save(user);
		return newuser.getUserId();
	}

	@Override
	@Transactional
	public SaamfiUserProfileOutDTO modifyProfile(SaamfiUserProfileInDTO profile) throws NoResultException {
		SaamfiUserProfileOutDTO user = new SaamfiUserProfileOutDTO();
		SaamfiUser userFound = userRepo.findByUserId(profile.getUserId());
		if (userFound != null) {
			userFound.setUserName(profile.getUserName());
			userFound.setUserLastname(profile.getUserLastname());
			userFound.setUserEmail(profile.getUserEmail());
			userFound.setUserPhone(profile.getUserPhone());
			if (profile.getUserPassword() != null && !profile.getUserPassword().isEmpty()) {
				String password = getPasswordHash(profile.getUserPassword());
				userFound.setUserPassword(password);
			}
			userRepo.save(userFound);
			user.setUserName(userFound.getUserName());
			user.setUserLastname(userFound.getUserLastname());
			user.setUserEmail(userFound.getUserEmail());
			user.setUserPhone(userFound.getUserPhone());
			return user;
		} else {
			throw new NoResultException();
		}
	}

	@Override
	public SaamfiUserProfileOutDTO getDataProfile(Long userId) throws NoResultException {
		SaamfiUserProfileOutDTO user = new SaamfiUserProfileOutDTO();
		SaamfiUser userFound = userRepo.findByUserId(userId);
		if (userFound != null) {
			user.setUserName(userFound.getUserName());
			user.setUserLastname(userFound.getUserLastname());
			user.setUserEmail(userFound.getUserEmail());
			user.setUserPhone(userFound.getUserPhone());

			return user;
		} else {
			throw new NoResultException();
		}

	}

	@Override
	@Transactional
	public void deleteUser(long userId) throws Exception {

		System.out.println("ENTROOO");
		Optional<SaamfiUser> userOp = userRepo.findById(userId);
		if (userOp.isEmpty())
			throw new Exception("User doesn't exist");

		Iterator<SaamfiUserRole> userPersons = userPersonService.findAllByUserId(userId).iterator();

		while (userPersons.hasNext()) {
			userPersonService.deleteAutUserRol(userPersons.next().getId());
		}
		SaamfiUser user = userOp.get();
		List<SaamfiSystmUser> systemUsers = systmUserRepo.findBySaamfiUser(user);
		for (SaamfiSystmUser sysU : systemUsers) {
			systmUserRepo.delete(sysU);
		}

		userRepo.deleteById(userId);
	}

	@Override
	public SaamfiUser findByUsername(String username) throws Exception {
		try {
			return userRepo.findByUserUsername(username);

		} catch (Exception e) {
			throw new Exception("User doesnt Exist");
		}
	}

	public Iterable<SaamfiUserRole> getRolesbyId(long id) {
		return personRoleService.getUserRolesAll(id);
	}

	@Override
	public SaamfiNewuserSdtInDTO getUserById(long userid) {

		SaamfiUser myUser = userRepo.findById(userid).get();

		SaamfiNewuserSdtInDTO user = UserMapper.INSTANCE.asNewUserStdDTO(myUser);
		// llamando al id
		user.setUserInstitution(myUser.getSaamfiInstitution().getInstId());

		return user;

	}

	@Override
	@Transactional
	public SaamfiUser getUser(String userName, long instid) {
		SaamfiUser user = userRepo.findByUserUsername(userName);
		return user;
	}

	@Override
	public List<SaamfiUserrStdOutDTO> getUsers(long instid) {

		return UserMapper.INSTANCE.asListUserrStdOutDTO(userRepo.findAllBySaamfiInstitutionInstId(instid));

	}

	@Override
	public SaamfiUser modifyUser(long userId, SaamfiNewuserSdtInDTO userDto) throws Exception {

		// Usuario a retornar
		SaamfiUser user = userRepo.findByUserId(userId);
		System.out.println("ENTREEEEE");
		System.out.println(userDto);
		try {
			user = userRepo.findById(userId).get();
		} catch (Exception e) {
			throw new Exception("User doesn't exist");
		}

		if (userDto.getUserUsername().equals(""))
			throw new Exception("User name empty!");

		if (userDto.getUserEmail().equals(""))
			throw new Exception("email empty!");

		if (userRepo.findByUserUsername(userDto.getUserUsername()) != null) {
			if (userRepo.findByUserUsername(userDto.getUserUsername()).getUserId() != userId)
				throw new Exception("username is already in use!");
		}

		System.out.println();
		// set campos in dto
		user.setUserUsername(userDto.getUserUsername());
		user.setUserDocumentId(userDto.getUserDocumentId());
		user.setUserName(userDto.getUserName());
		user.setUserLastname(userDto.getUserLastname());
		user.setUserIsActive(userDto.getUserIsActive());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPhone(userDto.getUserPhone());

		userRepo.save(user);
		return user;
	}

	@Override
	public boolean validateUser(String userName, String password, long instid, long sysid) {
		boolean validUser = validateUser(userName, instid, sysid);
		if (!validUser) {
			return false;
		}
		SaamfiUser user = userRepo.findByUserUsername(userName);

		boolean passOk = user.getUserPassword().equals(password);
		return passOk;
	}

	@Override
	// TODO validate last password
	public void changePassword(long instid, SaamfiUserPasswordSpeInDTO userPerson) {
		SaamfiUser userFound = userRepo.findByUserId(userPerson.getUserId());
		String password = getPasswordHash(userPerson.getUserPassword());
		userFound.setUserPassword(password);
		userRepo.save(userFound);
	}

	@Override
	public boolean validateUser(String username, long instid, long sysId) {
		List<SaamfiSystem> systems = systemRepo.findByInstitution(instid);
		List<SaamfiSystem> exist = systems.stream().filter((SaamfiSystem sys) -> sys.getSysId() == sysId)
				.collect(Collectors.toList());
		if (exist.size() < 1) {
			return false;
		}
		SaamfiSystem system = exist.get(0);
		String allLog = system.getSysAllowAllInstUsersLogin();
		boolean all = allLog == null ? false : allLog.equals("Y");
		SaamfiUser user = null;
		if (all) {
			user = userRepo.findByUserUsernameAndSaamfiInstitutionInstId(username, instid);
		} else {
			user = userRepo.findByUsernameAndInstitutionAndSystem(username, instid, sysId);
		}
		return user != null && user.getUserIsActive().equals("Y");
	}

	@Override
	public void modifyUser(long userId, SaamfiUserStInDTO userDto) throws Exception {
		// TODO Auto-generated method stub

	}
}
