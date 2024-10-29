package co.edu.icesi.dev.saamfi.controller.implementation.baseEntities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.interfaces.authorization.AdmSaamfiInstitutionController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.ListOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiInstitutionStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiInstitutionService;

@RestController
@RequestMapping("/adm/institutions/")
@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept, Authorization")
@PreAuthorize("hasAnyRole('Admin-insts-glob','Admin-insts-insts')")
public class AdmSaamfiInstitutionControllerImp implements AdmSaamfiInstitutionController {

	@Autowired
	private SaamfiInstitutionService institutionService;
	@Autowired
	private SaamfiUserService userService;

	@Override
	@PostMapping("/saveInstitution")
	public ResponseEntity<InstitutionSpecOutDTO> saveInstitution(@RequestBody SaamfiInstitutionStdInDTO institution)
			throws Exception {
		if (hasPermission()) {
			InstitutionSpecOutDTO inst;
			try {
				inst = institutionService.saveInstitution(institution);
			} catch (BadRequestDataException e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} catch (NoResultException e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(inst, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

	}

	@Override
	@PutMapping("/editInstitution/{instid}")
	public ResponseEntity<InstitutionSpecOutDTO> editInstitution(@PathVariable("instid") long instid,
			@RequestBody SaamfiInstitution institution) throws NoResultException, BadRequestDataException {
		boolean perm = ControllerAuthorizationValidator.hasPermission("Admin-insts-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", instid);
		if (perm) {
			InstitutionSpecOutDTO inst;
			try {
				inst = institutionService.editInstitution(institution, instid);
			} catch (BadRequestDataException br) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} catch (NoResultException nr) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(inst, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

	}

	@Override
	@DeleteMapping("/deleteInstitution/{instid}")
	public ResponseEntity<String> deleteInstitution(@PathVariable("instid") long instid)
			throws NoResultException, BadRequestDataException {
		boolean perm = ControllerAuthorizationValidator.hasPermission("Admin-insts-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", instid);
		if (perm) {
			try {
				institutionService.deleteInstitution(instid);
			} catch (BadRequestDataException br) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} catch (NoResultException nr) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Fue eliminado correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

	}

	@Override
	@GetMapping("/getInstitution/{instid}")
	public ResponseEntity<InstitutionSpecOutDTO> findInstitutionById(@PathVariable("instid") long instid)
			throws NoResultException, BadRequestDataException {
		InstitutionSpecOutDTO inst;
		try {
			inst = this.institutionService.getInstitutionById(instid);
		} catch (BadRequestDataException br) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (NoResultException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(inst, HttpStatus.OK);
	}

	@GetMapping("/")
	public ListOutDTO<InstitutionSpecOutDTO> findAllInstitutions() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser user = userService.findByUsername(username);
		ListOutDTO<InstitutionSpecOutDTO> respOutDTO = new ListOutDTO<>();

		if (ControllerAuthorizationValidator.hasPermission("Admin-insts-glob")) {
			List<InstitutionSpecOutDTO> inst = this.institutionService.getInstitutions();
			respOutDTO.setElements(inst);
		} else if (ControllerAuthorizationValidator.hasPermission("Admin-insts-insts")) {
			List<InstitutionSpecOutDTO> inst = this.institutionService
					.findInstitutionsByAdminInstitutions(user.getUserId());
			respOutDTO.setElements(inst);
		}

		return respOutDTO;
	}

	private boolean hasPermission() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser loggedUser = userService.findByUsername(username);
		long userInstId = loggedUser.getSaamfiInstitution().getInstId();

		return ControllerAuthorizationValidator.hasPermission("Admin-sys-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", userInstId);
	}
}
