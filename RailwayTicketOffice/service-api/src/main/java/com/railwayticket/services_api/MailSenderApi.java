package com.railwayticket.services_api;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;

public interface MailSenderApi {
    boolean SendMessageWithAttchement(String emailFrom, String subject, String message, MultipartFile file) throws MessagingException;
    boolean SendMessage(String emailFrom, String subject, String message);
}
