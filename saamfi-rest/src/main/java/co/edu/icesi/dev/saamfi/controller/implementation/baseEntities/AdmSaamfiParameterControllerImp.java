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

import co.edu.icesi.dev.saamfi.controller.interfaces.authentication.SaamfiParameterController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.ParameterSpecOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiParameterService;

@RestController
@RequestMapping("/adm/institutions/{instid}/params")
@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept, Authorization")
@PreAuthorize("hasAnyRole('Admin-insts-glob','Admin-insts-insts')")
public class AdmSaamfiParameterControllerImp implements SaamfiParameterController {

	@Autowired
	private SaamfiParameterService saamfiParameterService;
	@Autowired
	private SaamfiUserService userService;

	@Override
	@PostMapping("/saveParameter")
	public ResponseEntity<ParameterSpecOutDTO> saveParameter(@RequestBody SaamfiParameter parameter,
			@PathVariable("instid") long instid) throws Exception {
		if (hasPermission()) {
			ParameterSpecOutDTO pam;
			try {
				pam = saamfiParameterService.saveParameter(parameter, instid);
			} catch (BadRequestDataException e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(pam, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@PutMapping("/editParameter/{paramid}")
	public ResponseEntity<ParameterSpecOutDTO> editParameter(@RequestBody SaamfiParameter parameter,
			@PathVariable("paramid") long paramid, @PathVariable("instid") long instid) {
		boolean perm = ControllerAuthorizationValidator.hasPermission("Admin-insts-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", instid);
		if (perm) {
			ParameterSpecOutDTO param;
			try {
				param = saamfiParameterService.editParameter(parameter, paramid, instid);
			} catch (BadRequestDataException br) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} catch (NoResultException nr) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(param, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}

	@Override
	@DeleteMapping("/deleteParameter/{paramid}")
	public ResponseEntity<String> deleteParameter(@PathVariable("paramid") long paramid,
			@PathVariable("instid") long instid) {
		boolean perm = ControllerAuthorizationValidator.hasPermission("Admin-insts-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", instid);
		if (perm) {
			try {
				saamfiParameterService.deleteParameter(paramid);
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
	@GetMapping("/getParameter/{paramid}")
	public ResponseEntity<ParameterSpecOutDTO> findParameterById(@PathVariable("paramid") long paramid) {
		ParameterSpecOutDTO param;
		try {
			param = saamfiParameterService.getParameterById(paramid);
		} catch (BadRequestDataException br) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (NoResultException nr) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(param, HttpStatus.OK);
	}

	@Override
	@GetMapping("/")
	public ResponseEntity<List<ParameterSpecOutDTO>> findAllParametersByInstitution(
			@PathVariable("instid") long instid) {
		boolean perm = ControllerAuthorizationValidator.hasPermission("Admin-insts-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", instid);
		if (perm) {
			List<ParameterSpecOutDTO> params;
			try {
				params = saamfiParameterService.getParametersByInstitution(instid);
			} catch (NoResultException nr) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(params, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

	}

	private boolean hasPermission() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser loggedUser = userService.findByUsername(username);
		long userInstId = loggedUser.getSaamfiInstitution().getInstId();

		return ControllerAuthorizationValidator.hasPermission("Admin-sys-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-insts-insts", userInstId);
	}

}
