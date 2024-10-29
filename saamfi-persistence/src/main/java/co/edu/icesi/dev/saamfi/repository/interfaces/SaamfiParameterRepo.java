package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;

public interface SaamfiParameterRepo extends CrudRepository<SaamfiParameter, Long> {

	public SaamfiParameter findByParamNameAndSaamfiInstitutionInstId(String paramName, long instId);
	
	public List<SaamfiParameter> findBySaamfiInstitutionInstId(long instId);
	
	@Query("SELECT pa FROM SaamfiParameter pa,SaamfiInstitution i WHERE i.instId =:instId AND pa.saamfiInstitution.instId=:instId AND pa.paramName = 'CodeExpiration'")
	public SaamfiParameter findCodeExpirationParameterByInstitutionId(@Param("instId") Long instId);

}
