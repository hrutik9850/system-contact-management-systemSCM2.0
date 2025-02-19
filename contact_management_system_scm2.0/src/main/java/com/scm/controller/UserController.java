package com.scm.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.entity.User;
import com.scm.helper.Helper;
import com.scm.services.UserServices;



@Controller
@RequestMapping("/user")
public class UserController {
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
   @Autowired
   private UserServices userServices;
    //user dashbaord page 


    @RequestMapping(value="/dashbaord")
    public String userDashbaord() {
        return "user/dashbaord";
    }
    @RequestMapping(value="/profile")
    //Authentication user kalya ver jo pan user profile pagle la eato tychi ditail eate 
    // Model are using to show the data form UI 
    public String userProfile(Model model, Authentication authenticator) {
   

        
        return "user/profile";
    }

    //user add contacts page 

    // user view contacts

    // user edit contacts 

    //user delete contacts 

    //user search contacts 

    //
}
