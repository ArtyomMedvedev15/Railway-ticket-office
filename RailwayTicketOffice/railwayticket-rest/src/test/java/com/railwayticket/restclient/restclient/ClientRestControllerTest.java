package com.railwayticket.restclient.restclient;

import com.domain.ClientRailway;
import com.fasterxml.jackson.databind.ObjectMapper;
 import com.railwayticket.restclient.config.BeanConfig;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {BeanConfig.class})
@Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientRestControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ClientRailwayByIdTest_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/clients/123")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void ClientRailwayByIdTest_thenStatusBadRequest() throws Exception {
        mockMvc.perform(get("/api/clients/null")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ClientRailwayByIdTest_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/431")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void SaveClient_thenStatusCreated() throws Exception {
        ClientRailway clientRailway = new ClientRailway(1L,123L,"Test Client","Test Client",new Date(new java.util.Date().getTime()),"+373(33)332323");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/clients/saveClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRailway)))
                .andExpect(status().isCreated());
    }

    @Test
    public void SaveClient_thenStatusBadRequest() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.post("/api/clients/saveClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void UpdateClient_thenStatusCreated() throws Exception {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"Update Test Client","Test Client",new Date(new java.util.Date().getTime()),"+373(33)332323");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/clients/updateClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientRailway)))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateClient_thenStatusBadRequest() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.post("/api/clients/updateClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void DeleteClient_thenStatusNoContent() throws Exception {
        mockMvc.perform(get("/api/clients/deleteClient/123")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    public void DeleteClient_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/deleteClient/12233")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void GetAllClient_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/clients/allClient")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void GetAllClientByName_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/clients/findclientbyname/Te")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void GetAllClientByName_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/findclientbyname/zzzzzzzzzzz")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}