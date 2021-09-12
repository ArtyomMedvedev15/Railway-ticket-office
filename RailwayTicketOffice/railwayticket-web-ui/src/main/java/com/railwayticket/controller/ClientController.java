package com.railwayticket.controller;


import com.railwayticket.services_api.ClientServiceApi;
import com.railwayticket.services_api.MailSenderApi;
import com.railwayticket.services_api.exception.ClientServiceException;
import com.railwayticket.services_api.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    final static Logger logger = Logger.getLogger(ClientController.class);

    @Autowired
    private ClientServiceApi clientServiceApiRest;

    @Autowired
    private com.rest.ClientRestControllerApi clientRestApi;

    @Autowired
    private MailSenderApi mailSender;

    @GetMapping("/")
    public String homePage(){
          logger.info("Load home page");
        return "index";
    }

    @GetMapping("/listClient")
    public String listClientPage(Model model) throws ServiceException {
        try {
            List<com.rest.domains.ClientRailway> ClientList = clientRestApi.allClientUsingGET();
            logger.info("Client list page load. " + "List client size: " + ClientList.size() + " Time: " + new Date().toString());
            model.addAttribute("ClientList", ClientList);
        }catch (HttpClientErrorException ex){
            List<com.rest.domains.ClientRailway> ClientList = new ArrayList<>();
            model.addAttribute("ClientList", ClientList);
        }
        return "listclient";
    }

    @GetMapping("/deleteclient/{id}")
    public String deleteClientById(@PathVariable(name = "id")String id) throws ServiceException {
        com.rest.domains.ClientRailway clientDelete = clientRestApi.clientRailwayByIdUsingGET(Long.valueOf(id));
        clientRestApi.deleteClientUsingGET(Long.valueOf(id));
        logger.info("Delete client by id. " + " Client info: " + clientDelete.toString() + " Time: " + new Date().toString());
        return "redirect:/listClient";
    }

    @GetMapping("/EditClientInfo/{id}")
    public String UpdateClientById(@PathVariable(name = "id")String id, Model model) throws ServiceException {
        model.addAttribute("id",id);
        model.addAttribute("editClient",clientRestApi.clientRailwayByIdUsingGET(Long.valueOf(id)));
        logger.info("Update client info page load." + " Client id: " + id + " Time: " + new Date().toString());
        return "editClient";
    }


    @PostMapping("/EditClientInfo/{id}")
    public String UpdateClientById(@RequestParam(name="name_client")String name_client,
                                    @RequestParam(name="soname_client")String soname_client,
                                    @RequestParam(name="phone_client")String phone_client,
                                    @PathVariable(name = "id")String id) throws ServiceException {

        com.rest.domains.ClientRailway client_update = clientRestApi.clientRailwayByIdUsingGET(Long.valueOf(id));
        client_update.setIdClient(Long.valueOf(id));
        client_update.setNameClient(name_client);
        client_update.setSonameClient(soname_client);
        client_update.setPhoneClient(phone_client);
        logger.info("Update client info." + " Client: " + client_update.toString() + " Time: " + new Date().toString());
        clientRestApi.updateClientUsingPOST(client_update);
        return "redirect:/listClient";
    }

    @PostMapping("/buyticket")
    public String buyTicket(@RequestParam(name = "name_client")String name_client,
                            @RequestParam(name = "soname_client")String soname_client,
                            @RequestParam(name = "phone_client")String phone_client,
                            @RequestParam(name="train_id")String id_train, Model model) throws ServiceException {
        final DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        com.rest.domains.ClientRailway clientRailway_save = new com.rest.domains.ClientRailway();
        clientRailway_save.setIdTrain(Long.valueOf(id_train));
        clientRailway_save.setNameClient(name_client);
        clientRailway_save.setSonameClient(soname_client);
        clientRailway_save.setPhoneClient(phone_client);
        clientRailway_save.setDatePurchase(LocalDate.parse(LocalDate.now().toString(),date_formatter).toString());


        logger.info("Save client info." + " Client: " + clientRailway_save.toString() + " Time: " + new Date().toString());

        clientRestApi.saveClientUsingPOST(clientRailway_save);

        return "redirect:/listClient";
    }

    @PostMapping("/FindClientByName")
    public String FindByName(@RequestParam(name = "name_client")String name_client,Model model) throws ClientServiceException {
        List<com.rest.domains.ClientRailway> ClientList;

        if(!name_client.equals("")) {
            try {
                ClientList = clientRestApi.findAllClientByNameUsingGET(name_client);
                logger.info("Find client by name. " + "Name: " + name_client + " Time: " + new Date().toString());
            }catch (HttpClientErrorException ex){
                ClientList = Collections.emptyList();
            }
        }else{
            ClientList = clientRestApi.allClientUsingGET();
            logger.info("Find all client. " + "Name: Empty"  + " Time: " + new Date().toString());
        }

        model.addAttribute("ClientList", ClientList);

        return "listclient";
    }

    @GetMapping("/oneClient/{id}")
    public String oneClient(@PathVariable(name = "id")String id,Model model) throws ServiceException {
        model.addAttribute("oneClient",clientRestApi.clientRailwayByIdUsingGET(Long.valueOf(id)));
        logger.info("One client by id. " + " Client id: " + id);
        return "oneClient";
    }

    @PostMapping("/client/import/excel")
    public String ImportFromExcel(@RequestParam(name = "file") MultipartFile file){
        clientServiceApiRest.ImportExcel(file);
        logger.info("Import data to database from excel");
        return "redirect:/listClient";
    }

    @GetMapping("/contactform")
    public String contactForm(){
        return "contactForm";
    }

    @PostMapping("/contactform")
    public String sendContactForm(@RequestParam(name = "email")String email,
                                  @RequestParam(name = "subject")String subject,
                                  @RequestParam(name = "message")String message,
                                  @RequestParam(name = "file")MultipartFile file) throws MessagingException {
        try {
            mailSender.SendMessageWithAttchement(email,subject,message,file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/contactform";
    }

    @PostMapping("/client/import/xml")
    public String ImportFromXml(@RequestParam(name = "file") MultipartFile file){
        clientServiceApiRest.ImportXml(file);
        logger.info("Import data to database from xml");
        return "redirect:/listClient";
    }


 }
