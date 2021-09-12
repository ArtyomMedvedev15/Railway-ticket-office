package com.railwayticket.restclient.config;


 import com.railwayticket.dao.ClientDaoImplementation;
import com.railwayticket.dao.TrainDaoImplementation;
import com.railwayticket.dao_api.ClientDaoApi;
import com.railwayticket.dao_api.TrainDaoApi;

 import org.apache.ibatis.session.SqlSessionFactory;
 import org.mybatis.spring.SqlSessionFactoryBean;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
import java.util.Locale;

@Configuration
@MapperScan("com.railwayticket.dao_api")
public class BeanConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
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
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
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

}
