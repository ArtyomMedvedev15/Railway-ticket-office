package com.railwayticket.restclient.service;

import com.domain.Stations;
import com.domain.Trains;
import com.railwayticket.restclient.config.BeanConfig;
import com.railwayticket.restclient.config.DispatcherServletInitializer;
import com.railwayticket.services_api.TrainServiceApi;
import com.railwayticket.services_api.exception.ServiceException;
import com.railwayticket.services_api.exception.TrainServiceException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@SpringBootTest
@WebAppConfiguration
public class TrainServiceApiApiImplementationTest extends TestCase {

    @MockBean
    @Qualifier("TrainServiceImplementation")
    public TrainServiceApi trainServiceApi;

    @Test
    public void SaveNewTrainTest_True() throws ServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApi.save(train)).thenReturn(true);
        boolean result_save_train = trainServiceApi.save(train);
        Assert.assertTrue(result_save_train);
        Mockito.verify(trainServiceApi,Mockito.times(1)).save(train);
    }

    @Test
    public void SaveNewTrainTest_Exception() throws ServiceException {
         Mockito.when(trainServiceApi.save(Mockito.any(Trains.class))).thenThrow( new TrainServiceException("Error train for save equal null"));
        boolean result_save_train = true;
        try {
            result_save_train = trainServiceApi.save(null);
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_save_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Error train for save equal null");
        }
        Mockito.verify(trainServiceApi,Mockito.times(1)).save(null);
    }

    @Test
    public void UpdateTrainInfoTest_True() throws ServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApi.update(train)).thenReturn(true);
        boolean result_update_train = trainServiceApi.update(train);

        Assert.assertTrue(result_update_train);
        Mockito.verify(trainServiceApi,Mockito.times(1)).update(train);
    }

    @Test
    public void UpdateTrainInfoTest_Exception() throws ServiceException {
        Trains train = new Trains();
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        boolean result_update_train = false;
        Mockito.when(trainServiceApi.update(train)).thenThrow(new TrainServiceException("Id train equal null"));
        try {
             result_update_train = trainServiceApi.update(train);
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_update_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }
         Mockito.verify(trainServiceApi,Mockito.times(1)).update(train);
    }

    @Test
    public void DeleteTrainTest_True() throws ServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApi.delete(train)).thenReturn(true);
        boolean result_delete_train = trainServiceApi.delete(train);

        Assert.assertTrue(result_delete_train);
        Mockito.verify(trainServiceApi,Mockito.times(1)).delete(train);
    }

    @Test
    public void DeleteTrainTest_Exception() throws ServiceException {
        Trains train = new Trains();
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        boolean result_delete_train = true;
        Mockito.when(trainServiceApi.delete(train)).thenThrow(new TrainServiceException("Id train equal null"));

        try {
            result_delete_train = trainServiceApi.delete(new Trains());
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_delete_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }
        Mockito.verify(trainServiceApi,Mockito.times(1)).delete(new Trains());
    }

    @Test
    public void GetOneTrainTest_True() throws ServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApi.getOneById(1L)).thenReturn(train);
        Trains result_getonebyid_train = trainServiceApi.getOneById(1L);

        Assert.assertEquals("Train#1", result_getonebyid_train.getName_train());
        Mockito.verify(trainServiceApi,Mockito.times(1)).getOneById(1L);
    }

    @Test
    public void GetOneTrainTest_Exception() throws ServiceException {
        Trains result_getbyid_train = null;
        Mockito.when(trainServiceApi.getOneById(null)).thenThrow(new TrainServiceException("Id train equal null"));

        try {
            result_getbyid_train = trainServiceApi.getOneById(null);
        }catch (TrainServiceException trainServiceException){
            Assert.assertNull(result_getbyid_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }

        Mockito.verify(trainServiceApi,Mockito.times(1)).getOneById(null);
    }

    @Test
    public void FindAllTrainsTest_True() {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApi.FindAll()).thenReturn(Stream.of(train).collect(Collectors.toList()));
        boolean result_findall_trains = trainServiceApi.FindAll().size()>0;

        Assert.assertTrue(result_findall_trains);
        Mockito.verify(trainServiceApi,Mockito.times(1)).FindAll();
    }

    @Test(expected = Exception.class)
    public void FindAllTrainsTest_Exception() {
        Mockito.when(trainServiceApi.FindAll()).thenThrow(Exception.class);
        boolean result_findall_trains = trainServiceApi.FindAll().size()>0;

        Assert.assertFalse(result_findall_trains);
        Mockito.verify(trainServiceApi,Mockito.times(1)).FindAll();
    }

    @Test
    public void FindAllTrainByDateDepartureArrivalStationsTest_True() throws TrainServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);
        train.setDate_time_arrival(new Date(new java.util.Date().getTime()));
        Date date =  new Date(new java.util.Date().getTime());
        Mockito.when(trainServiceApi.FindAllByDateDepartureArrivalStations(date,date,
                Stations.BREST,Stations.MINSK)).
                thenReturn(Stream.of(train).collect(Collectors.toList()));
        boolean result_findallbydate_stations_trains = trainServiceApi.FindAllByDateDepartureArrivalStations
                (date,date,Stations.BREST,Stations.MINSK).size()>0;

        Assert.assertTrue(result_findallbydate_stations_trains);
        Mockito.verify(trainServiceApi,Mockito.times(1)).FindAllByDateDepartureArrivalStations(date,date,Stations.BREST,Stations.MINSK);
    }

    @Test
    public void FindAllTrainByDateDepartureArrivalStationsTest_Exception() throws TrainServiceException {
        Mockito.when(trainServiceApi.FindAllByDateDepartureArrivalStations(null,null,Stations.BREST,Stations.MINSK)).
        thenThrow(new TrainServiceException("Date eqaul null and error in stations"));
        boolean result_findallbydate_stations_trains = false;
        try {
          result_findallbydate_stations_trains =  trainServiceApi.FindAllByDateDepartureArrivalStations(null,null, Stations.BREST, Stations.MINSK).size()>0;
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_findallbydate_stations_trains);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Date eqaul null and error in stations");
        }
         Mockito.verify(trainServiceApi,Mockito.times(1)).FindAllByDateDepartureArrivalStations(null,null,Stations.BREST,Stations.MINSK);
    }

}