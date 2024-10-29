package co.edu.icesi.dev.saamfi.controller.implementation.baseEntities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.dtos.speout.ListOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiSystemStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import co.edu.icesi.dev.saamfi.mappers.SystemMapper;

@RestController
@RequestMapping("/public/systems")
@CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept, Authorization")
public class PublicSaamfiSystemController {

    @Autowired
    private SaamfiSystemService service;

    @RequestMapping(method = RequestMethod.GET)
    public ListOutDTO<SaamfiSystemStdOutDTO> getSystems(@RequestParam(name = "inst", required = false) String instId) {
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
    
    @GetMapping("/{sysid}")
    public SaamfiSystemStdOutDTO getSystem(@PathVariable Long sysid) throws Exception {
    	SaamfiSystem sys = service.getSystem(sysid);
    	
    	SaamfiSystemStdOutDTO sysDto = SystemMapper.INSTANCE.asSystemStdOutDTO(sys);
    	return sysDto;
    }

}
