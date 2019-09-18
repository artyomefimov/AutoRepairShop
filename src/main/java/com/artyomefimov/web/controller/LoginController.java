package com.artyomefimov.web.controller;

import com.artyomefimov.database.repository.WorkshopRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(WebConstants.LOGIN_PAGE)
public class LoginController {
    private WorkshopRepository workshopRepository;

    @Autowired
    public LoginController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping
    public String loginPage(Model model) {
        model.addAttribute(
                WebConstants.WORKSHOPS_ATTRIBUTE,
                workshopRepository.findAll());
        return WebConstants.WORKSHOPS_PAGE;
    }
}
