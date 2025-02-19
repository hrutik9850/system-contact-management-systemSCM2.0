package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.scm.services.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    //configuration of authentication providerfor spring security
    @Autowired
    private OAuthAuthenicationSuccessHandler handler;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationConfigurer = new  DaoAuthenticationProvider();
        //user detail servicr ka object 
        daoAuthenticationConfigurer.setUserDetailsService(userDetailService);
        //password encoder ka object 
        daoAuthenticationConfigurer.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationConfigurer;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        //coniguration
        //urls configure keay hai ki koun se public rangenge aur koun se private ryage 
        httpSecurity.authorizeHttpRequests(authorize ->{
          //  authorize.requestMatchers("/home","/register","/services").permitAll(); 
              authorize.requestMatchers("/user/**").authenticated();
              authorize.anyRequest().permitAll();

        });
        //form default login 
        // ager hame kuch bhi chang karna hua to hama yaha ayenga form login se related 
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
         //   formLogin.failureForwardUrl("/login?error=true");
            //formLogin.defaultSuccessUrl("/home");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=ture");
        });
        
        //oauth2 configuration
       httpSecurity.oauth2Login(oauth ->{
        oauth.loginPage("/login");  
        oauth.successHandler(handler);
       });            // to get custome login page 
       return httpSecurity.build();

    }
    

    
}
