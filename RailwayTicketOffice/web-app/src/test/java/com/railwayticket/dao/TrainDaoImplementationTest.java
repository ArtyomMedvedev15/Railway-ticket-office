package com.railwayticket.dao;

import com.railwayticket.dao.dao_api.TrainDaoApi;
import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.domain.TypeTrain;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

@RunWith(JUnit4.class)
public class TrainDaoImplementationTest extends TestCase {

    ApplicationContext context = new ClassPathXmlApplicationContext("config/beans.xml");

//    TrainDaoApi trainDaoApi = context.getBean("traindao",TrainDaoImplementation.class);

    @Test
    public void testFindAllByDateDepartureArrivalStations() {
    }

    @Test
    public void testSave() {
//        Trains trains = new Trains();
//        trains.setName_train("TestTrain");
//        trains.setTypeTrain(TypeTrain.BUSINESS);
//        trains.setDepartureStation(Stations.BREST);
//        trains.setArrivalStation(Stations.MINSK);
//        trains.setDate_time_departure(new Date(new java.util.Date().getTime()));
//        trains.setDate_time_arrival(new Date(new java.util.Date().getTime()));
//        trains.setAvailable_ticket(100);
//        trains.setPrice_ticket(21F);
//        trains.setTotal_ticket(230);
//
//        boolean result_save_train = trainDaoApi.save(trains);
//
//        Assert.assertTrue(result_save_train);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetOneById() {
    }

    @Test
    public void testFindAll() {
    }
}