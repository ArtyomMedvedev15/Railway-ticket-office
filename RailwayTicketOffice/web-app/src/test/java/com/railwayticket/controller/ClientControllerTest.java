package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import com.railwayticket.dao.dao_api.ClientDaoApi;
import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@ContextConfiguration(classes = {BeanConfig.class})
@Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ClientControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("ClientServiceRestImpl")
    private ClientServiceApi clientServiceApi;

    @Autowired
    private ClientDaoApi clientDaoApi;

    @Test
    public void DeleteClientTest() throws Exception {
        ClientRailway oneById_delete = clientDaoApi.getOneById(778L);
        Mockito.when(clientServiceApi.getOneById(778L)).thenReturn(oneById_delete);
        Mockito.when(clientServiceApi.delete(oneById_delete)).thenReturn(true);
          mockMvc.perform(get("/deleteclient/778"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));
     }

    @Test
    public void HomePageLoadTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='navbarResponsive']/ul/li[1]/a").string("Home"));
    }

    @Test
    public void ListPageClientTest() throws Exception {
        Mockito.when(clientServiceApi.FindAll()).thenReturn(clientDaoApi.FindAll());
        mockMvc.perform(get("/listClient"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(6))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }



    @Test
    public void UpdateClientPageLoadTest() throws Exception {
        ClientRailway oneById_update = clientDaoApi.getOneById(421L);
        Mockito.when(clientServiceApi.getOneById(421L)).thenReturn(oneById_update);
        mockMvc.perform(get("/EditClientInfo/421"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_client_edit']")
                        .string("TestClient"));
    }

    @Test
    public void UpdateClientTest() throws Exception {
        ClientRailway clientRailway_update = clientDaoApi.getOneById(421L);

        Mockito.when(clientServiceApi.getOneById(421L)).thenReturn(clientRailway_update);
        Mockito.when(clientServiceApi.update(clientRailway_update)).thenReturn(true);
        this.mockMvc.perform(post("/EditClientInfo/421")
                .param("name_client","NewUpdateName")
                .param("soname_client","asd")
                .param("phone_client","12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));
    }

    @Test
    public void BuyTicketTest() throws Exception {
        ClientRailway clientRailway_save = new ClientRailway();
        clientRailway_save.setName_client("NewTempNameClient");
        clientRailway_save.setSoname_client("NewTempSonameClient");
        clientRailway_save.setPhone_client("NewTempPhoneClient");
        clientRailway_save.setId_train(123L);
        clientRailway_save.setDate_purchase(new Date(new java.util.Date().getTime()));
        Mockito.when(clientServiceApi.save(clientRailway_save)).thenReturn(true);
        this.mockMvc.perform(post("/buyticket")
                .param("name_client","NewTempNameClient")
                .param("soname_client","NewTempSonameClient")
                .param("phone_client","NewTempPhoneClient")
                .param("train_id","123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));

     }

    @Test
    public void FindByNameTest() throws Exception {
        Mockito.when(clientServiceApi.FindByNameClient("Tes")).thenReturn(clientDaoApi.FindByName("Tes"));
        this.mockMvc.perform(post("/FindClientByName")
                .param("name_client","Tes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(4))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }
}