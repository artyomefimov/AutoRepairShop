package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(WebConstants.LOGIN_PAGE)
public class LoginController {
    private static final String WORKSHOP_LIST = "workshop_list";

    private WorkshopRepository workshopRepository;

    @Autowired
    public LoginController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping
    public String loginPage(Model model) {
        List<Workshop> workshopList = workshopRepository.findAll();
        model.addAttribute(WORKSHOP_LIST, workshopList);
        return WebConstants.WORKSHOPS_PAGE;
    }
}
