package com.railwayticket.controller;

import com.domain.Stations;
import com.domain.Trains;
import com.domain.TypeTrain;
import com.railwayticket.config.BeanConfig;

import com.railwayticket.services_api.TrainServiceApi;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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
    private com.rest.TrainRestControllerApi trainServiceApi;


    @Test
    public void ListTrainPageTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);
        List<io.swagger.client.model.Trains>result_train = Arrays.asList(trains,trains,trains);
        Mockito.when(trainServiceApi.allTrainUsingGET()).thenReturn(result_train);
        mockMvc.perform(get("/listTrain"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(3))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of trains"));
    }

    @Test
    public void OneTrainPageTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(321L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);
        Mockito.when(trainServiceApi.trainByIdUsingGET(321L)).thenReturn(trains);
        mockMvc.perform(get("/oneTrain/321"))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateTrainLoadPageTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(321L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);
        Mockito.when(trainServiceApi.trainByIdUsingGET(321L)).thenReturn(trains);

        Mockito.when(trainServiceApi.updateTrainUsingPOST(trains)).thenReturn(trains);
        mockMvc.perform(get("/EditTrainInfo/321"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='train_number']")
                        .string("321"));
    }

    @Test
    public void UpdateTrainTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(321L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);

        Mockito.when(trainServiceApi.trainByIdUsingGET(321L)).thenReturn(trains);
        Mockito.when(trainServiceApi.updateTrainUsingPOST(trains)).thenReturn(trains);

        this.mockMvc.perform(post("/EditTrainInfo/321")
                .param("name_train_edit","NewUpdateName")
                .param("departure_station_edit","MINSK")
                .param("arrival_station_edit","BREST")
                .param("type_train_edit","ECONOM")
                .param("datetime_dep_edit","2021-09-12")
                .param("datetime_arr_edit","2021-19-12")
                .param("total_ticket_edit","12")
                .param("price_edit","12,2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
    }

    @Test
    public void SaveTrainTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(321L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);
        Mockito.when(trainServiceApi.saveTrainUsingPOST(trains)).thenReturn(trains);
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
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(123L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("date");
        trains.setDateTimeDeparture("Date");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);

        Mockito.when(trainServiceApi.trainByIdUsingGET(123L)).thenReturn(trains);
        Mockito.when(trainServiceApi.deleteTrainsUsingGET(trains.getIdTrain())).thenReturn(trains);
        mockMvc.perform(get("/DeleteTrain/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
    }

    @Test
    public void FindTrainByDateTest() throws Exception {
        io.swagger.client.model.Trains trains = new io.swagger.client.model.Trains();
        trains.setIdTrain(321L);
        trains.setNameTrain("Temp");
        trains.setTypeTrain(io.swagger.client.model.Trains.TypeTrainEnum.ECONOM);
        trains.setDepartureStation(io.swagger.client.model.Trains.DepartureStationEnum.MINSK);
        trains.setArrivalStation(io.swagger.client.model.Trains.ArrivalStationEnum.BREST);
        trains.setDateTimeArrival("2020-09-15");
        trains.setDateTimeDeparture("2020-09-20");
        trains.setAvailableTicket(123);
        trains.setPriceTicket(20.0F);
        trains.setTotalTicket(123);;

        Mockito.when(trainServiceApi.findAllTrainsByDatesAndStationsUsingPOST("2020-09-15","BREST","2020-09-20","MINSK")
        ).thenReturn(Stream.of(trains,trains).collect(Collectors.toList()));
        this.mockMvc.perform(post("/FindTrain")
                .param("departure_date","2020-09-12")
                .param("arrival_date","2020-09-13")
                .param("departure_station_find","Brest")
                .param("arrival_station_find","Minsk"))
                .andExpect(status().isOk());
    }
}