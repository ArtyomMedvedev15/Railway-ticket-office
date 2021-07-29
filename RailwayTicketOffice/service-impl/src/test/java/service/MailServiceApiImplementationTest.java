package service;

import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.MailSenderApi;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;
import service.config.BeanConfig;
import service.config.DispatcherServletInitializer;

import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@SpringBootTest
@WebAppConfiguration
public class MailServiceApiImplementationTest extends TestCase {

    @MockBean
    @Qualifier("getMailService")
    public MailSenderApi mailSenderApi;

    @Test
    public void SendMessageWithSuccessParametersTest_ThenReturnTrue() throws MessagingException {
        String subject = "subject";
        String email = "email";
        String message = "message";
        MultipartFile file = new MockMultipartFile("fileas",new byte[]{});

        Mockito.when(mailSenderApi.SendMessageWithAttchement(email,subject,message,file)).thenReturn(true);

        boolean result_send = mailSenderApi.SendMessageWithAttchement(email,subject,message,file);

        Assert.assertTrue(result_send);

        Mockito.verify(mailSenderApi,Mockito.times(1)).SendMessageWithAttchement(email,subject,message,file);

    }

    @Test
    public void SendMessageWithErrorParametersTest_ThenReturnFalse() throws MessagingException {
        String subject = "";
        String email = "email";
        String message = "message";
        MultipartFile file = new MockMultipartFile("fileas",new byte[]{});

        Mockito.when(mailSenderApi.SendMessageWithAttchement(email,subject,message,file)).thenReturn(false);

        boolean result_send = mailSenderApi.SendMessageWithAttchement(email,subject,message,file);

        Assert.assertFalse(result_send);

        Mockito.verify(mailSenderApi,Mockito.times(1)).SendMessageWithAttchement(email,subject,message,file);

    }

    @Test
    public void SendMessageCorrectTest_ThenReturnTrue() {
        String subject = "subject";
        String email = "email";
        String message = "message";

        Mockito.when(mailSenderApi.SendMessage(email,subject,message)).thenReturn(true);

        boolean result_send = mailSenderApi.SendMessage(email,subject,message);

        Assert.assertTrue(result_send);

        Mockito.verify(mailSenderApi,Mockito.times(1)).SendMessage(email,subject,message);
    }

    @Test
    public void SendMessageErrorTest_ThenReturnFalse() {
        String subject = "";
        String email = "email";
        String message = "message";

        Mockito.when(mailSenderApi.SendMessage(email,subject,message)).thenReturn(false);

        boolean result_send = mailSenderApi.SendMessage(email,subject,message);

        Assert.assertFalse(result_send);

        Mockito.verify(mailSenderApi,Mockito.times(1)).SendMessage(email,subject,message);
    }
}