package com.railwayticket.service_rest;


import com.domain.ClientRailway;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
 import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    public void ImportExcel(MultipartFile file) {

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(convert(file)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity( base_url+"excel/import", request , Void.class );

        logger.info("Import excel to database");
    }

    @Override
    public void ImportXml(MultipartFile file) {
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(convert(file)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(bodyMap, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity( base_url+"xml/import", request , Void.class );

        logger.info("Import xml to database");
    }

    public static File convert(MultipartFile file)
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convFile;
    }
}
