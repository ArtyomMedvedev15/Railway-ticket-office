package com.railwayticket.service_rest;

import com.railwayticket.services_api.MailSenderApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class MailSenderRestServiceImpl implements MailSenderApi {
    @Autowired
    private RestTemplate restTemplate;

    final static Logger logger = Logger.getLogger(MailSenderRestServiceImpl.class);

    private final String base_url = "http://localhost:8181/";
    @Override
    public boolean SendMessageWithAttchement(String emailFrom, String subject, String message, MultipartFile file) throws MessagingException {
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("email",new String(emailFrom));
        bodyMap.add("subject",new String(subject));
        bodyMap.add("message",new String(message));
        bodyMap.add("file",  new FileSystemResource(convert(file)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);

        ResponseEntity<Boolean> response = restTemplate.postForEntity( base_url+"sendemail", request , Boolean.class );

        if(response.getBody()){
            logger.info("Send email with attchement success. " + new Date());
            return true;
        }else{
           logger.error("Send email not send. "  + new Date());
           return false;
        }
     }

    @Override
    public boolean SendMessage(String emailFrom, String subject, String message) {
        return false;
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
