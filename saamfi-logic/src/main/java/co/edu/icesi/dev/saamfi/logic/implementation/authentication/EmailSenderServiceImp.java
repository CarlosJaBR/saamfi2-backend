package co.edu.icesi.dev.saamfi.logic.implementation.authentication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import co.edu.icesi.dev.saamfi.logic.interfaces.authentication.EmailSenderService;

@Service
public class EmailSenderServiceImp implements EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String email, String code, String expirationTime) throws MessagingException, IOException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:/emailTemplate.html");
		String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		StringBuilder htmlBuilder = new StringBuilder(htmlContent);
		int index = htmlBuilder.indexOf("%s");
		if (index != -1) {
			htmlBuilder.replace(index, index + 2, code);
		}
		index = htmlBuilder.indexOf("%d");
		if (index != -1) {
			htmlBuilder.replace(index, index + 2, expirationTime);
		}
		mimeHelper.setText(htmlBuilder.toString(), true);

		mimeHelper.setText(htmlBuilder.toString(), true);
		mimeHelper.setTo(email);
		mimeHelper.setSubject("Restablecimiento de contraseña");

		mailSender.send(mimeMessage);

	}

}
