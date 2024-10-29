package co.edu.icesi.dev.saamfi.logic.interfaces.authentication;

import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;

public interface SaamfiUserRoleService3 {

	public void addAutUserPerson(SaamfiUserRole newAut);

	public void deleteAutUserRol(SaamfiUserRolePK autUserPersId);

	public boolean deleteAutUserPersonByUseridAndPersid(String userName, long persid);

	Iterable<SaamfiUserRole> findAllByUserId(long userId);

}
