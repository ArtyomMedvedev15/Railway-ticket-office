package com.railwayticket.service;

import com.railwayticket.domain.Stations;
import com.railwayticket.domain.Trains;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.exception.TrainServiceException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class TrainServiceApiApiImplementationTest extends TestCase {


     private final TrainServiceApiApiImplementation trainServiceApiApiImplementation = mock(TrainServiceApiApiImplementation.class);

    @Test
    public void SaveNewTrainTest_True() throws TrainServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApiApiImplementation.save(train)).thenReturn(true);
        boolean result_save_train = trainServiceApiApiImplementation.save(train);
        Assert.assertTrue(result_save_train);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).save(train);
    }

    @Test
    public void SaveNewTrainTest_Exception() throws TrainServiceException {
         Mockito.when(trainServiceApiApiImplementation.save(Mockito.any(Trains.class))).thenThrow( new TrainServiceException("Error train for save equal null"));
        boolean result_save_train = true;
        try {
            result_save_train = trainServiceApiApiImplementation.save(null);
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_save_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Error train for save equal null");
        }
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).save(null);
    }

    @Test
    public void UpdateTrainInfoTest_True() throws TrainServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApiApiImplementation.update(train)).thenReturn(true);
        boolean result_update_train = trainServiceApiApiImplementation.update(train);

        Assert.assertTrue(result_update_train);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).update(train);
    }

    @Test
    public void UpdateTrainInfoTest_Exception() throws TrainServiceException {
        Trains train = new Trains();
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        boolean result_update_train = false;
        Mockito.when(trainServiceApiApiImplementation.update(train)).thenThrow(new TrainServiceException("Id train equal null"));
        try {
             result_update_train = trainServiceApiApiImplementation.update(train);
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_update_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }
         Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).update(train);
    }

    @Test
    public void DeleteTrainTest_True() throws TrainServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApiApiImplementation.delete(train)).thenReturn(true);
        boolean result_delete_train = trainServiceApiApiImplementation.delete(train);

        Assert.assertTrue(result_delete_train);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).delete(train);
    }

    @Test
    public void DeleteTrainTest_Exception() throws TrainServiceException {
        Trains train = new Trains();
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        boolean result_delete_train = true;
        Mockito.when(trainServiceApiApiImplementation.delete(train)).thenThrow(new TrainServiceException("Id train equal null"));

        try {
            result_delete_train = trainServiceApiApiImplementation.delete(new Trains());
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_delete_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).delete(new Trains());
    }

    @Test
    public void GetOneTrainTest_True() throws TrainServiceException {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApiApiImplementation.getOneById(1L)).thenReturn(train);
        Trains result_getonebyid_train = trainServiceApiApiImplementation.getOneById(1L);

        Assert.assertEquals("Train#1", result_getonebyid_train.getName_train());
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).getOneById(1L);
    }

    @Test
    public void GetOneTrainTest_Exception() throws TrainServiceException {
        Trains result_getbyid_train = null;
        Mockito.when(trainServiceApiApiImplementation.getOneById(null)).thenThrow(new TrainServiceException("Id train equal null"));

        try {
            result_getbyid_train = trainServiceApiApiImplementation.getOneById(null);
        }catch (TrainServiceException trainServiceException){
            Assert.assertNull(result_getbyid_train);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Id train equal null");
        }

        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).getOneById(null);
    }

    @Test
    public void FindAllTrainsTest_True() {
        Trains train = new Trains();
        train.setId_train(1L);
        train.setName_train("Train#1");
        train.setArrivalStation(Stations.BREST);
        train.setDepartureStation(Stations.MINSK);
        train.setTotal_ticket(123);

        Mockito.when(trainServiceApiApiImplementation.FindAll()).thenReturn(Optional.of(train));
        boolean result_findall_trains = trainServiceApiApiImplementation.FindAll().isPresent();

        Assert.assertTrue(result_findall_trains);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).FindAll();
    }

    @Test(expected = Exception.class)
    public void FindAllTrainsTest_Exception() {
        Mockito.when(trainServiceApiApiImplementation.FindAll()).thenThrow(Exception.class);
        boolean result_findall_trains = trainServiceApiApiImplementation.FindAll().isPresent();

        Assert.assertFalse(result_findall_trains);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).FindAll();
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
        Mockito.when(trainServiceApiApiImplementation.FindAllByDateDepartureArrivalStations(date,
                Stations.BREST,Stations.MINSK)).
                thenReturn(Optional.of(train));
        boolean result_findallbydate_stations_trains = trainServiceApiApiImplementation.FindAllByDateDepartureArrivalStations
                (date,Stations.BREST,Stations.MINSK).isPresent();

        Assert.assertTrue(result_findallbydate_stations_trains);
        Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).FindAllByDateDepartureArrivalStations(date,Stations.BREST,Stations.MINSK);
    }

    @Test
    public void FindAllTrainByDateDepartureArrivalStationsTest_Exception() throws TrainServiceException {
        Mockito.when(trainServiceApiApiImplementation.FindAllByDateDepartureArrivalStations(null,Stations.BREST,Stations.MINSK)).
        thenThrow(new TrainServiceException("Date eqaul null and error in stations"));
        boolean result_findallbydate_stations_trains = false;
        try {
          result_findallbydate_stations_trains =  trainServiceApiApiImplementation.FindAllByDateDepartureArrivalStations(null, Stations.BREST, Stations.MINSK).isPresent();
        }catch (TrainServiceException trainServiceException){
            Assert.assertFalse(result_findallbydate_stations_trains);
            Assert.assertTrue(trainServiceException instanceof ServiceException);
            Assert.assertEquals(trainServiceException.getMessage(), "Date eqaul null and error in stations");
        }
         Mockito.verify(trainServiceApiApiImplementation,Mockito.times(1)).FindAllByDateDepartureArrivalStations(null,Stations.BREST,Stations.MINSK);
    }

}