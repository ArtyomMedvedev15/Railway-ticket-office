package com.railwayticket.dao;

import com.railwayticket.dao.dao_api.ClientDaoApi;
import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.domain.ClientRailway;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext-test.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientDaoImplementationTest extends TestCase {

    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/applicationContext-test.xml");
    ClientDaoApi clientDaoApi = context.getBean("clientdao",ClientDaoImplementation.class);

    @Test
    public void SaveNewClientTest() {
        ClientRailway clientRailway = new ClientRailway();
        clientRailway.setName_client("Test");
        clientRailway.setSoname_client("Test");
        clientRailway.setId_train(123L);
        clientRailway.setDate_purchase(new Date(new java.util.Date().getTime()));
        clientRailway.setPhone_client("123123123");

        boolean result_save_client = clientDaoApi.save(clientRailway);

        Assert.assertTrue(result_save_client);
    }

    @Test
    public void UpdateClientTest() {
        ClientRailway clientRailway = clientDaoApi.getOneById(123L);
        clientRailway.setName_client("NewNameClient");

        boolean result_update_client = clientDaoApi.update(clientRailway);

        Assert.assertTrue(result_update_client);
        Assert.assertEquals("NewNameClient",clientDaoApi.getOneById(123L).getName_client());
    }

    @Test
    public void DeleteClientTest() {
        ClientRailway clientRailway = clientDaoApi.getOneById(321L);

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
}