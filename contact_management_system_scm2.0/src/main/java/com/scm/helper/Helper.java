package com.scm.helper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {
    public static  String getEmailFOLoggelUser(Authentication authentication){
        // ager hame sing in Email and password kay hay to user name kas kadcha 
        String userName ="";
        if (authentication instanceof OAuth2AuthenticationToken) {
            System.out.println("OAuth2AuthenticationToken:______________");
            // Your logic here
            var aOAuth2Authentication = (OAuth2AuthenticationToken) authentication;
            var clientId =  aOAuth2Authentication.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            if(clientId.equalsIgnoreCase("google")){
            // login in google kela ahe tr  user name Email kas kadcha 
                System.out.println("Gatting the email is google ");
               userName = oauth2User.getAttribute("email").toString();
               

            }
            else if(clientId.equalsIgnoreCase("github")){
            // login in github kela ahe tr user name Email kas kadcha 
                System.out.println("Gatting the email is github");
                userName  = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                :oauth2User.getAttribute("login").toString()+"@gmail.com";

               
            }
       
        }
    
        
        else {
            System.out.println("Gatting thr email is use login");
            userName = authentication.getName();
        }
        return userName;
    }

        
    }
    

