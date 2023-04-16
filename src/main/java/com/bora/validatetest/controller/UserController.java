package com.bora.validatetest.controller;

import com.bora.validatetest.model.Request;
import com.bora.validatetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage() {
        return "user/dashboard";
    }

}