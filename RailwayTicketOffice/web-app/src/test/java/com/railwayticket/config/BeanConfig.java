package com.railwayticket.config;

import com.railwayticket.dao.ClientDaoImplementation;
import com.railwayticket.dao.TrainDaoImplementation;
import com.railwayticket.dao.dao_api.ClientDaoApi;
import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.service.ClientServiceApiImplementation;
import com.railwayticket.service.TrainServiceApiApiImplementation;
import com.railwayticket.service.servic_api.ClientServiceApi;
import com.railwayticket.service.servic_api.TrainServiceApi;
import com.railwayticket.service_rest.ClientRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

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
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/railwayticketofficetest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

        Resource initSchema = new ClassPathResource("database/initializeDatabase.sql");
        Resource initData = new ClassPathResource("database/populateDataTest.sql");
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
    public ClientServiceApi ClientServiceServiceRest(){
        return new ClientRestServiceImpl();
    }
}
