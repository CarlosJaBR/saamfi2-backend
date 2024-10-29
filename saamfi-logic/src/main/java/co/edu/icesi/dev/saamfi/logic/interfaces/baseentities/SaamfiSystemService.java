package co.edu.icesi.dev.saamfi.logic.interfaces.baseentities;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiSystemStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;

public interface SaamfiSystemService {

	public SaamfiSystem getSystem(long sysid) throws Exception;

	public SaamfiSystem getSystemByInstitutionId(long sysid, long instid) throws Exception;

	public SaamfiSystem addSystem(SaamfiSystemStdInDTO sys) throws Exception;

	public SaamfiSystem editSystem(long sysid, SaamfiSystemStdInDTO sys) throws Exception;

	public SaamfiSystem deleteSystem(long sysid) throws Exception;

	public List<SaamfiSystem> getSystemsByInstitution(long parseLong);

	public List<SaamfiSystem> getAllSystems();
	
	public List<SaamfiSystem> getAllSystemsByUserId(long userId);
}