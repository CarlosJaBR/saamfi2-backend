package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;

public interface SaamfiSystemRepo extends CrudRepository<SaamfiSystem, Long> {
    public List<SaamfiSystem> findAll();
    
    @Query(value = "SELECT s.* FROM saamfi_system s WHERE s.sys_id in (SELECT i.systm_sys_id FROM saamfi_systm_inst i WHERE i.inst_inst_id = ?1)", nativeQuery = true)
    public List<SaamfiSystem> findByInstitution(long instId);
    
    @Query(value = "SELECT s.* FROM saamfi_system s WHERE s.sys_id in (SELECT i.systm_sys_id FROM saamfi_systm_inst i, SAAMFI_USER su WHERE i.inst_inst_id = su.inst_inst_id AND su.user_id = ?1)", nativeQuery = true)
    public List<SaamfiSystem> findByInstitutionsFromSaamfiUser(long userId);
    
    
}
