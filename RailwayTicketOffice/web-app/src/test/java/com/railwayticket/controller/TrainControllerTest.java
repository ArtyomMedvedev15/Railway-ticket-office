package com.railwayticket.controller;

import com.railwayticket.config.BeanConfig;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.TrainServiceApi;
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
public class TrainControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainServiceApi trainServiceApi;

    @Test
    public void ListTrainPageTest() throws Exception {
        mockMvc.perform(get("/listTrain"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath(" //*[@id='node']")
                        .nodeCount(3))
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name_page']")
                        .string("List of trains"));
    }

    @Test
    public void OneTrainPageTest() throws Exception {
        mockMvc.perform(get("/oneTrain/321"))
                .andExpect(status().isOk());
    }

    @Test
    public void UpdateTrainLoadPageTest() throws Exception {
        mockMvc.perform(get("/EditTrainInfo/321"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='train_number']")
                        .string("321"));
    }

    @Test
    public void UpdateTrainTest() throws Exception {
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

        Assert.assertEquals("NewUpdateName", trainServiceApi.getOneById(321L).getName_train());
    }

    @Test
    public void SaveTrainTest() throws Exception {
        this.mockMvc.perform(post("/SaveNewTrain")
                .param("name_train","NewSaveName")
                .param("departure_station","Minsk")
                .param("arrival_station","Brest")
                .param("type_train","Econom")
                .param("datetime_dep","2021-09-12")
                .param("datetime_arr","2021-19-12")
                .param("total_ticket","123")
                .param("available_ticket","123")
                .param("price","12,2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
     }


    @Test(expected = EmptyResultDataAccessException.class)
    public void DeleteTrainTest() throws Exception {
        Assert.assertNotNull(trainServiceApi.getOneById(123L));
        mockMvc.perform(get("/DeleteTrain/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listTrain"));
        trainServiceApi.getOneById(123L);
    }

    @Test
    public void FindTrainByDateTest() throws Exception {
        this.mockMvc.perform(post("/FindTrain")
                .param("departure_date","2020-09-12")
                .param("arrival_date","2020-09-13")
                .param("departure_station_find","Brest")
                .param("arrival_station_find","Minsk"))
                .andExpect(status().isOk());
    }
}