package co.edu.icesi.dev.saamfi.logic.interfaces.authentication;

import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailSenderService {

	void sendEmail(String email, String code, String expirationTime) throws MessagingException, IOException;

}
