package co.edu.icesi.dev.saamfi.controller.implementation.authentication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.PublicSaamfiUserController;
import co.edu.icesi.dev.saamfi.controller.security.JwtTokenProvider;
import co.edu.icesi.dev.saamfi.controller.security.UsernamePasswordInstitutionAutenticationToken;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.spein.JwtAuthenticationRespInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiLoginRequestInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiApiResponseOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiLoginFullSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import co.edu.icesi.dev.saamfi.mappers.AuthenticationMapper;
import co.edu.icesi.dev.saamfi.mappers.UserMapper;

/**
 * Class to handle http authorization request
 *
 * @author Luis Miguel Paz
 *
 */
@RestController
@RequestMapping("/public/institutions/{instid}/systems/{sysid}/users")
@CrossOrigin(origins = "*")

public class PublicSaamfiUserControllerImpl implements PublicSaamfiUserController {

	public static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

	@Autowired
	private SaamfiUserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private SaamfiSystemService systemService;

	public PublicSaamfiUserControllerImpl(SaamfiUserService userService, AuthenticationManager authenticationManager,
			JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userService = userService;
	}

	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestBody SaamfiLoginRequestInDTO loginRequest,
			@PathVariable long instid, @PathVariable long sysid) throws Exception {
		ArrayList<SimpleGrantedAuthority> data = new ArrayList<SimpleGrantedAuthority>();
		data.add(new SimpleGrantedAuthority("instid:" + instid));
		data.add(new SimpleGrantedAuthority("sysid:" + sysid));
		String usernameFront = loginRequest.getUsername().trim();
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usernameFront, loginRequest.getPassword(), data));
		final String jwt = tokenProvider.generateToken(authentication, instid, sysid);
		SaamfiSystem system = systemService.getSystem(sysid);

		final SaamfiUser user = userService.getUser(usernameFront, instid);
		final JwtAuthenticationRespInDTO response = new JwtAuthenticationRespInDTO(jwt);
		final SaamfiLoginFullSpeOutDTO loginOut = AuthenticationMapper.INSTANCE.asLoginSpeOutDTO(user, response);
		loginOut.setSystemHomePage(system.getSysRemoteLandingPage());
		return ResponseEntity.ok(loginOut);
	}

	/**
	 * <b>Name:</b> createUserr<br>
	 * This method is in charge of create a new user in the database and call the
	 * delegate for create a person <br>
	 * Modified: Kliver Daniel Giron <b>pre:</b> The userName and persIddocument
	 * doesn't exists in the database <br>
	 *
	 * @param instid:     Id of the institution to which the person and will be
	 *                    associated<br>
	 * @param userPerson: DTO with the data of the new person and user
	 * @return
	 */
	@Override
	@PostMapping("/add")
	public ResponseEntity<?> createUser(@PathVariable long instid, @PathVariable long sysid,
			@RequestBody SaamfiUserStInDTO userPerson) {
		String pass = ControllerAuthorizationValidator.getPasswordHash(userPerson.getUserPassword());
		userPerson.setUserPassword(pass);

		// validateUser(null, instid, sysid);
		userService.createUser(instid, sysid, userPerson, "public");

		return new ResponseEntity<>(HttpStatus.OK);

	}

	// @Override
	// @PostMapping("/generateCode")
	// public ResponseEntity<String> generateCode(@PathVariable long instid,
	// @RequestBody SaamfiUserEmailStdInDTO user) {
	// try {
	// userService.accountRecoveryGenerateCode(instid, user);
	// }catch(NoResultException nr) {
	// return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// }
	// catch(IOException nr) {
	// return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// }
	// catch(MessagingException nr) {
	// return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// }
	// return new ResponseEntity<>("Enviado correctamente", HttpStatus.OK);
	// }
	@PostMapping("/validate-token")
	public ResponseEntity<Boolean> validateToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok(authentication != null &&
				getAuthorities(authentication).noneMatch(ANONYMOUS_ROLE::equals));
	}

	private static Stream<String> getAuthorities(Authentication authentication) {
		return authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority);
	}

	@Override
	@RequestMapping(value = "/validate-user", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody SaamfiLoginRequestInDTO loginRequest, @PathVariable long instid,
			@PathVariable long sysid) {
		SaamfiUser user = userService.getUser(loginRequest.getUsername().trim(), instid);
		if (ControllerAuthorizationValidator.validateInstitutionAndSystemAndPerson(instid, sysid, user.getUserId())) {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordInstitutionAutenticationToken(loginRequest.getUsername(),
							loginRequest.getPassword(), instid, sysid));
			SaamfiApiResponseOutDTO response;
			if (authentication.isAuthenticated())
				response = new SaamfiApiResponseOutDTO(LocalDateTime.now().toString(), "Authenticated");
			else
				response = new SaamfiApiResponseOutDTO(LocalDateTime.now().toString(), "Bad credentials");
			return ResponseEntity.ok(response);
		}
		SaamfiApiResponseOutDTO response;
		response = new SaamfiApiResponseOutDTO(LocalDateTime.now().toString(), "Bad credentials");
		return ResponseEntity.ok(response);

	}

	@Override
	@PutMapping("/{userId}")
	@PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> modifyUser(@PathVariable long instid, @PathVariable long sysid, @PathVariable long userId,
			@RequestBody SaamfiUserStInDTO userDto) throws Exception {
		// if (ControllerAuthorizationValidator.validateInstitution(instid)) {
		// userService.modifyUser(userId, userDto);
		// return new ResponseEntity<>(HttpStatus.OK);
		// }
		//
		// return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		return null;
	}

	@Override
	@GetMapping("")
	public ResponseEntity<List<?>> getAllUsersByInstAndSys(@PathVariable Long instid, @PathVariable Long sysid) {
		List<SaamfiUser> users = userService.findAllUsersByInsAndSys(instid, sysid);
		List<SaamfiUserSpeOutDTO> dtoUserList = UserMapper.INSTANCE.asListUsersSpecOutDTO(users);

		return new ResponseEntity<>(dtoUserList, HttpStatus.OK);
	}

	@Override
	@GetMapping("/{userid}")
	// @PreAuthorize("hasAnyRole('Admin-Persons')")
	public ResponseEntity<?> getUserById(@PathVariable long instid, @PathVariable long sysid, @PathVariable long userid)
			throws Exception {

		return new ResponseEntity<SaamfiNewuserSdtInDTO>(userService.getUserById(userid), HttpStatus.OK);

	}

}
