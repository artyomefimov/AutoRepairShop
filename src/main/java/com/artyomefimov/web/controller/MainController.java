package com.artyomefimov.web.controller;

import com.artyomefimov.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String start() {
        return WebConstants.LOGIN_PAGE;
    }
}
