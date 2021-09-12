package com.railwayticket.dao.jpa;

import com.domain.ClientRailway;
import com.railwayticket.dao_api.ClientJpaRepository;
import com.railwayticket.restclient.config.BeanConfig;
import com.railwayticket.restclient.config.JpaConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@ContextConfiguration(classes = JpaConfig.class)
@WebAppConfiguration
@Sql(value = {"classpath:/script_before_clients_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test_clients.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientJpaRepositoryTest {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Test
    public void GetClientById(){
        ClientRailway clientRailway = clientJpaRepository.getOne(421L);
        Assert.assertEquals("TestClient",clientRailway.getName_client());
    }

    @Test
    public void SaveClient(){
        ClientRailway clientRailway = new ClientRailway(5712L,127L,"test","test",null,"test");
        ClientRailway clientRailways = clientJpaRepository.save(clientRailway);

        ClientRailway savedClient = clientJpaRepository.getOne(5712L);
        Assert.assertEquals("test",savedClient.getName_client());
    }

}
