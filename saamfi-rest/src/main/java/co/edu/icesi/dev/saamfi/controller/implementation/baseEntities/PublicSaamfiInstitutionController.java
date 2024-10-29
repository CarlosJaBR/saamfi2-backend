package co.edu.icesi.dev.saamfi.controller.implementation.baseEntities;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.ListOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiSystemStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiInstitutionService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import co.edu.icesi.dev.saamfi.mappers.SystemMapper;

@RestController
@RequestMapping("/public/institutions")
@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept, Authorization")
public class PublicSaamfiInstitutionController {

    @Autowired
    private SaamfiInstitutionService institutionService;
    @Autowired
    private SaamfiSystemService service;

    @GetMapping("")
    public ListOutDTO<InstitutionSpecOutDTO> findAllInstitutions() throws NoResultException {
        ListOutDTO<InstitutionSpecOutDTO> respOutDTO = new ListOutDTO<>();
        List<InstitutionSpecOutDTO> inst = this.institutionService.getInstitutions();
        respOutDTO.setElements(inst);
        return respOutDTO;
    }

    @GetMapping("/{instId}/systems")
    public ListOutDTO<SaamfiSystemStdOutDTO> getSystems(@PathVariable String instId) {
        List<SaamfiSystem> systems = new ArrayList<>();
        if (instId != null && !instId.isEmpty()) {
            systems = service.getSystemsByInstitution(Long.parseLong(instId));
        } else {
            systems = service.getAllSystems();
        }

        List<SaamfiSystemStdOutDTO> systemStdOutDTOs = SystemMapper.INSTANCE.asListSystemStdOutDTO(systems);
        ListOutDTO<SaamfiSystemStdOutDTO> dtos = new ListOutDTO<>(systemStdOutDTOs);

        return dtos;
    }

}
