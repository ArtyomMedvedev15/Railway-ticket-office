package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        monochrome = true,
        tags = "@home",
        glue = "com.cucumber.junit",
        features = "classpath:com/cucumber/features"
)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {BeanConfig.class})
@ActiveProfiles("test")
public class ClientControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.rest.ClientRestControllerApi controllerApi;


    @Test
    public void DeleteClientTest() throws Exception {
        com.rest.domains.ClientRailway oneById_delete = new com.rest.domains.ClientRailway();
        oneById_delete.setIdClient(778L);
        oneById_delete.setIdTrain(123L);
        oneById_delete.setNameClient("Temp");
        oneById_delete.setSonameClient("temp");
        oneById_delete.setDatePurchase("2020-09-15");
        oneById_delete.setPhoneClient("temp");

        Mockito.when(controllerApi.clientRailwayByIdUsingGET(778L)).thenReturn(oneById_delete);
        Mockito.when(controllerApi.deleteClientUsingGET(oneById_delete.getIdClient())).thenReturn(oneById_delete);
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
        com.rest.domains.ClientRailway findalll = new com.rest.domains.ClientRailway();
        findalll.setIdClient(778L);
        findalll.setIdTrain(123L);
        findalll.setNameClient("Temp");
        findalll.setSonameClient("temp");
        findalll.setDatePurchase("2020-09-15");
        findalll.setPhoneClient("temp");
        Mockito.when(controllerApi.allClientUsingGET()).thenReturn(Stream.of(findalll,findalll,findalll,findalll,findalll,findalll).collect(Collectors.toList()));
        mockMvc.perform(get("/listClient"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(6))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }



    @Test
    public void UpdateClientPageLoadTest() throws Exception {
        com.rest.domains.ClientRailway edit_client = new com.rest.domains.ClientRailway();
        edit_client.setIdClient(421L);
        edit_client.setIdTrain(123L);
        edit_client.setNameClient("TestClient");
        edit_client.setSonameClient("temp");
        edit_client.setDatePurchase("2020-09-15");
        edit_client.setPhoneClient("temp");
        Mockito.when(controllerApi.clientRailwayByIdUsingGET(421L)).thenReturn(edit_client);
        mockMvc.perform(get("/EditClientInfo/421"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_client_edit']")
                        .string("TestClient"));
    }

    @Test
    public void UpdateClientTest() throws Exception {
        com.rest.domains.ClientRailway edit_client = new com.rest.domains.ClientRailway();
        edit_client.setIdClient(421L);
        edit_client.setIdTrain(123L);
        edit_client.setNameClient("TestClient");
        edit_client.setSonameClient("temp");
        edit_client.setDatePurchase("2020-09-15");
        edit_client.setPhoneClient("temp");

        Mockito.when(controllerApi.clientRailwayByIdUsingGET(421L)).thenReturn(edit_client);
        Mockito.when(controllerApi.updateClientUsingPOST(edit_client)).thenReturn(edit_client);
        this.mockMvc.perform(post("/EditClientInfo/421")
                .param("name_client","NewUpdateName")
                .param("soname_client","asd")
                .param("phone_client","12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listClient"));
    }

    @Test
    public void BuyTicketTest() throws Exception {
        com.rest.domains.ClientRailway save_client = new com.rest.domains.ClientRailway();
        save_client.setIdClient(421L);
        save_client.setIdTrain(123L);
        save_client.setNameClient("TestClient");
        save_client.setSonameClient("temp");
        save_client.setDatePurchase("2020-09-15");
        save_client.setPhoneClient("temp");

         Mockito.when(controllerApi.saveClientUsingPOST(save_client)).thenReturn(save_client);
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
        com.rest.domains.ClientRailway find_byname = new com.rest.domains.ClientRailway();
        find_byname.setIdClient(421L);
        find_byname.setIdTrain(123L);
        find_byname.setNameClient("TestClient");
        find_byname.setSonameClient("temp");
        find_byname.setDatePurchase("2020-09-15");
        find_byname.setPhoneClient("temp");

        Mockito.when(controllerApi.findAllClientByNameUsingGET("Tes")).thenReturn(Stream.of(find_byname,find_byname,find_byname,find_byname).collect(Collectors.toList()));
        this.mockMvc.perform(post("/FindClientByName")
                .param("name_client","Tes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(4))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of client"));
    }
}