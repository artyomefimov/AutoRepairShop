package com.artyomefimov.controllers;

import com.artyomefimov.database.model.BreakdownType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.artyomefimov.repositories.BreakdownTypeRepository;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private BreakdownTypeRepository breakdownTypeRepository;

    @Autowired
    public LoginController(BreakdownTypeRepository breakdownTypeRepository) {
        this.breakdownTypeRepository = breakdownTypeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {
        List<BreakdownType> breakdownTypeList = breakdownTypeRepository.getAll();
        model.addAttribute("list", breakdownTypeList);
        return "breakdown_types";
    }
}
