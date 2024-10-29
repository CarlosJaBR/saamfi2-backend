package co.edu.icesi.dev.saamfi.logic.interfaces.authentication;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserChangePassSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserPasswordSpeInDTO;
import co.edu.icesi.dev.saamfi.dtos.spein.SaamfiUserProfileInDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserCodeDTO;
import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiUserProfileOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiNewuserSdtInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserEmailStdInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiUserStInDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiUserrStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiUser;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.ExpirationCodeException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.implementation.authentication.NoPasswordsEqualException;

/**
 * interface to define user Service methods
 *
 * @author Luis Miguel Paz
 *
 */
public interface SaamfiUserService {

	public void addRolesToUserLong(long persId, ArrayList<Long> roles);

	public void addRolesToUserString(long persId, ArrayList<String> roles);

	/**
	 * Create a user and call createPerson of personDelegate
	 *
	 * @param instid     Id of the institution to which the person and will be
	 *                   associated
	 * @param userPerson DTO with the data of the new person and user
	 * @return
	 */
	public long createUser(long instid, long sysid, SaamfiUserStInDTO userPerson, String source);

	public long createUser(long instid, SaamfiNewuserSdtInDTO userPerson);

	public void deleteUser(long userId) throws Exception;

	/**
	 * Find all users in the database.
	 *
	 * @return Iterable with all the users retrieved from the database
	 */

	public SaamfiUser findByUsername(String username) throws Exception;

	/**
	 * Retrieves a user from the database using its user name
	 *
	 * @param username user name of the user to retrieve
	 * @return Userr object with the data of the
	 */
	public SaamfiUser getUser(String username, long instid);

	public List<SaamfiUserrStdOutDTO> getUsers(long instid);
	// public void modifyUser(long userId, SaamfiUserSpeInDTO userDto) throws
	// Exception;

	/**
	 * Method to validate the username and password against the database
	 *
	 * @param username user name to validate against the database users
	 * @param password password of the user to validate
	 * @param instid   institution identifier
	 * @return
	 */
	public boolean validateUser(String username, String password, long instid, long sysId);

	/**
	 * @author Miguel Romero, Angie Cordoba, Alejandra Gonzalez
	 * @Method responsible of sending an email to a visitor to recover the acces to
	 *         the account and change the password in the DB.
	 * @Param: instid is the id of the institution to which the person belongs
	 * @Param: PersonEmailStdInDTO is the Person whom I will send the password
	 */

	/**
	 * @author Miguel Romero, Angie Cordoba, Alejandra Gonzalez
	 * @throws IOException
	 * @throws NoResultException
	 * @throws MessagingException
	 * @throws NoSuchAlgorithmException
	 * @Method responsible of sending an email to a visitor to recover the acces to
	 *         the account and change the password in the DB.
	 * @Param: instid is the id of the institution to which the person belongs
	 * @Param: PersonEmailStdInDTO is the Person whom I will send the password
	 */
	public void accountRecoveryGenerateCode(long instid, SaamfiUserEmailStdInDTO user)
			throws IOException, NoResultException, MessagingException;

	public String generateKey(int number);

	public Boolean accountValidateGenerateCode(SaamfiUserCodeDTO user) throws ExpirationCodeException, BadCodeException;

	/**
	 * @author Carolina Pasuy Pinilla
	 * @throws NoResultException
	 * @throws NoPasswordsEqualException
	 * @Method responsible of changind the password for a visitor.
	 * @Param: instid is the id of the institution to which the person belongs
	 * @Param: PersonPasswordStdInDTO is the Person whom I will change the password
	 */
	public SaamfiUserPasswordSpeInDTO changePasswordRecovery(SaamfiUserChangePassSpeInDTO user)
			throws NoResultException, NoPasswordsEqualException;

	void changePassword(long instid, SaamfiUserPasswordSpeInDTO userPerson);

	SaamfiNewuserSdtInDTO getUserById(long userid);

	public boolean validateUser(String username, long instid, long sysId);

	SaamfiUserProfileOutDTO modifyProfile(SaamfiUserProfileInDTO profile) throws NoResultException;

	SaamfiUserProfileOutDTO getDataProfile(Long userId) throws NoResultException;

	void modifyUser(long userId, SaamfiUserStInDTO userDto) throws Exception;

	public List<SaamfiUser> findAllUsersByInsAndSys(long instid, long sysid);

	SaamfiUser modifyUser(long userId, SaamfiNewuserSdtInDTO userDto) throws Exception;

	List<SaamfiUser> findAllUsersByIns(long instid);

}
