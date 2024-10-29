package co.edu.icesi.dev.saamfi.controller.implementation.baseEntities;

import java.util.ArrayList;
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

import co.edu.icesi.dev.saamfi.controller.interfaces.baseentities.AdminSaamfiSystemController;
import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.ListOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiSystemFullSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiSystemStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiSystemStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInst;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import co.edu.icesi.dev.saamfi.mappers.SystemMapper;

//TODO Add the HasRole annotation
/**
 * This class is in charge the system's management
 * 
 * @author Santiago Hurtado Solis
 */
@RestController
@RequestMapping("/adm/systems")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('Admin-sys-glob','Admin-sys-inst','Admin-sys-sys')")
public class AdminSaamfiSystemControllerImpl implements AdminSaamfiSystemController {

	@Autowired
	private SaamfiSystemService systemService;
	@Autowired
	private SaamfiUserService userService;

	@Override
	@GetMapping("")
	public ResponseEntity<?> getAllSaamfiSystemsByLoggeduser() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser loggedUser = userService.findByUsername(username);

		List<SaamfiSystem> systems = new ArrayList<>();
		if (ControllerAuthorizationValidator.hasPermission("Admin-sys-glob")) {
			systems = systemService.getAllSystems();
		} else if (ControllerAuthorizationValidator.hasPermission("Admin-sys-inst")) {
			systems = systemService.getAllSystemsByUserId(loggedUser.getUserId());
		}
		List<SaamfiSystemStdOutDTO> systemStdOutDTOs = SystemMapper.INSTANCE.asListSystemStdOutDTO(systems);
		ListOutDTO<SaamfiSystemStdOutDTO> dtos = new ListOutDTO<>(systemStdOutDTOs);
		return ResponseEntity.ok(dtos);
	}

	@Override
	@GetMapping("/{sysid}")
	public ResponseEntity<?> getSaamfiSystem(@PathVariable long sysid) throws Exception {
		SaamfiSystem sys = systemService.getSystem(sysid);
		SaamfiSystemFullSpeOutDTO sysDto = SystemMapper.INSTANCE.asSystemFullSpeOutDTO(sys);
		sysDto.setSysInstitutions(new ArrayList<>());
		for (SaamfiSystmInst sysInst : sys.getSaamfiSystmInsts()) {
			Long instId = sysInst.getSaamfiInstitution().getInstId();
			sysDto.getSysInstitutions().add(instId);
		}

		return ResponseEntity.ok(sysDto);
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<?> addSaamfiSystem(@RequestBody SaamfiSystemStdInDTO sysDto) throws Exception {
		if (!hasPermission()) {
			return new ResponseEntity<String>("No tienes permiso para hacer esto", HttpStatus.FORBIDDEN);
		}

		SaamfiSystem system = systemService.addSystem(sysDto);
		return ResponseEntity.ok("sistema agregado correctamente con id: " + system.getSysId());
	}

	@Override
	@PutMapping("/edit/{sysid}")
	public ResponseEntity<?> editSaamfiSystem(@PathVariable long sysid, @RequestBody SaamfiSystemStdInDTO sysDto)
			throws Exception {
		if (!hasPermission(sysid)) {
			System.out.println("JAJAJJAJAJAJ XD");
			return new ResponseEntity<String>("No tienes permiso para hacer esto", HttpStatus.FORBIDDEN);
		}
		SaamfiSystem system = systemService.editSystem(sysid, sysDto);
		return ResponseEntity.ok("sistema editado correctamente con id: " + system.getSysId());
	}

	@Override
	@DeleteMapping("/delete/{sysid}")
	public ResponseEntity<?> deleteSaamfiSystem(@PathVariable long sysid) throws Exception {
		if (!hasPermission(sysid)) {
			System.out.println("JAJAJJAJAJAJ XD");
			return new ResponseEntity<String>("No tienes permiso para hacer esto", HttpStatus.FORBIDDEN);
		}
		SaamfiSystem system = systemService.deleteSystem(sysid);
		return ResponseEntity.ok("sistema eliminado correctamente con id: " + system.getSysId());
	}

	private boolean hasPermission(long sysid) throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser loggedUser = userService.findByUsername(username);
		long userInstId = loggedUser.getSaamfiInstitution().getInstId();

		return ControllerAuthorizationValidator.hasPermission("Admin-sys-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-sys-inst", userInstId) ||
				ControllerAuthorizationValidator.hasPermission("Admin-sys-sys", userInstId, sysid);
	}

	private boolean hasPermission() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SaamfiUser loggedUser = userService.findByUsername(username);
		long userInstId = loggedUser.getSaamfiInstitution().getInstId();

		return ControllerAuthorizationValidator.hasPermission("Admin-sys-glob") ||
				ControllerAuthorizationValidator.hasPermission("Admin-sys-inst", userInstId);
	}

}
