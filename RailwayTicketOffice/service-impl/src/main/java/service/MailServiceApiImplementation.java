package service;

import com.railwayticket.services_api.MailSenderApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "ebc3d8e7d7bfe0";
        String pass = "423addd5488917";
         if ((username != null) && (username.length() > 0) && (pass != null)
                && (pass.length() > 0)) {

            return new PasswordAuthentication(username, pass);
        }

        return null;
    }
}

public class MailServiceApiImplementation implements MailSenderApi {
    final static Logger logger = Logger.getLogger(MailServiceApiImplementation.class);

    @Value("${spring.mail.username}")
    private String admin;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private String port;

    @Qualifier("getMailSender")
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean SendMessageWithAttchement(String emailFrom, String subject, String message, MultipartFile file) throws MessagingException {
        SmtpAuthenticator authenticator = new SmtpAuthenticator();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.host",host);

        if(!emailFrom.isEmpty() && !subject.isEmpty() && file!=null) {
            Message messageToSend = new MimeMessage(Session
                    .getDefaultInstance(properties, authenticator));
             messageToSend.setFrom(new InternetAddress(emailFrom));
            messageToSend.setSubject(subject);
            messageToSend.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(admin));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            String filename = new FileSystemResource(convert(file)).getFilename();
            assert filename != null;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);

            multipart.addBodyPart(messageBodyPart);

            messageToSend.setContent(multipart);


            Session session = Session.getInstance(properties,authenticator);


            Transport transport = session.getTransport("smtp");
            transport.connect(host, Integer.parseInt(port), admin, password);
            transport.sendMessage(messageToSend, messageToSend.getAllRecipients());
            transport.close();

            logger.info("Send message to admin from " + emailFrom + " " + new Date());
            return true;
        }else{
            logger.error("Message not send. Illegal parametrs. " + new Date());
            return false;
        }
    }

    @Override
    public boolean SendMessage(String emailFrom, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if(!emailFrom.isEmpty() && !message.isEmpty()) {
            mailMessage.setFrom(emailFrom);
            mailMessage.setTo(admin);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            mailSender.send(mailMessage);
            logger.info("Send message to admin from " + emailFrom + " " + new Date());
            return true;
        }else{
            logger.error("Message not send. Illegal parametrs. " + new Date());
            return false;
        }
    }

    public static File convert(MultipartFile file)
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convFile;
    }
}
