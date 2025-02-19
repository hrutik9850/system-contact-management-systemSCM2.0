package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")

public class ContactController {
    @RequestMapping("/add")
    public String showAddContactPage(){
        return"/user/addContact";
    }
    @RequestMapping("/showAllContact")
    public String showAllContact(){
        return"/user/showAllContact";
    }
    
}
