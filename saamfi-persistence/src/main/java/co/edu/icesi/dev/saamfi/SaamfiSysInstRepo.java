package co.edu.icesi.dev.saamfi;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInst;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInstPK;

/**
 * SaamfiSysInstRepo
 */
public interface SaamfiSysInstRepo extends CrudRepository<SaamfiSystmInst, SaamfiSystmInstPK> {

}