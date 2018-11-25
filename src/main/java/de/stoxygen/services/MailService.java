package de.stoxygen.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String subject, String message, String receiver_address) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(receiver_address);
        mail.setFrom("status.stoxygen@googlemail.com");
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }
}
