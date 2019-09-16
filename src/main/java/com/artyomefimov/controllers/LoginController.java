package com.artyomefimov.controllers;

import com.artyomefimov.database.dao.WorkshopRepository;
import com.artyomefimov.database.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private WorkshopRepository workshopRepository;

    @Autowired
    public LoginController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {
        List<Workshop> workshopList = workshopRepository.findAll();
        model.addAttribute("list", workshopList);
        return "breakdown_types";
    }
}
