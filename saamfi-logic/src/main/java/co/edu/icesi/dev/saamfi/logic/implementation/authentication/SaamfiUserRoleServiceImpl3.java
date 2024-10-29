package co.edu.icesi.dev.saamfi.logic.implementation.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.saamfi.entities.SaamfiUserRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiUserRolePK;
import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.SaamfiUserRoleService3;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiUserRoleRepo;

@Service
public class SaamfiUserRoleServiceImpl3 implements SaamfiUserRoleService3 {

	public final static String APPLY_PERMISSIONS_Y = "Y";
	public final static String APPLY_PERMISSIONS_N = "N";
	private SaamfiUserRoleRepo autUserpersonrepo;

	@Autowired
	public SaamfiUserRoleServiceImpl3(SaamfiUserRoleRepo userPersonRepo) {
		autUserpersonrepo = userPersonRepo;
	}

	@Override
	public void addAutUserPerson(SaamfiUserRole newAut) {
		autUserpersonrepo.save(newAut);
	}

	@Override
	public void deleteAutUserRol(SaamfiUserRolePK autUserPersId) {
		autUserpersonrepo.deleteById(autUserPersId);

	}

	@Override
	public boolean deleteAutUserPersonByUseridAndPersid(String userName, long persid) {

		List<SaamfiUserRole> relations = autUserpersonrepo.findBySaamfiUserUserId(persid);

		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getSaamfiUser().getUserName().equals(userName)) {

				autUserpersonrepo.deleteById(relations.get(i).getId());
				return true;
			}

		}

		return false;
	}

	@Override
	public List<SaamfiUserRole> findAllByUserId(long userId) {
		return autUserpersonrepo.findBySaamfiUserUserId(userId);
	}

}
