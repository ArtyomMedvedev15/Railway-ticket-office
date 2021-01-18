package com.railwayticket.config;

import com.railwayticket.dao.ClientDaoImplementation;
import com.railwayticket.dao.TrainDaoImplementation;
import com.railwayticket.dao.dao_api.ClientDaoApi;
import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.service.ClientServiceApiImplementation;
import com.railwayticket.service.TrainServiceApiApiImplementation;
import com.railwayticket.service.servic_api.ClientServiceApi;
import com.railwayticket.service.servic_api.TrainServiceApi;
import com.railwayticket.service.service_rest.ClientRestServiceImpl;
import com.railwayticket.service.service_rest.TrainRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.*;

import javax.sql.DataSource;
import java.util.Objects;

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
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(this.environment.getProperty("spring.datasource.password"));
        Resource initSchema;
        if (this.environment.getActiveProfiles().length!=0) {
            if (this.environment.getActiveProfiles()[0].equals("dev")) {
                initSchema = new ClassPathResource("database/initializeDatabaseMySql.sql");
            } else {
                initSchema = new ClassPathResource("database/initializeDatabase.sql");
            }
        }else{
            initSchema = new ClassPathResource("database/initializeDatabase.sql");
        }
        Resource initData = new ClassPathResource("database/populateData.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TrainDaoApi TrainDaoApiImplementation(){
        return new TrainDaoImplementation(dataSource());
    }

    @Bean
    public ClientDaoApi ClientDaoApiImplementation(){
        return new ClientDaoImplementation(dataSource());
    }

    @Bean
    public TrainServiceApi TrainServiceImplementation(){
        return new TrainServiceApiApiImplementation(transactionManager());
    }

    @Bean
    public ClientServiceApi ClientServiceApiImplementation(){
        return new ClientServiceApiImplementation(transactionManager());
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
