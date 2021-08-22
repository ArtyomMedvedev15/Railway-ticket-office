package com.railwayticket.restclient.dao;

import com.domain.ClientRailway;
import com.domain.Stations;
import com.domain.Trains;
import com.domain.TypeTrain;
import com.railwayticket.dao_api.TrainDaoApi;
import com.railwayticket.restclient.config.BeanConfig;
import com.railwayticket.restclient.config.DispatcherServletInitializer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@WebAppConfiguration
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TrainDaoImplementationTest extends TestCase {

    @Qualifier("TrainDaoApiImplementation")
    @Autowired
    public TrainDaoApi trainDaoApi;

    @Test
    public void SaveTrainTest() throws ParseException {
        String date_departure = "2020/08/19";
        String date_arrival = "2020/08/22";
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");

        Trains trains = new Trains();
        trains.setName_train("TestTrain");
        trains.setTypeTrain(TypeTrain.BUSINESS);
        trains.setDepartureStation(Stations.BREST);
        trains.setArrivalStation(Stations.MINSK);
        trains.setDate_time_departure(new Date(date.parse(date_departure).getTime()));
        trains.setDate_time_arrival(new Date(date.parse(date_arrival).getTime()));
        trains.setAvailable_ticket(100);
        trains.setPrice_ticket(21F);
        trains.setTotal_ticket(230);

        boolean result_save_train = trainDaoApi.save(trains);

        Assert.assertTrue(result_save_train);
    }
    @Test
    public void FindAllByDateDepartureArrivalStationsTest() throws ParseException {
        String date_departure = "2020/09/12";
        String date_arrival = "2020/09/13";
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");


        List<Trains>find_result =
                trainDaoApi.FindAllByDateDepartureArrivalStations(new Date(date.parse(date_departure).getTime()),
                        new Date(date.parse(date_arrival).getTime()),Stations.BREST,Stations.MINSK);

        Assert.assertTrue(find_result.size()>0);
     }

    @Test
    public void UpdateTrainTest() {
        Trains trains = trainDaoApi.getOneById(321L);
        trains.setName_train("NewNameTrain");

        boolean result_update_train = trainDaoApi.update(trains);

        Assert.assertEquals("NewNameTrain",trainDaoApi.getOneById(321L).getName_train());
    }

    @Test
    public void DeleteTrainTest() {
        Trains trains = trainDaoApi.getOneById(321L);

        boolean result_delete_train = trainDaoApi.delete(trains);

        Assert.assertTrue(result_delete_train);
    }

    @Test
    public void GetOneByIdTest() {
        Trains trains = trainDaoApi.getOneById(1234L);

        assertNotNull(trains);
    }

    @Test
    public void FindAllTest() {
        List<Trains> list = trainDaoApi.FindAll();

        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void GetAllClientTrainTest() {
        List<ClientRailway>getAllClient = trainDaoApi.GetAllClientTrain(126L);

     }
}