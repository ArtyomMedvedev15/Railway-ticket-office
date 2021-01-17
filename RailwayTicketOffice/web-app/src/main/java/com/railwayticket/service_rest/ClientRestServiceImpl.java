package com.railwayticket.service_rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.railwayticket.domain.ClientRailway;
import com.railwayticket.rest.ClientRestController;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class ClientRestServiceImpl implements ClientServiceApi {

    @Autowired
    private RestTemplate restTemplate;

    final static Logger logger = Logger.getLogger(ClientRestServiceImpl.class);

    @Override
    public boolean save(ClientRailway clientRailway) throws ServiceException {
            ResponseEntity<String> response = restTemplate.postForEntity( "http://localhost:8181/api/clients/saveClient", clientRailway , String.class );
            if(response.getStatusCode().toString().equals("CREATED")){
                logger.info("Save new client. " + "Client name: " + clientRailway.getName_client() + "With status Created");
                return true;
            }else{
                logger.error("Save new client failed. " + "Client: null" + "With status Bad request");
                return false;
            }
    }

    @Override
    public boolean update(ClientRailway clientRailway) throws ServiceException {
        ResponseEntity<String> response_request_update = restTemplate.postForEntity( "http://localhost:8181/api/clients/updateClient", clientRailway , String.class );
        if(response_request_update.getStatusCode().toString().equals("OK")){
            logger.info("Update client. " + "Client name: " + clientRailway.getName_client() + "With status Ok");
            return true;
        }else{
            logger.error("Update client failed. " + "Client: null" + "With status Bad request");
            return false;
        }
     }

    @Override
    public boolean delete(ClientRailway clientRailway) throws ServiceException {
        ResponseEntity<String> result_request_delete = restTemplate.getForEntity("http://localhost:8181/api/clients/deleteClient/"+clientRailway.getId_client(),String.class);

        if(result_request_delete.getStatusCode().toString().equals("NO_CONTENT")){
            logger.info("Delete client. " + "Client name: " + clientRailway.getName_client() + "With status No content");
            return true;
        }else{
            logger.error("Delete client failed. " + "Client: null" + "With status Bad request");
            return false;
        }

    }

    @Override
    public ClientRailway getOneById(Long id) throws ServiceException {
        ClientRailway clientRailway = restTemplate.getForObject("http://localhost:8181/api/clients/"+id,ClientRailway.class);
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
        List<ClientRailway> clientRailwayAllClient =  Arrays.asList(restTemplate.getForObject("http://localhost:8181/api/clients/allClient",ClientRailway[].class).clone());

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
        List<ClientRailway> clientRailwayAllClientByName =  Arrays.asList(restTemplate.getForObject("http://localhost:8181/api/clients/findclientbyname/"+name_client,ClientRailway[].class).clone());

        if (clientRailwayAllClientByName!=null){
            logger.info("Get all client by name. " + " List size: " + clientRailwayAllClientByName.size() + " With status Ok");
            return clientRailwayAllClientByName;
        }else{
            logger.info("Get all client by name failed. " + " List size: 0" + " With status not found");
            return null;
        }

    }

}
