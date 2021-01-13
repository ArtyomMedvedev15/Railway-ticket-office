package com.railwayticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.log4j.Logger;

@Controller
public class HomeController {
    final static Logger logger = Logger.getLogger(HomeController.class);

    @GetMapping("/")
    public String homePage(){
        logger.info("Load home page");
        return "index";
    }

    @GetMapping("/listTrain")
    public String listTrainPage(Model model){
        logger.info("Load list of train page");
        return "listtrain";
    }

    @GetMapping("/listClient")
    public String listClientPage(Model model){
        logger.info("Load list of client page");
        return "listclient";
    }

    @GetMapping("/oneTrain/{id}")
    public String oneTrainPage(Model model){
        logger.info("Load one train by id page");
        return "onetrain";
    }

    @PostMapping("/findTrain")
    public String resultFindTrainPage(Model model){
        logger.info("Find train by few param");
        return "resultfindtrain";
    }
}
