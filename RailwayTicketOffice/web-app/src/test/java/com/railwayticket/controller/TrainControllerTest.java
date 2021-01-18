package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.domain.TypeTrain;
import com.railwayticket.rest.TrainRestController;
import com.railwayticket.service.servic_api.TrainServiceApi;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class TrainControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("TrainServiceRest")
    private TrainServiceApi trainServiceApi;

    @Autowired
    private TrainDaoApi trainDaoApi;

    @Test
    public void ListTrainPageTest() throws Exception {
        List<Trains>result_train = trainDaoApi.FindAll();
        Mockito.when(trainServiceApi.FindAll()).thenReturn(result_train);
        mockMvc.perform(get("/listTrain"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(3))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of trains"));
    }

    @Test
    public void OneTrainPageTest() throws Exception {
        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(trainDaoApi.getOneById(321L));
        mockMvc.perform(get("/oneTrain/321"))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateTrainLoadPageTest() throws Exception {
        Trains update_train = trainDaoApi.getOneById(321L);

        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(update_train);

        Mockito.when(trainServiceApi.update(trainDaoApi.getOneById(321L))).thenReturn(true);
        mockMvc.perform(get("/EditTrainInfo/321"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='train_number']")
                        .string("321"));
    }

    @Test
    public void UpdateTrainTest() throws Exception {
        Trains update_train = trainDaoApi.getOneById(321L);

        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(update_train);
        Mockito.when(trainServiceApi.update(update_train)).thenReturn(true);

        this.mockMvc.perform(post("/EditTrainInfo/321")
                .param("name_train_edit","NewUpdateName")
                .param("departure_station_edit","Minsk")
                .param("arrival_station_edit","Brest")
                .param("type_train_edit","Econom")
                .param("datetime_dep_edit","2021-09-12")
                .param("datetime_arr_edit","2021-19-12")
                .param("total_ticket_edit","12")
                .param("price_edit","12,2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
    }

    @Test
    public void SaveTrainTest() throws Exception {
        Trains save_train = new Trains();
        save_train.setName_train("NewSaveName");
        save_train.setDepartureStation(Stations.MINSK);
        save_train.setArrivalStation(Stations.BREST);
        save_train.setTypeTrain(TypeTrain.ECONOM);
        save_train.setDate_time_departure(new Date(new java.util.Date().getTime()));
        save_train.setDate_time_arrival(new Date(new java.util.Date().getTime()));
        save_train.setTotal_ticket(321);
        save_train.setPrice_ticket(12.2F);
        save_train.setAvailable_ticket(321);
        Mockito.when(trainServiceApi.update(save_train)).thenReturn(true);
        this.mockMvc.perform(post("/SaveNewTrain")
                .param("name_train","NewSaveName")
                .param("departure_station","Minsk")
                .param("arrival_station","Brest")
                .param("type_train","Econom")
                .param("datetime_dep","2021-09-12")
                .param("datetime_arr","2021-19-12")
                .param("total_ticket","321")
                .param("available_ticket","321")
                .param("price","12,2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
     }


    @Test
    public void DeleteTrainTest() throws Exception {
        Trains delete_train = trainDaoApi.getOneById(123L);

        Mockito.when(trainServiceApi.getOneById(123L)).thenReturn(delete_train);
        Mockito.when(trainServiceApi.delete(delete_train)).thenReturn(true);
        mockMvc.perform(get("/DeleteTrain/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
    }

    @Test
    public void FindTrainByDateTest() throws Exception {
        java.util.Date date_departure_find=new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-12");
        java.util.Date date_arrival_find=new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-13");
        Mockito.when(trainServiceApi.FindAllByDateDepartureArrivalStations(new Date(date_departure_find.getTime()),new Date(date_arrival_find.getTime()),Stations.BREST,Stations.MINSK))
                .thenReturn(trainDaoApi.FindAllByDateDepartureArrivalStations(new Date(date_departure_find.getTime()),new Date(date_arrival_find.getTime()),Stations.BREST,Stations.MINSK));
        this.mockMvc.perform(post("/FindTrain")
                .param("departure_date","2020-09-12")
                .param("arrival_date","2020-09-13")
                .param("departure_station_find","Brest")
                .param("arrival_station_find","Minsk"))
                .andExpect(status().isOk());
    }
}