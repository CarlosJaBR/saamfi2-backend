package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;

public interface SaamfiUserRepo extends CrudRepository<SaamfiUser, Long> {

	

	/**
	 * Method to find a user in the database
	 *
	 * @param userName username of the user to search in the database
	 * @param instid   institution identifier
	 * @return
	 */
	public SaamfiUser findByUserUsernameAndSaamfiInstitutionInstId(String userName, long instid);
    public List<SaamfiUser> findAll();
	public List<SaamfiUser> findAllBySaamfiInstitutionInstId(long instid);
	public SaamfiUser findByUserId(long userId);
	public SaamfiUser findByUserEmail(String email);
	public SaamfiUser findByUserUsername(String userUsername);
	public boolean existsByUserUsername(String username);
	

	
	public boolean existsByUserEmail(String email);
	
	
	@Query(value = "SELECT * FROM SAAMFI_USER u WHERE u.inst_inst_id = ?1  AND u.user_id IN (SELECT s.usr_user_id FROM saamfi_systm_user s WHERE s.systm_sys_id = ?2)", nativeQuery = true)
	public List<SaamfiUser> findAllBySaamfiInstitutionAndSaamfiSystem(long instid, long sysid);

	@Query(value = "Select * From Saamfi_user u Where u.user_username = ?1 And u.inst_inst_id = ?2 And u.user_id in (Select s.usr_user_id From saamfi_systm_user s where s.systm_sys_id = ?3)", nativeQuery = true)
	public SaamfiUser findByUsernameAndInstitutionAndSystem(String userName, long instid, long sysId);
	/**
	 * Method to find a user in the database
	 *
	 * @param userDocumentId document id of the user to search in the database
	 * @param instid institution identifier
	 * @return
	 */
	public SaamfiUser findByUserDocumentIdAndSaamfiInstitutionInstId(String userDocumentId, long instid);
	
	@Query(value = "SELECT * FROM SAAMFI_USER u WHERE u.inst_inst_id = ?1 ", nativeQuery = true)
	public List<SaamfiUser> findAllBySaamfiInstitution(long instid);


};