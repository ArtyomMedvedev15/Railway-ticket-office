package com.railwayticket.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railwayticket.config.BeanConfig;
import com.railwayticket.domain.ClientRailway;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.domain.TypeTrain;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {BeanConfig.class})
@Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TrainRestControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TrainByIdTest_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/train/123")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void TrainByIdTest_thenStatusBadRequest() throws Exception {
        mockMvc.perform(get("/api/train/null")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TrainByIdTest_thenStatusNotFound() throws Exception {
        mockMvc.perform(get("/api/train/123412")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void SaveTrainTest_thenStatusCreated() throws Exception {
        Trains trains = new Trains("Test", TypeTrain.BUSINESS, Stations.BREST,Stations.GOMEL,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,23.3F);
         mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/saveTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(trains)))
                .andExpect(status().isCreated());
    }

    @Test
    public void SaveTrainTest_thenStatusBadRequest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/saveTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void UpdateTrainTest_thenStatusOk() throws Exception {
        Trains trains = new Trains("Test", TypeTrain.BUSINESS, Stations.BREST,Stations.GOMEL,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,23.3F);
        trains.setId_train(1243L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/updateTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(trains)))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateTrainTest_thenStatusBadRequest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/updateTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void DeleteTrainsTest_thenStatusNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/train/deleteTrain/123")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }

    @Test
    public void GetAllTrainTest_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/train/allTrain")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void FindAllTrainsByDatesAndStations_thenStatusOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/findtrainbydates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("departure_date","2020-09-12")
                        .param("arrival_date","2020-09-13")
                        .param("departure_station_find","Brest")
                        .param("arrival_station_find","Minsk"))
                .andExpect(status().isOk());
    }

    @Test
    public void FindAllTrainsByDatesAndStations_thenStatusNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/train/findtrainbydates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("departure_date","2020-07-12")
                        .param("arrival_date","2020-08-13")
                        .param("departure_station_find","Brest")
                        .param("arrival_station_find","Minsk"))
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