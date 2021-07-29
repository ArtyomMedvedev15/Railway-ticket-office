package service.config;



import com.railwayticket.dao.ClientDaoImplementation;
import com.railwayticket.dao.TrainDaoImplementation;
import com.railwayticket.dao_api.ClientDaoApi;
import com.railwayticket.dao_api.TrainDaoApi;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.MailSenderApi;
import com.railwayticket.services_api.TrainServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import service.ClientServiceApiImplementation;
import service.MailServiceApiImplementation;
import service.TrainServiceApiApiImplementation;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
public class BeanConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public BeanConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Value("${spring.mail.username}")
    private String admin;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private String port;


    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(admin);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.debug", String.valueOf(true));
        return mailSender;
    }
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/railwayticketofficetest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
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
    public TrainDaoApi TrainDaoApiImplementation(){
        return new TrainDaoImplementation(dataSource());
    }

    @Bean
    public ClientDaoApi ClientDaoApiImplementation(){
        return new ClientDaoImplementation(dataSource());
    }

    @Bean
    public MailSenderApi getMailService(){
        return new MailServiceApiImplementation();
    }
}
