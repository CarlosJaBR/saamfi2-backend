package co.edu.icesi.dev.saamfi.repository.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.saamfi.entities.SaamfiSystmUser;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmUserPK;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;

public interface SaamfiSystmUserRepo extends CrudRepository<SaamfiSystmUser, SaamfiSystmUserPK> {
	List<SaamfiSystmUser> findBySaamfiUser(SaamfiUser saamfiUser);
}
