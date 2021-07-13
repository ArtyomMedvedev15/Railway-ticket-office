package com.railwayticket.service_rest;


import com.domain.ClientRailway;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
 import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClientRestServiceImpl implements ClientServiceApi {

    @Autowired
    private RestTemplate restTemplate;

    final static Logger logger = Logger.getLogger(ClientRestServiceImpl.class);

    private final String base_url = "http://localhost:8181/api/clients/";

    @Override
    public boolean save(ClientRailway clientRailway) throws ServiceException {
            ResponseEntity<String> response = restTemplate.postForEntity( base_url+"saveClient", clientRailway , String.class );
            if(response.getStatusCode().toString().equals("201 CREATED")){
                logger.info("Save new client. " + "Client name: " + clientRailway.getName_client() + "With status Created");
                return true;
            }else{
                logger.error("Save new client failed. " + "Client: null" + "With status Bad request");
                return false;
            }
    }

    @Override
    public boolean update(ClientRailway clientRailway) throws ServiceException {
        ResponseEntity<String> response_request_update = restTemplate.postForEntity( base_url+"updateClient", clientRailway , String.class );
        if(response_request_update.getStatusCode().toString().equals("200 OK")){
            logger.info("Update client. " + "Client name: " + clientRailway.getName_client() + "With status Ok");
            return true;
        }else{
            logger.error("Update client failed. " + "Client: null" + "With status Bad request");
            return false;
        }
     }

    @Override
    public boolean delete(ClientRailway clientRailway) throws ServiceException {
        ResponseEntity<String> result_request_delete = restTemplate.getForEntity(base_url+"deleteClient/"+clientRailway.getId_client(),String.class);

        if(result_request_delete.getStatusCode().toString().equals("204 NO_CONTENT")){
            logger.info("Delete client. " + "Client name: " + clientRailway.getName_client() + "With status No content");
            return true;
        }else{
            logger.error("Delete client failed. " + "Client: null" + "With status Bad request");
            return false;
        }

    }

    @Override
    public ClientRailway getOneById(Long id) throws ServiceException {
        ClientRailway clientRailway = restTemplate.getForObject(base_url+id,ClientRailway.class);
        if (clientRailway != null) {
            logger.info("Get one client. " + "Client name: " + clientRailway.getName_client() + "With status OK");
            return clientRailway;
        }else{
            logger.error("Get one clientt failed. " + "Client: null" + "With status Bad request");
            return null;
        }
    }

    @Override
    public List<ClientRailway> FindAll() {
        List<ClientRailway> clientRailwayAllClient =  Arrays.asList(restTemplate.getForObject(base_url+"allClient",ClientRailway[].class).clone());

        if (clientRailwayAllClient!=null){
            logger.info("Get all client. " + " List size: " + clientRailwayAllClient.size() + " With status Ok");
            return clientRailwayAllClient;
        }else{
            logger.info("Get all client failed. " + " List size: 0" + " With status not found");
            return null;
        }

    }

    @Override
    public List<ClientRailway> FindByNameClient(String name_client) throws ClientServiceException {
        List<ClientRailway> clientRailwayAllClientByName =  Arrays.asList(restTemplate.getForObject(base_url+"findclientbyname/"+name_client,ClientRailway[].class).clone());

        if (name_client != null){
            logger.info("Get all client by name. " + " List size: " + clientRailwayAllClientByName.size() + " With status Ok");
            return clientRailwayAllClientByName;
        }else{
            logger.info("Get all client by name failed. " + " List size: 0" + " With status not found");
            return null;
        }

    }

    @Override
    public void ExportToExcel() {
       logger.info("Export client to excel file" + new Date());
       restTemplate.getForEntity(base_url+"listclients/export/excel",Void.class);
    }

}
