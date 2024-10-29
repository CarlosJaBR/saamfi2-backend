package co.edu.icesi.dev.saamfi.delegate.internal.interfaces;

import java.util.List;

import co.edu.icesi.dev.saamfi.dtos.intin.BannerPersonIntInDTO;
import co.edu.icesi.dev.saamfi.dtos.intin.SystemPersonStdInDTO;

/**
 * Interface to handle requirements of persons data from other projects
 *
 * @author Juan Carlos
 *
 */
public interface PersonDelegate {

	/**
	 * Method to create a new person
	 *
	 * @param instid    institution id where the person will belong
	 * @param persondto Person DTO with the necessary data to create the person
	 * @return personid id of the person created
	 */
	// public long createPerson(long instid, PersonSpeInDTO persondto);

	// public void deletePerson(long instid, long persid);

	public boolean existPersonById(long instid, long persId);

	/**
	 * Method to retrieve person basic data from other project
	 *
	 * @param instId   institution id where the person belongs
	 * @param personid id of the person to retrieve
	 * @return Person DTO with the necessary data from a person
	 */
	public BannerPersonIntInDTO getPersonByDocument(long instId, String persDocument);

	BannerPersonIntInDTO getPersonById(long instid, long personid, String token);

	public SystemPersonStdInDTO getPersonByIdInternal(long instid, long personid);

	List<SystemPersonStdInDTO> getPersons(long instid);

	public SystemPersonStdInDTO getPersonsByUserId(long persid, long instid);

	// public void modifyPerson(long instid, long persid, PersonSpeInDTO2
	// userPerson);

	// public String sendEmailRecovey(long instid, SaamfiUserEmailStdInDTO
	// userPerson);

}
