package com.artyomefimov.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class UserController {
    @RequestMapping("/login")
    public String login() {
        return "static/login";
    }
}
