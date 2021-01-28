package com.railwayticket.restclient.rest;



import com.domain.ClientRailway;
import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
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
@Api(value = "Client requests",description = "Operations for work with clients in railway tickets ")
public class ClientRestController {

    final static Logger logger = Logger.getLogger(ClientRestController.class);

    @Autowired
    private ClientServiceApi clientServiceApi;

    @RequestMapping(value = "{id}",method = RequestMethod.GET,produces  = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find client by id.",notes = "Allows you to get a single client by ID",
    response = ClientRailway.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully get client by id."),
            @ApiResponse(code = 400,message = "Error id client equal null"),
            @ApiResponse(code = 404,message = "Client was not found by this id")
    })
    public ResponseEntity<ClientRailway>clientRailwayById(@ApiParam(name = "id",value = "ID value for find client by id.",required = true) @PathVariable("id") Long id_client) throws ServiceException {

        if(id_client==null){
            logger.error("Get client by id failed. " + " Id: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ClientRailway clientRailway = clientServiceApi.getOneById(id_client);

        if(clientRailway==null){
            logger.error("Get client by id Failed. " + " Client not found"  + " With status Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("Get client by id. " + " Id: " + id_client + " With status OK");
        return new ResponseEntity<>(clientRailway,HttpStatus.OK);
    }

    @RequestMapping(value = "/saveClient",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Save new client.",notes = "Allows you to save new client to database.",
            response = ClientRailway.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Successfully save client."),
            @ApiResponse(code = 400,message = "Error client for save equal null"),
    })
    public ResponseEntity<ClientRailway>saveClient(@ApiParam(name = "client_save",value = "Client model for save new client with full field.",required = true)@RequestBody ClientRailway clientRailway) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientRailway==null){
            logger.error("Save client failed." + "Client: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientServiceApi.save(clientRailway);
        logger.info("Save new client." + " Client name: " + clientRailway.getName_client() + " With status Created");
        return new ResponseEntity<>(clientRailway,httpHeaders,HttpStatus.CREATED);

    }


    @RequestMapping(value = "/updateClient",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Update client.",notes = "Allows you to update client.",
            response = ClientRailway.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully update client."),
            @ApiResponse(code = 400,message = "Error client for update equal null"),
    })
    public ResponseEntity<ClientRailway>updateClient(@ApiParam(name = "client_update",value = "Client model for update client info with full field.",required = true)@RequestBody ClientRailway clientRailway, UriComponentsBuilder uriComponentsBuilder) throws ServiceException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if(clientRailway==null){
            logger.error("Update client failed." + " Client: null" + " With status Bad request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        clientServiceApi.update(clientRailway);
        logger.info("Update client. " + "Client: " + clientRailway.getName_client() + " With status OK");
        return new ResponseEntity<>(clientRailway,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteClient/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Delete client.",notes = "Allows you to delete client by id.",
            response = ClientRailway.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204,message = "Successfully delete client."),
            @ApiResponse(code = 404,message = "Error client for delete equal null"),
    })
    public ResponseEntity<ClientRailway>deleteClient(@ApiParam(name = "id",value = "ID value for delete client.",required = true)@PathVariable("id")Long id_client) throws ServiceException {
        ClientRailway clientRailwayDelete = clientServiceApi.getOneById(id_client);

        if(clientRailwayDelete==null){
            logger.error("Delete client failed. " + "Client: null" + " With status Not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientServiceApi.delete(clientRailwayDelete);
        logger.info("Delete client. " + "Client: " + clientRailwayDelete.toString() + " With status No content");

        return new ResponseEntity<>(clientRailwayDelete,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/allClient",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find all client.",notes = "Allows you to get all client.",
            response = Iterable.class,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully find all client."),
            @ApiResponse(code = 404,message = "List of client was empty"),
    })
    public ResponseEntity<List<ClientRailway>>AllClient(){
        List<ClientRailway>allClient = clientServiceApi.FindAll();

        if(allClient.isEmpty()){
            logger.error("Empty list. " + "List size: 0" + " With status No found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("All client. " + "List size: " + allClient.size() + "With status Ok");
        return new ResponseEntity<>(allClient,HttpStatus.OK);
    }


    @RequestMapping(value = "/findclientbyname/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find all client by name.",notes = "Allows you to get all by name client.",
            response = Iterable.class,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Successfully find all client by name."),
            @ApiResponse(code = 404,message = "List of client was empty"),
    })
    public ResponseEntity<List<ClientRailway>>FindAllClientByName(@ApiParam(name = "name",value = "Name value for find.",required = true)@PathVariable("name")String name) throws ClientServiceException {
        List<ClientRailway>allClient = clientServiceApi.FindByNameClient(name);

        if(allClient.isEmpty()){
            logger.error("Empty list. " + "List size: 0" + " With status No found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("All client by name. " + "Name: " + name + "With status Ok");
        return new ResponseEntity<>(allClient,HttpStatus.OK);
    }

}
