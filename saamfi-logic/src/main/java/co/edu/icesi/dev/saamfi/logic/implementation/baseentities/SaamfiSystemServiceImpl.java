package co.edu.icesi.dev.saamfi.logic.implementation.baseentities;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiSystemStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInst;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInstPK;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import co.edu.icesi.dev.saamfi.mappers.SystemMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiInstitutionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystemRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystmInstRepo;

@Service
@Transactional
public class SaamfiSystemServiceImpl implements SaamfiSystemService {

	@Autowired
	private SaamfiSystemRepo systemRepo;
	@Autowired
	private SaamfiInstitutionRepo instRepo;
	@Autowired
	private SaamfiSystmInstRepo systmInstRepo;
	
	@Override
	public List<SaamfiSystem> getSystemsByInstitution(long parseLong) {
	 	List<SaamfiSystem> inter = systemRepo.findByInstitution(parseLong);
	 	return inter;
	}
	
	@Override
	public List<SaamfiSystem> getAllSystems() {
	 	return systemRepo.findAll();
	}
	
	@Override
	public List<SaamfiSystem> getAllSystemsByUserId(long userId) {
		return systemRepo.findByInstitutionsFromSaamfiUser(userId);
	}
	
	@Override
	public SaamfiSystem getSystem(long sysid) throws IllegalArgumentException {
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
		if (sysOp.isEmpty())
			throw new IllegalArgumentException("No se pudo encontrar un sistema con ese ID");
		
		SaamfiSystem sys =  sysOp.get();
//		SaamfiSystemStdOutDTO sysDto = SystemMapper.INSTANCE.asSaamfiSystemStdOutDTO(sys);
		return  sys;
	}
	
	@Override
	public SaamfiSystem getSystemByInstitutionId(long sysid, long instid) throws IllegalArgumentException {
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
		Optional<SaamfiInstitution> instOp = instRepo.findById(instid);
		if (instOp.isEmpty())
			throw new IllegalArgumentException("No se pudo encontrar uns institución con ese ID");
		if (sysOp.isEmpty())
			throw new IllegalArgumentException("No se pudo encontrar un sistema con ese ID");
		
		SaamfiSystem sys = sysOp.get();
		SaamfiInstitution inst = instOp.get();
		
		// check if sys and institute are connected
		Optional<SaamfiSystmInst> sysInstOp = systmInstRepo.findBySaamfiSystemAndSaamfiInstitution(sys, inst);
		if (sysInstOp.isEmpty())
			throw new IllegalArgumentException("Este sistema no pertenece a esta institución");
		
//		SaamfiSystemStdOutDTO sysDto = SystemMapper.INSTANCE.asSaamfiSystemStdOutDTO(sys);
		return  sys;
	}

	@Override
	public SaamfiSystem addSystem(SaamfiSystemStdInDTO sysDto) throws IllegalArgumentException {
		
		System.out.println(sysDto.toString());
		SaamfiSystem sys = SystemMapper.INSTANCE.asSaamfiSystem(sysDto);
		
		
		
		if(sys.getSysName()==null||sys.getSysName().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un sistema sin nombre");
		}
		
		if(sys.getSysRemoteLandingPage()==null || sys.getSysRemoteLandingPage().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un sistema sin una landing page");
		}
		
		if(sys.getSysAllowedAuth()==null || sys.getSysAllowedAuth().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un sistema sin definir los metodos de autenticación");
		}
		
		if(sys.getSysNormalTokenTimeout()==null) {
			throw new IllegalArgumentException("No se puede crear un sistema sin definir el timeout para el token normal del doble factor de autenticación");
		}
		
		if(sys.getSysDoubleFactorLogin()==null || sys.getSysDoubleFactorLogin().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un sistema sin definir el metodo de envio para el doble factor de autenticación en el login");
		}
		
		if(sys.getSysDoubleFactorSpecial()==null || sys.getSysDoubleFactorSpecial().isEmpty()) {
			throw new IllegalArgumentException("No se puede crear un sistema sin definir el metodo de envio para el doble factor de autenticación especial");
		}
		
		if(	
			!(sys.getSysAllowAllInstUsersLogin() ==null || sys.getSysAllowAllInstUsersLogin().equalsIgnoreCase("Y") || sys.getSysAllowedAuth().equalsIgnoreCase("N")) ||
			!(sys.getSysAllowPublicUserCreate() ==null || sys.getSysAllowPublicUserCreate().equalsIgnoreCase("Y") || sys.getSysAllowPublicUserCreate().equalsIgnoreCase("N")) ||
			!(sys.getSysAllowRoleUserCreate() ==null || sys.getSysAllowRoleUserCreate().equalsIgnoreCase("Y") || sys.getSysAllowRoleUserCreate().equalsIgnoreCase("N"))
			
		){
			throw new IllegalArgumentException("Obción no permitida");
		}
		
		
		sys = systemRepo.save(sys);
		
		for(Long instid : sysDto.getSysInstitutions()) {
			SaamfiSystmInst systmInst = new SaamfiSystmInst();
			Optional<SaamfiInstitution> instOp = instRepo.findById(instid);
			if(instOp.isEmpty()) {
				throw new IllegalArgumentException("No se puede crear un sistema: No existe la/s institución seleccionada");
			}
			SaamfiInstitution inst = instOp.get();
			
			SaamfiSystmInstPK pk = new SaamfiSystmInstPK();
			pk.setInstInstId(inst.getInstId());
			pk.setSystmSysId(sys.getSysId());
			systmInst.setId(pk);
			systmInst.setSaamfiInstitution(inst);
			systmInst.setSaamfiSystem(sys);
			systmInstRepo.save(systmInst);
		}
		
		return sys;
	}

	@Override
	public SaamfiSystem editSystem(long sysid, SaamfiSystemStdInDTO sysDto) throws IllegalArgumentException {
		System.out.println("ID "+sysid);
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
		if (sysOp.isEmpty())
			throw new IllegalArgumentException("No se pudo encontrar un sistema con ese ID");
		SaamfiSystem sysOld = sysOp.get();
		
		SaamfiSystem sys = SystemMapper.INSTANCE.asSaamfiSystem(sysDto);
		if(sys.getSysName()==null||sys.getSysName().isEmpty()) {
			throw new IllegalArgumentException("No se puede editar un sistema sin nombre");
		}
		
		if(sys.getSysRemoteLandingPage()==null || sys.getSysRemoteLandingPage().isEmpty()) {
			throw new IllegalArgumentException("No se puede editar un sistema sin una landing page");
		}
		
		if(sys.getSysAllowedAuth()==null || sys.getSysAllowedAuth().isEmpty()) {
			throw new IllegalArgumentException("No se puede editar un sistema sin definir los metodos de autenticación");
		}
		
		if(sys.getSysNormalTokenTimeout()==null) {
			throw new IllegalArgumentException("No se puede editar un sistema sin definir el timeout para el token normal del doble factor de autenticación");
		}
		
		if(sys.getSysDoubleFactorLogin()==null || sys.getSysDoubleFactorLogin().isEmpty()) {
			throw new IllegalArgumentException("No se puede editar un sistema sin definir el metodo de envio para el doble factor de autenticación en el login");
		}
		
		if(sys.getSysDoubleFactorSpecial()==null || sys.getSysDoubleFactorSpecial().isEmpty()) {
			throw new IllegalArgumentException("No se puede editar un sistema sin definir el metodo de envio para el doble factor de autenticación especial");
		}
		sys.setSysId(sysOld.getSysId());
		sys = systemRepo.save(sys);
		
		List<SaamfiSystmInst> sysInsts = systmInstRepo.findAllBySaamfiSystem(sysOld);
		
		for(SaamfiSystmInst systmInst : sysInsts ) {
			Long intsId = systmInst.getSaamfiInstitution().getInstId();
			if(sysDto.getSysInstitutions().contains(intsId)) {
				sysDto.getSysInstitutions().remove(intsId);
			}else {
				systmInstRepo.delete(systmInst);
			}
		}
		
		for(Long instid : sysDto.getSysInstitutions()) {
			SaamfiSystmInst systmInst = new SaamfiSystmInst();
			Optional<SaamfiInstitution> instOp = instRepo.findById(instid);
			if(instOp.isEmpty()) {
				throw new IllegalArgumentException("No se puede crear un sistema: No existe la/s institución seleccionada");
			}
			SaamfiInstitution inst = instOp.get();
			SaamfiSystmInstPK pk = new SaamfiSystmInstPK();
			pk.setInstInstId(inst.getInstId());
			pk.setSystmSysId(sys.getSysId());
			systmInst.setId(pk);
			systmInst.setSaamfiInstitution(inst);
			systmInst.setSaamfiSystem(sys);
			systmInstRepo.save(systmInst);
		}
		
		return sys;
	}

	@Override
	public SaamfiSystem deleteSystem(long sysid) throws IllegalArgumentException {
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
		if (sysOp.isEmpty())
			throw new IllegalArgumentException("No se pudo encontrar un sistema con ese ID");
		
		SaamfiSystem sys = sysOp.get();
		
		systemRepo.delete(sys);
		return sys;
	}

	
}
