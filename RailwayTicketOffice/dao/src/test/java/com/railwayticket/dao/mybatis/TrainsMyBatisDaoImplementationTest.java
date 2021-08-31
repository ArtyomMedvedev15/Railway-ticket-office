package com.railwayticket.dao.mybatis;

import com.domain.ClientRailway;
import com.domain.Stations;
import com.domain.Trains;
import com.domain.TypeTrain;
import com.railwayticket.restclient.config.BeanConfig;
import com.railwayticket.restclient.config.DispatcherServletInitializer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@WebAppConfiguration
 @Sql(value = {"classpath:/script_before_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"classpath:/script_after_test.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
 public class TrainsMyBatisDaoImplementationTest extends TestCase {

    @Autowired
    private TrainsMyBatisDaoImplementation trainsMyBatisDaoImplementation;

    @Test
    public void SaveTrain_WithNameTrainMyBatis_ThenReturnTrue() {
        Trains trainsSave = new Trains("TrainSaveMyBatis", TypeTrain.COUPE, Stations.BREST,Stations.GOMEL,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,12.0F);
        trainsSave.setId_train(5421L);
        trainsMyBatisDaoImplementation.save(trainsSave);

        Trains trainResultSave = trainsMyBatisDaoImplementation.getOneById(5421L);

        Assert.assertEquals("TrainSaveMyBatis",trainResultSave.getName_train());

    }

    @Test
    public void UpdateTrain_withId321_thenReturnTrue() {
        Trains trains = trainsMyBatisDaoImplementation.getOneById(321L);
        trains.setName_train("MyBatisTrain");

        trainsMyBatisDaoImplementation.update(trains);

        Trains trainUpdate = trainsMyBatisDaoImplementation.getOneById(321L);

        Assert.assertEquals("MyBatisTrain",trainUpdate.getName_train());
    }

    @Test
    public void DeleteTrain_Withid1234_ThenReturnTrue() {
        Trains trainDelete = trainsMyBatisDaoImplementation.getOneById(1234L);

        trainsMyBatisDaoImplementation.delete(trainDelete);

        Assert.assertEquals(2, trainsMyBatisDaoImplementation.FindAll().size());
    }

    @Test
    public void GetOne_WithId321_ThenReturnNotNull() {
        Trains trainsgetone = trainsMyBatisDaoImplementation.getOneById(321L);

        Assert.assertNotNull(trainsgetone);
    }

    @Test
    public void FindAll_ThenReturnTrue() {
        List<Trains>allTrain = trainsMyBatisDaoImplementation.FindAll();
        Assert.assertEquals(3,allTrain.size());
    }

    @Test
    public void FindAllByDateDepartureArrivalStations_ThenReturn3() throws ParseException {
        java.util.Date dateDeparture=new SimpleDateFormat("dd/MM/yyyy").parse("12/09/2020");
        java.util.Date datearrival=new SimpleDateFormat("dd/MM/yyyy").parse("13/09/2020");
        List<Trains>allTrainFindByDateStation = trainsMyBatisDaoImplementation.FindAllByDateDepartureArrivalStations(new Date(dateDeparture.getTime()),new Date(datearrival.getTime()),Stations.BREST,Stations.MINSK);

        Assert.assertEquals(3,allTrainFindByDateStation.size());
    }

    @Test
    public void GetAllClientTrain_WithClientId() {
        List<ClientRailway>allClientTrain = trainsMyBatisDaoImplementation.GetAllClientTrain(122L);

        Assert.assertEquals(1,allClientTrain.size());
    }
}