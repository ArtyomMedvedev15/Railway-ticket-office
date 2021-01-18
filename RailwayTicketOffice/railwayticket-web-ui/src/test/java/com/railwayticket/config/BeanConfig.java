package com.railwayticket.config;

import com.railwayticket.service.servic_api.ClientServiceApi;
import com.railwayticket.service.servic_api.TrainServiceApi;
import com.railwayticket.service.service_rest.ClientRestServiceImpl;
import com.railwayticket.service.service_rest.TrainRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.railwayticket")
@EnableWebMvc
public class BeanConfig extends WebMvcConfigurerAdapter {

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ClientServiceApi ClientServiceRestImpl(){
        return new ClientRestServiceImpl();
    }


    @Bean(name = "TrainServiceRest")
    public TrainServiceApi TrainServiceRestImpl(){
        return new TrainRestServiceImpl();
    }
}
