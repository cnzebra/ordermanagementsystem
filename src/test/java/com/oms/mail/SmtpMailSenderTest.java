package com.oms.mail;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RunWith(MockitoJUnitRunner.class)
public class SmtpMailSenderTest {

	@InjectMocks
	private SmtpMailSender smtpMailSender = new SmtpMailSender();

	@Mock
	private JavaMailSender javaMailSender;

	@Mock
	MimeMessage message;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void sendTest() throws MessagingException {

		when(javaMailSender.createMimeMessage()).thenReturn(message);
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("Welcome");
		helper.setTo("to");
		helper.setText("nisum", true);
		doNothing().when(javaMailSender).send(message);
		smtpMailSender.send("abc@nisum.com", "Welcome", "nisum");
	}

}
