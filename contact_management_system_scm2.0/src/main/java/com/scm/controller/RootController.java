package com.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entity.User;
import com.scm.helper.Helper;
import com.scm.services.UserServices;

@ControllerAdvice   // for using the all the 
public class RootController {
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);
    @Autowired
    public UserServices userServices;

     @ModelAttribute     //use to the rune every time when /user/... eany thing 
    public void addLoggdingUserInformation(Model model,Authentication authenticator){// to lode the data user to user page 
        if (authenticator == null) {
            return;
        }
        
        System.out.println("Adding logged in user information to the Model");
        String userName = Helper.getEmailFOLoggelUser(authenticator);
        logger.info("usser Email id in {}",userName);
         //get a  the  user name and  find the data to data basse 
         User user = userServices.getUserByEmail(userName);
         System.out.println(user);
         System.out.println(user.getName());
         System.out.println(user.getEmail());
         model.addAttribute("loggedinUser", user);

    }
    
}
