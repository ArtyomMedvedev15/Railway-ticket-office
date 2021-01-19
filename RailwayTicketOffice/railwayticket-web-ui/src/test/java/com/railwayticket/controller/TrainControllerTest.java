package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.domain.TypeTrain;
import com.railwayticket.service.servic_api.TrainServiceApi;
import junit.framework.TestCase;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
@ActiveProfiles("test")
public class TrainControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainServiceApi trainServiceApi;


    @Test
    public void ListTrainPageTest() throws Exception {
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime()),123,123,20.2F);
        List<Trains>result_train = Arrays.asList(trains,trains,trains);
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
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime()),123,123,20.2F);
        trains.setId_train(321L);
        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(trains);
        mockMvc.perform(get("/oneTrain/321"))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateTrainLoadPageTest() throws Exception {
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime()),123,123,20.2F);
        trains.setId_train(321L);

        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(trains);

        Mockito.when(trainServiceApi.update(trains)).thenReturn(true);
        mockMvc.perform(get("/EditTrainInfo/321"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='train_number']")
                        .string("321"));
    }

    @Test
    public void UpdateTrainTest() throws Exception {
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime()),123,123,20.2F);
        trains.setId_train(321L);

        Mockito.when(trainServiceApi.getOneById(321L)).thenReturn(trains);
        Mockito.when(trainServiceApi.update(trains)).thenReturn(true);

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
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),new Date(new java.util.Date().getTime()),123,123,20.2F);
        trains.setId_train(123L);

        Mockito.when(trainServiceApi.getOneById(123L)).thenReturn(trains);
        Mockito.when(trainServiceApi.delete(trains)).thenReturn(true);
        mockMvc.perform(get("/DeleteTrain/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
    }

    @Test
    public void FindTrainByDateTest() throws Exception {
        java.util.Date date_departure_find=new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-12");
        java.util.Date date_arrival_find=new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-13");
        Trains trains = new Trains("Temp",TypeTrain.ECONOM,Stations.BREST,Stations.GRODNO,new Date(date_departure_find.getTime()),new Date(date_arrival_find.getTime()),123,123,20.2F);
        trains.setId_train(123L);

        Mockito.when(trainServiceApi.FindAllByDateDepartureArrivalStations(new Date(date_departure_find.getTime()),new Date(date_arrival_find.getTime()),Stations.BREST,Stations.MINSK))
                .thenReturn(Stream.of(trains,trains).collect(Collectors.toList()));
        this.mockMvc.perform(post("/FindTrain")
                .param("departure_date","2020-09-12")
                .param("arrival_date","2020-09-13")
                .param("departure_station_find","Brest")
                .param("arrival_station_find","Minsk"))
                .andExpect(status().isOk());
    }
}