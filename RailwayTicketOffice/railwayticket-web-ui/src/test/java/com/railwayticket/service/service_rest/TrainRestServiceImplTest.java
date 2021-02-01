package com.railwayticket.service.service_rest;

import com.domain.Stations;
import com.domain.Trains;
import com.domain.TypeTrain;
import com.railwayticket.config.BeanConfig;
import com.railwayticket.config.DispatcherServletInitializer;

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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class)
@SpringBootTest
public class TrainRestServiceImplTest extends TestCase {

    @MockBean
    @Qualifier("TrainServiceRestImpl")
    private TrainServiceApi trainServiceApi;

    @Test
    public void SaveTrain_ThenTrue() throws ServiceException {
        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,22.3F);

        Mockito.when(trainServiceApi.save(trains)).thenReturn(true);

        boolean result_save = trainServiceApi.save(trains);

        assertTrue(result_save);

        Mockito.verify(trainServiceApi,Mockito.times(1)).save(trains);
    }

    @Test
    public void SaveTrain_ThenFalse() throws ServiceException {
        Mockito.when(trainServiceApi.save(null)).thenReturn(false);

        boolean result_save = trainServiceApi.save(null);

        assertFalse(result_save);

        Mockito.verify(trainServiceApi,Mockito.times(1)).save(null);
    }

    @Test
    public void UpdateTrain_ThenTrue() throws ServiceException {
        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,22.3F);
        trains.setId_train(1L);

        Mockito.when(trainServiceApi.update(trains)).thenReturn(true);

        boolean result_update = trainServiceApi.update(trains);

        assertTrue(result_update);

        Mockito.verify(trainServiceApi,Mockito.times(1)).update(trains);
    }

    @Test
    public void UpdateTrain_ThenFalse() throws ServiceException {

        Mockito.when(trainServiceApi.update(null)).thenReturn(false);

        boolean result_update = trainServiceApi.update(null);

        assertFalse(result_update);

        Mockito.verify(trainServiceApi,Mockito.times(1)).update(null);
    }

    @Test
    public void DeleteTrain_ThenTrue() throws ServiceException {
        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,22.3F);
        trains.setId_train(1L);

        Mockito.when(trainServiceApi.delete(trains)).thenReturn(true);

        boolean result_delete = trainServiceApi.delete(trains);

        assertTrue(result_delete);

        Mockito.verify(trainServiceApi,Mockito.times(1)).delete(trains);
    }

    @Test
    public void DeleteTrain_ThenFalse() throws ServiceException {
        Mockito.when(trainServiceApi.delete(null)).thenReturn(false);

        boolean result_delete = trainServiceApi.delete(null);

        assertFalse(result_delete);

        Mockito.verify(trainServiceApi,Mockito.times(1)).delete(null);
    }

    @Test
    public void GetOneById_ThenTrainWithNameTemp() throws ServiceException {
        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,22.3F);
        trains.setId_train(1L);

        Mockito.when(trainServiceApi.getOneById(1L)).thenReturn(trains);

        Trains result_getbyid = trainServiceApi.getOneById(1L);

        Assert.assertEquals("TempTrain",result_getbyid.getName_train());

        Mockito.verify(trainServiceApi,Mockito.times(1)).getOneById(1L);
    }

    @Test
    public void FindAll_ThenListOf4() {
        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,new Date(new java.util.Date().getTime()),
                new Date(new java.util.Date().getTime()),123,123,22.3F);
        trains.setId_train(1L);

        Mockito.when(trainServiceApi.FindAll()).thenReturn(Stream.of(trains,trains,trains,trains).collect(Collectors.toList()));

        List<Trains> result_getbyid = trainServiceApi.FindAll();

        assertEquals(4, result_getbyid.size());

        Mockito.verify(trainServiceApi,Mockito.times(1)).FindAll();
    }

    @Test
    public void FindAllByDateDepartureArrivalStations_ThenListof2() throws TrainServiceException {
        Date date = new Date(new java.util.Date().getTime());

        Trains trains = new Trains("TempTrain", TypeTrain.ECONOM, Stations.BREST,Stations.GRODNO,date,
                date,123,123,22.3F);
        trains.setId_train(1L);

        Mockito.when(trainServiceApi.FindAllByDateDepartureArrivalStations(date,
                date,Stations.BREST,Stations.GRODNO)).thenReturn(Stream.of(trains,trains).collect(Collectors.toList()));

        List<Trains> result_find_dates_stations = trainServiceApi.FindAllByDateDepartureArrivalStations(date,
                date,Stations.BREST,Stations.GRODNO);

        assertEquals(2, result_find_dates_stations.size());

        Mockito.verify(trainServiceApi,Mockito.times(1)).FindAllByDateDepartureArrivalStations(date,
                date,Stations.BREST,Stations.GRODNO);
    }

}