package com.artyomefimov.web.controller;

import com.artyomefimov.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // todo perform redirect in the front end
public class StartController {
    @GetMapping(value = "/")
    public String start() {
        return WebConstants.LOGIN_PAGE;
    }
}
