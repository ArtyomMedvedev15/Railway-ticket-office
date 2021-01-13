package com.railwayticket.controller;

import com.railwayticket.dao.ClientDaoImplementation;
import com.railwayticket.domain.ClientRailway;
import com.railwayticket.service.ClientServiceApiImplementation;
import com.railwayticket.service.exception.ServiceException;
import com.railwayticket.service.servic_api.ClientServiceApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

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
