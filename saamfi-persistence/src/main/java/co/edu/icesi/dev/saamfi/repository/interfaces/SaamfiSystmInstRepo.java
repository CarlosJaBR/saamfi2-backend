package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInst;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInstPK;

public interface SaamfiSystmInstRepo extends CrudRepository<SaamfiSystmInst, SaamfiSystmInstPK> {
	
	public Optional<SaamfiSystmInst> findBySaamfiSystemAndSaamfiInstitution(SaamfiSystem sys, SaamfiInstitution inst);
	
	public List<SaamfiSystmInst> findAllBySaamfiSystem(SaamfiSystem sys);

}
