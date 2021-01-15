package com.railwayticket.controller;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.exception.ClientServiceException;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    final static Logger logger = Logger.getLogger(ClientController.class);

    @Qualifier("ClientServiceApiImplementation")
    @Autowired
    private ClientServiceApi clientServiceApi;



    @GetMapping("/")
    public String homePage(){
          logger.info("Load home page");
        return "index";
    }

    @GetMapping("/listClient")
    public String listClientPage(Model model) throws ServiceException {
        List<ClientRailway> ClientList = clientServiceApi.FindAll();
        logger.info("Client list page load. " + "List client size: " + ClientList.size() + " Time: " + new Date().toString());
        model.addAttribute("ClientList",ClientList);
        return "listclient";
    }

    @GetMapping("/deleteclient/{id}")
    public String deleteClientById(@PathVariable(name = "id")String id) throws ServiceException {
        ClientRailway clientDelete = clientServiceApi.getOneById(Long.valueOf(id));
        clientServiceApi.delete(clientDelete);
        logger.info("Delete client by id. " + " Client info: " + clientDelete.toString() + " Time: " + new Date().toString());
        return "redirect:/listClient";
    }

    @GetMapping("/EditClientInfo/{id}")
    public String UpdateClientById(@PathVariable(name = "id")String id, Model model) throws ServiceException {
        model.addAttribute("id",id);
        model.addAttribute("editClient",clientServiceApi.getOneById(Long.valueOf(id)));
        logger.info("Update client info page load." + " Client id: " + id + " Time: " + new Date().toString());
        return "editClient";
    }


    @PostMapping("/EditClientInfo/{id}")
    public String UpdateClientById(@RequestParam(name="name_client")String name_client,
                                    @RequestParam(name="soname_client")String soname_client,
                                    @RequestParam(name="phone_client")String phone_client,
                                    @PathVariable(name = "id")String id) throws ServiceException {

        ClientRailway client_update = clientServiceApi.getOneById(Long.valueOf(id));
        client_update.setId_client(Long.valueOf(id));
        client_update.setName_client(name_client);
        client_update.setSoname_client(soname_client);
        client_update.setPhone_client(phone_client);
        logger.info("Update client info." + " Client: " + client_update.toString() + " Time: " + new Date().toString());
        clientServiceApi.update(client_update);
        return "redirect:/listClient";
    }

    @PostMapping("/buyticket")
    public String buyTicket(@RequestParam(name = "name_client")String name_client,
                            @RequestParam(name = "soname_client")String soname_client,
                            @RequestParam(name = "phone_client")String phone_client,
                            @RequestParam(name="train_id")String id_train) throws ServiceException {
        ClientRailway clientRailway_save = new ClientRailway();
        clientRailway_save.setName_client(name_client);
        clientRailway_save.setSoname_client(soname_client);
        clientRailway_save.setPhone_client(phone_client);
        clientRailway_save.setDate_purchase(new java.sql.Date(new Date().getTime()));
        clientRailway_save.setId_train(Long.valueOf(id_train));

        logger.info("Save client info." + " Client: " + clientRailway_save.toString() + " Time: " + new Date().toString());

        clientServiceApi.save(clientRailway_save);

        return "redirect:/listClient";
    }

    @PostMapping("/FindClientByName")
    public String FindByName(@RequestParam(name = "name_client")String name_client,Model model) throws ClientServiceException {
        List<ClientRailway> ClientList;

        if(!name_client.equals("")) {
            ClientList = clientServiceApi.FindByNameClient(name_client);
            logger.info("Find client by name. " + "Name: " + name_client + " Time: " + new Date().toString());
        }else{
            ClientList = clientServiceApi.FindAll();
            logger.info("Find all client. " + "Name: Empty"  + " Time: " + new Date().toString());
        }

        model.addAttribute("ClientList", ClientList);

        return "listclient";
    }

}
