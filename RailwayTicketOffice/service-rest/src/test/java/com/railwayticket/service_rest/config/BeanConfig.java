package com.railwayticket.service_rest.config;


import com.railwayticket.service_rest.ClientRestServiceImpl;
import com.railwayticket.service_rest.MailSenderRestServiceImpl;
import com.railwayticket.service_rest.TrainRestServiceImpl;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.MailSenderApi;
import com.railwayticket.services_api.TrainServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig{

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ClientServiceApi ClientServiceRestImpl(){
        return new ClientRestServiceImpl();
    }

    @Bean
    public TrainServiceApi TrainServiceRestImpl(){
        return new TrainRestServiceImpl();
    }

    @Bean
    public MailSenderApi MailServiceRestService(){
        return new MailSenderRestServiceImpl();
    }
}
