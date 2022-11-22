package edu.miu.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import edu.miu.notification.DTO.EmailDetails;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class GmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;

    public String sendSimpleMail(){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("waa.test.pro@gmail.com");
            helper.setTo("silvana.nazih@gmail.com");
            helper.setSubject("SUBJECT1");
            helper.setText("details.getMsgBody()", true);
            javaMailSender.send(mimeMessage);
            return "messave";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
