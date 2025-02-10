package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {
    //user dashbaord page 
    @RequestMapping(value="/dashbaord")
    public String userDashbaord() {
        return "user/dashbaord";
    }
    @RequestMapping(value="/profile")
    public String userProfile() {
        return "user/profile";
    }

    //user add contacts page 

    // user view contacts

    // user edit contacts 

    //user delete contacts 

    //user search contacts 

    //
}
