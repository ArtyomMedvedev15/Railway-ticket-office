package com.railwayticket.rest;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/clients/")
public class ClientRestController {

    @Autowired
    private ClientServiceApi clientServiceApi;

    @RequestMapping(value = "{id}",method = RequestMethod.GET,produces  = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientRailway>clientRailwayById(@PathVariable("id") Long id_client) throws ServiceException {

        if(id_client==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientRailway clientRailway = clientServiceApi.getOneById(id_client);

        if(clientRailway==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clientRailway,HttpStatus.OK);
    }

    @RequestMapping(value = "/saveClient",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientRailway>saveClient(@RequestBody ClientRailway clientRailway) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientRailway==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientServiceApi.save(clientRailway);

        return new ResponseEntity<>(clientRailway,httpHeaders,HttpStatus.CREATED);

    }

    @RequestMapping(value = "/updateClient",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientRailway>updateClient(@RequestBody ClientRailway clientRailway, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientRailway==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientServiceApi.update(clientRailway);

        return new ResponseEntity<>(clientRailway,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteClient/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientRailway>deleteClient(@PathVariable("id")Long id_client) throws ServiceException {
        ClientRailway clientRailwayDelete = clientServiceApi.getOneById(id_client);

        if(clientRailwayDelete==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientServiceApi.delete(clientRailwayDelete);

        return new ResponseEntity<>(clientRailwayDelete,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/allClient",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ClientRailway>>AllClient(){
        List<ClientRailway>allClient = clientServiceApi.FindAll();

        if(allClient.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allClient,HttpStatus.OK);
    }


    @RequestMapping(value = "/findclientbyname/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ClientRailway>>FindAllClientByName(@PathVariable("name")String name) throws ClientServiceException {
        List<ClientRailway>allClient = clientServiceApi.FindByNameClient(name);

        if(allClient.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allClient,HttpStatus.OK);
    }

}
