package com.railwayticket.controller;

import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.ClientServiceApiImplementation;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    final static Logger logger = Logger.getLogger(ClientController.class);

    private ClientServiceApi clientServiceApi;

    @Autowired
    @Qualifier(value = "clientservice")
    public void setClientDao(ClientServiceApiImplementation clientServiceApiImplementation){
        this.clientServiceApi = clientServiceApiImplementation;
    }
    @GetMapping("/listClient")
    public String listClientPage(Model model) throws ServiceException {
        List<ClientRailway> ClientList = clientServiceApi.FindAll();
        logger.info("Client list page. " + "List client size: " + ClientList.size() + " Time: " + new Date().toString());
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
        logger.info("EDIT CLIENT");
        clientServiceApi.update(client_update);
        return "redirect:/listClient";
    }


}
