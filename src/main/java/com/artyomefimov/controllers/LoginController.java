package com.artyomefimov.controllers;

import com.artyomefimov.Constants;
import com.artyomefimov.database.DatabaseException;
import com.artyomefimov.database.dao.AbstractDao;
import com.artyomefimov.database.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private AbstractDao<Workshop> workshopDao;

    @Autowired
    public LoginController(
            @Qualifier(Constants.WORKSHOP_DAO) AbstractDao<Workshop> workshopDao) {
        this.workshopDao = workshopDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {
        try {
            List<Workshop> workshopList = workshopDao.getAll(Workshop.class);
            model.addAttribute("list", workshopList);
            return "breakdown_types";
        } catch (DatabaseException e) { // todo proper exception handling
            e.printStackTrace();
            return "error";
        }
    }
}
