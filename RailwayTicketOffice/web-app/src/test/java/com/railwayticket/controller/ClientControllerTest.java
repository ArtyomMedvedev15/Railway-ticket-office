package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import com.railwayticket.service.servic_api.ClientServiceApi;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {BeanConfig.class})
@Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientServiceApi clientServiceApi;


    @Test(expected = EmptyResultDataAccessException.class)
    public void DeleteClientTest() throws Exception {
        Assert.assertNotNull(clientServiceApi.getOneById(777L));
        mockMvc.perform(get("/deleteclient/777"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));
        clientServiceApi.getOneById(777L);
    }

    @Test
    public void HomePageLoadTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='navbarResponsive']/ul/li[1]/a").string("Home"));
    }

    @Test
    public void ListPageClientTest() throws Exception {
        mockMvc.perform(get("/listClient"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(5))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }



    @Test
    public void UpdateClientPageLoadTest() throws Exception {
        mockMvc.perform(get("/EditClientInfo/421"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_client_edit']")
                        .string("TestClient"));
    }

    @Test
    public void UpdateClientTest() throws Exception {
        this.mockMvc.perform(post("/EditClientInfo/421")
                .param("name_client","NewUpdateName")
                .param("soname_client","asd")
                .param("phone_client","12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));

        Assert.assertEquals("NewUpdateName", clientServiceApi.getOneById(421L).getName_client());
    }

    @Test
    public void BuyTicketTest() throws Exception {
        this.mockMvc.perform(post("/buyticket")
                .param("name_client","NewTempNameClient")
                .param("soname_client","NewTempSonameClient")
                .param("phone_client","NewTempPhoneClient")
                .param("train_id","321"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));

        Assert.assertEquals("NewTempNameClient", clientServiceApi.FindByNameClient("NewTempNameClient").get(0).getName_client());
    }

    @Test
    public void FindByNameTest() throws Exception {
        this.mockMvc.perform(post("/FindClientByName")
                .param("name_client","Tes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(4))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }
}