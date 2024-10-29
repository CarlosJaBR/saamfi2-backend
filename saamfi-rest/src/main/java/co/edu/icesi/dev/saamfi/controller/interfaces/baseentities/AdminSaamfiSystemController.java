package co.edu.icesi.dev.saamfi.controller.interfaces.baseentities;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiSystemStdInDTO;

public interface AdminSaamfiSystemController {
	
//	public ResponseEntity<?> findAllSaamfiSystems() throws Exception;
//	public ResponseEntity<?> findAllSaamfiSystemsByInstitutionId(long instid) throws Exception;
//	public ResponseEntity<?> findBySaamfiSystemIdAndInstitutionId(long sysid, long instid) throws Exception;
	public ResponseEntity<?> getSaamfiSystem(long sysid) throws Exception;
	public ResponseEntity<?> getAllSaamfiSystemsByLoggeduser() throws Exception;
	public ResponseEntity<?> addSaamfiSystem(SaamfiSystemStdInDTO sysDto) throws Exception;
	public ResponseEntity<?> editSaamfiSystem(long sysid, SaamfiSystemStdInDTO sysDto) throws Exception;
	public ResponseEntity<?> deleteSaamfiSystem(long sysid) throws Exception;
}
