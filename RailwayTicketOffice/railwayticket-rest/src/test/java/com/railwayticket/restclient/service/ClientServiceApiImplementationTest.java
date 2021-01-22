package com.railwayticket.restclient.service;

 import com.domain.ClientRailway;
 import com.railwayticket.restclient.config.BeanConfig;
import com.railwayticket.restclient.config.DispatcherServletInitializer;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@SpringBootTest
@WebAppConfiguration
public class ClientServiceApiImplementationTest extends TestCase {

    @MockBean
    @Qualifier("ClientServiceApiImplementation")
    public ClientServiceApi clientServiceApi;

    @Test
    public void FindCLientByNameTest_True() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApi.FindByNameClient("Temp")).thenReturn(Stream.of(clientTest).collect(Collectors.toList()));

        List<ClientRailway> result_findbyname = clientServiceApi.FindByNameClient("Temp");
        Assert.assertTrue(result_findbyname.size()>0);

        Mockito.verify(clientServiceApi,times(1)).FindByNameClient("Temp");
    }

    @Test
    public void FindCLientByNameTest_Exception() throws ClientServiceException {
        Mockito.when(clientServiceApi.FindByNameClient(Mockito.any(String.class))).thenThrow( new ClientServiceException("Error name for find client equal null"));
        List<ClientRailway> result_findbyname = Collections.emptyList();
        try {
            result_findbyname = clientServiceApi.FindByNameClient(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_findbyname.size()>0);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error name for find client equal null");
        }
        Mockito.verify(clientServiceApi,times(1)).FindByNameClient(null);
    }

    @Test
    public void SaveClientTest_True() throws ServiceException {
        ClientRailway clientRailway = new ClientRailway();
        clientRailway.setId_client(1L);
        clientRailway.setName_client("Temp");

        Mockito.when(clientServiceApi.save(clientRailway)).thenReturn(true);
        boolean result_save_client = clientServiceApi.save(clientRailway);

        Assert.assertTrue(result_save_client);
        Mockito.verify(clientServiceApi,times(1)).save(clientRailway);
    }

    @Test
    public void SaveClientTest_Exception() throws ServiceException {
        Mockito.when(clientServiceApi.save(null)).thenThrow( new ClientServiceException("Error client for save equal null"));
        boolean result_save_client = false;
        try {
             result_save_client = clientServiceApi.save(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_save_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error client for save equal null");
        }
         Mockito.verify(clientServiceApi,times(1)).save(null);
    }

    @Test
    public void UpdateClientTest_True() throws ServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApi.update(clientTest)).thenReturn(true);

        boolean result_update_client = clientServiceApi.update(clientTest);
        Assert.assertTrue(result_update_client);

        Mockito.verify(clientServiceApi,times(1)).update(clientTest);
    }

    @Test
    public void UpdateClientTest_Exception() throws ServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");

         Mockito.when(clientServiceApi.update(Mockito.any(ClientRailway.class))).thenThrow( new ClientServiceException("Error id client for update equal null"));
        boolean result_update_client = false;
        try {
            result_update_client = clientServiceApi.update(clientTest);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_update_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id client for update equal null");
        }
        Mockito.verify(clientServiceApi,times(1)).update(clientTest);
    }

    @Test
    public void DeleteClientTest_True() throws ServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApi.delete(clientTest)).thenReturn(true);

        boolean result_delete_client = clientServiceApi.delete(clientTest);
        Assert.assertTrue(result_delete_client);

        Mockito.verify(clientServiceApi,times(1)).delete(clientTest);
    }

    @Test
    public void DeleteClientTest_Exception() throws ServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");

        Mockito.when(clientServiceApi.delete(clientTest)).thenThrow(new ClientServiceException("Error id client for delete client equal null"));
        boolean result_delete_client = false;
        try {
            result_delete_client = clientServiceApi.delete(clientTest);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_delete_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id client for delete client equal null");
        }

        Mockito.verify(clientServiceApi,times(1)).delete(clientTest);
    }

    @Test
    public void GetOneByIdTest_True() throws ServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApi.getOneById(1L)).thenReturn(clientTest);

        ClientRailway result_getone_client = clientServiceApi.getOneById(1L);
        Assert.assertEquals("Temp", result_getone_client.getName_client());

        Mockito.verify(clientServiceApi,times(1)).getOneById(1L);
    }

    @Test
    public void GetOneByIdTest_Exception() throws ServiceException {
        Mockito.when(clientServiceApi.getOneById(null)).thenThrow(new ClientServiceException("Error id for get one equal null"));
        ClientRailway result_getone_client = null;
        try {
            result_getone_client = clientServiceApi.getOneById(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertNull(result_getone_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id for get one equal null");
        }

        Mockito.verify(clientServiceApi,times(1)).getOneById(null);
    }


    @Test
    public void FindAllClientTest_True() {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApi.FindAll()).thenReturn(Stream.of(clientTest).collect(Collectors.toList()));

        boolean result_find_all = clientServiceApi.FindAll().size()>0;

        Assert.assertTrue(result_find_all);
        Mockito.verify(clientServiceApi,times(1)).FindAll();
    }
}