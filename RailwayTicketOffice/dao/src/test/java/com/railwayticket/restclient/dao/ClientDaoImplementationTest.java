package com.railwayticket.restclient.dao;

import com.domain.ClientRailway;
import com.railwayticket.dao_api.ClientDaoApi;

import com.railwayticket.restclient.config.BeanConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@ContextConfiguration(classes = BeanConfig.class)
@WebAppConfiguration
@Sql(value = {"classpath:/script_before_clients_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test_clients.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientDaoImplementationTest extends TestCase {

    @Qualifier("ClientDaoApiImplementation")
    @Autowired
    private ClientDaoApi clientDaoApi;

    @Test
    public void SaveNewClientTest() {
        ClientRailway clientRailway = new ClientRailway();
        clientRailway.setName_client("TestTestTest");
        clientRailway.setSoname_client("Test");
        clientRailway.setId_train(322L);
        clientRailway.setDate_purchase(new Date(new java.util.Date().getTime()));
        clientRailway.setPhone_client("123123123");

        boolean result_save_client = clientDaoApi.save(clientRailway);

        Assert.assertTrue(result_save_client);
    }

    @Test
    public void UpdateClientTest() {
        ClientRailway clientRailway = clientDaoApi.getOneById(421L);

        boolean result_update_client = clientDaoApi.update(clientRailway);

     }

    @Test
    public void DeleteClientTest() {
        ClientRailway clientdelete = new ClientRailway();
        clientdelete.setName_client("TestTestTest");
        clientdelete.setSoname_client("Test");
        clientdelete.setId_train(124L);
        clientdelete.setDate_purchase(new Date(new java.util.Date().getTime()));
        clientdelete.setPhone_client("123123123");

        clientDaoApi.save(clientdelete);

        ClientRailway clientRailway = clientDaoApi.FindByName("TestTestTest").get(0);

        boolean result_delete_client = clientDaoApi.delete(clientRailway);

        Assert.assertTrue(result_delete_client);
    }

    @Test
    public void GetOneClientById() {
        ClientRailway clientRailway = clientDaoApi.getOneById(123L);

        Assert.assertNotNull(clientRailway);
    }

    @Test
    public void FindAllClientTest() {
        List<ClientRailway>all_client = clientDaoApi.FindAll();
        Assert.assertTrue(all_client.size()>0);
    }

    @Test
    public void FindByNameTest() {
        List<ClientRailway>all_client = clientDaoApi.FindByName("Test");
        Assert.assertTrue(all_client.size()>0);
    }
}