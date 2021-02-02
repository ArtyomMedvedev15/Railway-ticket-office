package com.railwayticket.config;


import com.ApiClient;
import com.railwayticket.service_rest.ClientRestServiceImpl;
import com.railwayticket.service_rest.TrainRestServiceImpl;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.TrainServiceApi;
import com.rest.ClientRestControllerApi;
import com.rest.TrainRestControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@ComponentScan("com.railwayticket")
@EnableWebMvc
public class BeanConfig extends WebMvcConfigurerAdapter {

    private final ApplicationContext applicationContext;

    @Autowired
    private Environment environment;

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


    @Bean
    public TrainServiceApi TrainServiceRestImpl(){
        return new TrainRestServiceImpl();
    }

    @Bean
    public ClientRestControllerApi ClientRestApi(){
        return new com.rest.ClientRestControllerApi();
    }

    @Bean
    public com.rest.TrainRestControllerApi TrainRestApi(){
        return new TrainRestControllerApi();
    }


}
