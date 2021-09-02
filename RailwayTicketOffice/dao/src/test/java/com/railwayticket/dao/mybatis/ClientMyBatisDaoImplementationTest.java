package com.railwayticket.dao.mybatis;

import com.domain.ClientRailway;
import com.railwayticket.dao_api.ClientMyBatisDaoImplementation;
import com.railwayticket.restclient.config.BeanConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@Sql(value = {"classpath:/script_after_test_clients.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientMyBatisDaoImplementationTest extends TestCase {

    @Autowired
    private ClientMyBatisDaoImplementation clientMyBatisDaoImplementation;

    @Test
    public void SaveClient_WithId_ThenReturnTrue() {
        ClientRailway clientRailway = new ClientRailway(6712L,127L,"test","test",null,"test");
        clientMyBatisDaoImplementation.save(clientRailway);
        ClientRailway clientRailwayResultSave = clientMyBatisDaoImplementation.getOneById(6712L);
        Assert.assertNotNull(clientRailwayResultSave);
    }

    @Test
    public void UpdateClientWithId777_ThenReturnTrue() {
        ClientRailway clientRailwayUpdate = clientMyBatisDaoImplementation.getOneById(777L);
        clientRailwayUpdate.setName_client("UpdateMyBatis");
        clientMyBatisDaoImplementation.update(clientRailwayUpdate);
        String name_update_client = clientMyBatisDaoImplementation.getOneById(777L).getName_client();
        Assert.assertEquals("UpdateMyBatis", name_update_client);
    }

    @Test
    public void DeleteClient_WithId521_ThenReturnSize5() {
        ClientRailway clientRailwayDelete = clientMyBatisDaoImplementation.getOneById(521L);
        clientMyBatisDaoImplementation.delete(clientRailwayDelete);
        Assert.assertEquals(5,clientMyBatisDaoImplementation.FindAll().size());
    }

    @Test
    public void GetOneClientById_WithID421_ThenReturnTrue() {
        ClientRailway clientRailwayWithId421 = clientMyBatisDaoImplementation.getOneById(421L);
        Assert.assertNotNull(clientRailwayWithId421);
    }

    @Test
    public void FindAllClient_ThenReturn6() {
        List<ClientRailway>allClients = clientMyBatisDaoImplementation.FindAll();
        Assert.assertEquals(6,allClients.size());
    }

    @Test
    public void FindByNameClient_WithNameTes_ThenReturnTrue() {
        List<ClientRailway>allClientWithPartNameTes = clientMyBatisDaoImplementation.FindByName("Tes");
        Assert.assertTrue(allClientWithPartNameTes.size()>1);
    }
}