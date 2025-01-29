package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.exceptions.MailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to, String subject, String text) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String encodedSubject = MimeUtility.encodeText(subject, "UTF-8", "B");

            helper.setTo(to);
            helper.setSubject(encodedSubject);
            helper.setText(text, true); // Set body as HTML
            mailSender.send(message);
        }catch(Exception e){
            throw new MailException("An error occurred in sending mail");
        }
    }
}

