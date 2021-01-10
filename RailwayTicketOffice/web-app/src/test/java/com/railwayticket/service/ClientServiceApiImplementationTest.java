package com.railwayticket.service;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.exception.TrainServiceException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(JUnit4.class)
public class ClientServiceApiImplementationTest extends TestCase {

    private final ClientServiceApiImplementation clientServiceApiImplementation = mock(ClientServiceApiImplementation.class);

    @Test
    public void FindCLientByNameTest_True() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApiImplementation.FindByNameClient("Temp")).thenReturn(Optional.of(clientTest));

        Optional<ClientRailway> result_findbyname = clientServiceApiImplementation.FindByNameClient("Temp");
        Assert.assertTrue(result_findbyname.isPresent());

        Mockito.verify(clientServiceApiImplementation,times(1)).FindByNameClient("Temp");
    }

    @Test
    public void FindCLientByNameTest_Exception() throws ClientServiceException {
        Mockito.when(clientServiceApiImplementation.FindByNameClient(Mockito.any(String.class))).thenThrow( new ClientServiceException("Error name for find client equal null"));
        Optional<ClientRailway> result_findbyname = Optional.empty();
        try {
            result_findbyname = clientServiceApiImplementation.FindByNameClient(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_findbyname.isPresent());
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error name for find client equal null");
        }
        Mockito.verify(clientServiceApiImplementation,times(1)).FindByNameClient(null);
    }

    @Test
    public void SaveClientTest_True() throws ClientServiceException {
        ClientRailway clientRailway = new ClientRailway();
        clientRailway.setId_client(1L);
        clientRailway.setName_client("Temp");

        Mockito.when(clientServiceApiImplementation.save(clientRailway)).thenReturn(true);
        boolean result_save_client = clientServiceApiImplementation.save(clientRailway);

        Assert.assertTrue(result_save_client);
        Mockito.verify(clientServiceApiImplementation,times(1)).save(clientRailway);
    }

    @Test
    public void SaveClientTest_Exception() throws ClientServiceException {
        Mockito.when(clientServiceApiImplementation.save(Mockito.any(ClientRailway.class))).thenThrow( new ClientServiceException("Error client for save equal null"));
        boolean result_save_client = false;
        try {
             result_save_client = clientServiceApiImplementation.save(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_save_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error client for save equal null");
        }
         Mockito.verify(clientServiceApiImplementation,times(1)).save(null);
    }

    @Test
    public void UpdateClientTest_True() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApiImplementation.update(clientTest)).thenReturn(true);

        boolean result_update_client = clientServiceApiImplementation.update(clientTest);
        Assert.assertTrue(result_update_client);

        Mockito.verify(clientServiceApiImplementation,times(1)).update(clientTest);
    }

    @Test
    public void UpdateClientTest_Exception() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");

         Mockito.when(clientServiceApiImplementation.update(Mockito.any(ClientRailway.class))).thenThrow( new ClientServiceException("Error id client for update equal null"));
        boolean result_update_client = false;
        try {
            result_update_client = clientServiceApiImplementation.update(clientTest);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_update_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id client for update equal null");
        }
        Mockito.verify(clientServiceApiImplementation,times(1)).update(clientTest);
    }

    @Test
    public void DeleteClientTest_True() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApiImplementation.delete(clientTest)).thenReturn(true);

        boolean result_delete_client = clientServiceApiImplementation.delete(clientTest);
        Assert.assertTrue(result_delete_client);

        Mockito.verify(clientServiceApiImplementation,times(1)).delete(clientTest);
    }

    @Test
    public void DeleteClientTest_Exception() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");

        Mockito.when(clientServiceApiImplementation.delete(clientTest)).thenThrow(new ClientServiceException("Error id client for delete client equal null"));
        boolean result_delete_client = false;
        try {
            result_delete_client = clientServiceApiImplementation.delete(clientTest);
        }catch (ClientServiceException clientServiceException){
            Assert.assertFalse(result_delete_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id client for delete client equal null");
        }

        Mockito.verify(clientServiceApiImplementation,times(1)).delete(clientTest);
    }

    @Test
    public void GetOneByIdTest_True() throws ClientServiceException {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApiImplementation.getOneById(1L)).thenReturn(clientTest);

        ClientRailway result_getone_client = clientServiceApiImplementation.getOneById(1L);
        Assert.assertEquals("Temp", result_getone_client.getName_client());

        Mockito.verify(clientServiceApiImplementation,times(1)).getOneById(1L);
    }

    @Test
    public void GetOneByIdTest_Exception() throws ClientServiceException {
        Mockito.when(clientServiceApiImplementation.getOneById(null)).thenThrow(new ClientServiceException("Error id for get one equal null"));
        ClientRailway result_getone_client = null;
        try {
            result_getone_client = clientServiceApiImplementation.getOneById(null);
        }catch (ClientServiceException clientServiceException){
            Assert.assertNull(result_getone_client);
            Assert.assertTrue(clientServiceException instanceof ServiceException);
            Assert.assertEquals(clientServiceException.getMessage(), "Error id for get one equal null");
        }

        Mockito.verify(clientServiceApiImplementation,times(1)).getOneById(null);
    }


    @Test
    public void FindAllClientTest_True() {
        ClientRailway clientTest = new ClientRailway();
        clientTest.setName_client("Temp");
        clientTest.setId_client(1L);

        Mockito.when(clientServiceApiImplementation.FindAll()).thenReturn(Optional.of(clientTest));

        boolean result_find_all = clientServiceApiImplementation.FindAll().isPresent();

        Assert.assertTrue(result_find_all);
        Mockito.verify(clientServiceApiImplementation,times(1)).FindAll();
    }
}