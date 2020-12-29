package com.railwayticket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/listTrain")
    public String listTrainPage(Model model){
        return "listtrain";
    }

    @GetMapping("/listClient")
    public String listClientPage(Model model){
        return "listclient";
    }

    @GetMapping("/oneTrain/{id}")
    public String oneTrainPage(Model model){
        return "onetrain";
    }

    @PostMapping("/findTrain")
    public String resultFindTrainPage(Model model){
        return "resultfindtrain";
    }
}
