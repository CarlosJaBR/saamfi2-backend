package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;

public interface SaamfiInstitutionRepo extends CrudRepository<SaamfiInstitution, Long> {

    public List<SaamfiInstitution> findAll();
    
    @Query("SELECT u.saamfiInstitution FROM SaamfiUser u WHERE u.userId = :userId")
    public SaamfiInstitution findByUserId(@Param("userId") long userId);
    
    @Query("SELECT r.saamfiInstitution FROM SaamfiRole r,SaamfiUserRole ur,SaamfiRolePerm rp WHERE ur.saamfiUser.userId=:userId AND "
    		+ "ur.saamfiRole.roleId=r.roleId AND rp.saamfiPermission.permName='Admin-insts-insts' AND rp.saamfiRole.roleId=r.roleId")
    public List<SaamfiInstitution>  findInstitutionsByAdminInstitutions(@Param("userId") long userId);


}
