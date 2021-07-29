package com.railwayticket.service_rest.service_rest;

import com.domain.ClientRailway;
import com.railwayticket.service_rest.config.BeanConfig;
import com.railwayticket.service_rest.config.DispatcherServletInitializer;
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

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class, DispatcherServletInitializer.class})
@SpringBootTest
@WebAppConfiguration
public class ClientRestServiceImplTest extends TestCase {

    @MockBean
    @Qualifier("ClientServiceRestImpl")
    private ClientServiceApi clientServiceRestApi;


    @Test
    public void SaveClient_ThenTrue() throws ServiceException {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"TempClient","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");
        Mockito.when(clientServiceRestApi.save(clientRailway)).thenReturn(true);

        boolean result_save = clientServiceRestApi.save(clientRailway);

        assertTrue(result_save);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).save(clientRailway);
     }

    @Test
    public void SaveClient_ThenFalse() throws ServiceException {
        Mockito.when(clientServiceRestApi.save(null)).thenReturn(false);

        boolean result_save = clientServiceRestApi.save(null);

        assertFalse(result_save);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).save(null);
    }

    @Test
    public void UpdateClient_ThenTrue() throws ServiceException {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"TempClient","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");
        Mockito.when(clientServiceRestApi.update(clientRailway)).thenReturn(true);

        boolean result_update = clientServiceRestApi.update(clientRailway);

        assertTrue(result_update);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).update(clientRailway);
    }

    @Test
    public void UpdateClient_ThenFalse() throws ServiceException {
        Mockito.when(clientServiceRestApi.update(null)).thenReturn(false);

        boolean result_update = clientServiceRestApi.update(null);

        assertFalse(result_update);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).update(null);
    }


    @Test
    public void DeleteClient_ThenTrue() throws ServiceException {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"TempClient","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");
        Mockito.when(clientServiceRestApi.delete(clientRailway)).thenReturn(true);

        boolean result_delete = clientServiceRestApi.delete(clientRailway);

        assertTrue(result_delete);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).delete(clientRailway);
    }

    @Test
    public void DeleteClient_ThenFalse() throws ServiceException {
         Mockito.when(clientServiceRestApi.delete(null)).thenReturn(false);

        boolean result_delete = clientServiceRestApi.delete(null);

        assertFalse(result_delete);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).delete(null);
    }

    @Test
    public void GetOneById_ThenClientWithNameTest() throws ServiceException {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"Test","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");

        Mockito.when(clientServiceRestApi.getOneById(123L)).thenReturn(clientRailway);

        ClientRailway result_getbyid = clientServiceRestApi.getOneById(123L);

        Assert.assertEquals("Test", result_getbyid.getName_client());

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).getOneById(123L);
    }

    @Test
    public void GetOneById_ThenClientNull() throws ServiceException {
        Mockito.when(clientServiceRestApi.getOneById(null)).thenReturn(null);

        ClientRailway result_getbyid = clientServiceRestApi.getOneById(null);

        Assert.assertNull(result_getbyid);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).getOneById(null);
    }

    @Test
    public void FindAll_ThenListof3() {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"TempClient","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");
        Mockito.when(clientServiceRestApi.FindAll()).thenReturn(Stream.of(clientRailway,clientRailway,clientRailway).collect(Collectors.toList()));

        List<ClientRailway> result_find_all = clientServiceRestApi.FindAll();

        assertEquals(3, result_find_all.size());

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).FindAll();
    }

    @Test
    public void FindAllByName_ThenListOf2() throws ClientServiceException {
        ClientRailway clientRailway = new ClientRailway(123L,123L,"TempClient","TempClient",new Date(new java.util.Date().getTime()),"TempPhone");
        Mockito.when(clientServiceRestApi.FindByNameClient("Temp")).thenReturn(Stream.of(clientRailway,clientRailway).collect(Collectors.toList()));

        List<ClientRailway> result_find_all = clientServiceRestApi.FindByNameClient("Temp");

        assertEquals(2, result_find_all.size());

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).FindByNameClient("Temp");
    }

    @Test
    public void FindAllByName_ThenListNull() throws ClientServiceException {
        Mockito.when(clientServiceRestApi.FindByNameClient(null)).thenReturn(null);

        List<ClientRailway> result_find_all = clientServiceRestApi.FindByNameClient(null);

        assertNull(result_find_all);

        Mockito.verify(clientServiceRestApi,Mockito.times(1)).FindByNameClient(null);
    }
}