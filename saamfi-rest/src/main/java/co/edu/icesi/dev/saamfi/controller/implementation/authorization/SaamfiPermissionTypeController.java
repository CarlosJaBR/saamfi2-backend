package co.edu.icesi.dev.saamfi.controller.implementation.authorization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.utils.ControllerAuthorizationValidator;
import co.edu.icesi.dev.saamfi.dtos.speout.ListOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionTypeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionTypeService;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adm/institutions/{instid}/systems/{sysid}/permissionType")
public class SaamfiPermissionTypeController {

    @Autowired
    private SaamfiPermissionTypeService permissionTypeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin-permissions-glob','Admin-permissions-sys','Admin-permissions-inst')")
    public ResponseEntity<?> getPermissionType(@PathVariable long sysid, @PathVariable long instid) {

        boolean hasPermission = ControllerAuthorizationValidator.hasPermission("Admin-permissions-glob");
        hasPermission = hasPermission
                || ControllerAuthorizationValidator.hasPermission("Admin-permissions-inst", instid);
        hasPermission = hasPermission
                || ControllerAuthorizationValidator.hasPermission("Admin-permissions-sys", instid, sysid);

        if (hasPermission) {
            List<SaamfiPermissionType> types = permissionTypeService.getPermTypeBySystem(sysid);
            List<SaamfiPermissionTypeStdOutDTO> dtos = AuthorizationMapper.INSTANCE.asPermissiontypesStdOutDTO(types);
            ListOutDTO<SaamfiPermissionTypeStdOutDTO> listOutDTO = new ListOutDTO<>(dtos);
            return ResponseEntity.ok(listOutDTO);

        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
