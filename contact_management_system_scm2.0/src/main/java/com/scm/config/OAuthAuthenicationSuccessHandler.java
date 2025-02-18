package com.scm.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.util.*;
import com.scm.entity.Providers;
import com.scm.entity.User;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.var;

import java.io.IOException;
import java.util.UUID;
@Configuration
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    UserRepo userRepo ;
     private static final Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,     // data bhetho ethun 
        HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("OAuthauthenicationSuccessHandler");

              // will be cheked which type of loging 
              var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;
             String authorizedClientRegistrationId =oauth2AuthenicationToken.getAuthorizedClientRegistrationId();
             logger.info(authorizedClientRegistrationId);

                //oauth2 user create object and ched the authentic providing 
                var  userOauth2 = (DefaultOAuth2User) authentication.getPrincipal();
                    userOauth2.getAttributes().forEach((key,value ) ->{
                        logger.info(key, value);
                    });

                    User user = new User();
                    user.setId(UUID.randomUUID().toString());
                    user.setEmailVerified(true);
                    user.setRolList(List.of(AppConstants.ROLE_USER));
                    user.setEnable(true);
                //goole login  
                if(authorizedClientRegistrationId.equalsIgnoreCase("google")){

                    user.setName(userOauth2.getAttribute("name").toString());
                    user.setEmail(userOauth2.getAttribute("email").toString());
                    user.setProfilePic(userOauth2.getAttribute("picture").toString());
                    user.setProviderUserId(userOauth2.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setAbout("This is user for login Google");
                    user.setPassword("dummy");
                  
                }
                //github login 
                else if(authorizedClientRegistrationId.equalsIgnoreCase("github")){
                    // email  ahe ki nahi chek kart ahe asnar the save kar nahi the github user name vare email tyar kar ahe 
                    String email = userOauth2.getAttribute("email") != null ? userOauth2.getAttribute("email").toString()
                    :userOauth2.getAttribute("login").toString()+"@gmail.com";
                    String picture = userOauth2.getAttribute("avatar_url").toString();
                    String name = userOauth2.getAttribute("login").toString();
                    String  providerid = userOauth2.getName();

                    user.setName(name);
                    user.setEmail(email);
                    user.setProfilePic(picture);
                    user.setProviderUserId(providerid);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("This is user for login GitHub");
                    user.setPassword("dummy");

            }
             //facebook login
                else if(authorizedClientRegistrationId.equalsIgnoreCase("facebook")){

                }
                //linkedin login
                else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {
                    
                }
                // some other login
                else{

                }
               

                //data la data base made save karu shakto 
    /**          DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
            //  logger.info(user.getName());
            //  user.getAttributes().forEach((key,valus)->{
            //     logger.info("{}=>{}",key,valus);
            //  });
            //  logger.info(user.getAuthorities().toString() );

            String email =user.getAttribute("email").toString();
            String name = user.getAttribute("name").toString();
            String picture = user.getAttribute("picture").toString();

            //create user aani save kara data base made 
                User user1 = new  User();
                user1.setName(name);
                user1.setEmail(email);
                user1.setProfilePic(picture);
                user1.setEmailVerified(true);
                user1.setId(UUID.randomUUID().toString());
                user1.setProvider(Providers.GOOGLE);
                user1.setProviderUserId(user.getName());
                user1.setRolList(List.of(AppConstants.ROLE_USER));
                user1.setPassword("password");
                user1.setAbout("This Account create using google");

               User user2 = userRepo.findByEmail(email).orElse(null);
                if(user2 == null){
                    userRepo.save(user1);
                    logger.info("save the user"+email);

                }


                */

                User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
                if(user2 == null){
                    userRepo.save(user);
                    logger.info("save the user"+user.getEmail());

                }
                new DefaultRedirectStrategy().sendRedirect(request, response,"/user/profile");

        
    }
    
}
