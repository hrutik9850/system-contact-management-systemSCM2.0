package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entity.Contact;
import com.scm.entity.User;
import com.scm.form.Contact_Form;
import com.scm.helper.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user/contact")

public class ContactController {
    @Autowired
    private ContactService contactService ;
    @Autowired
    private UserServices userServices;
    @RequestMapping("/add")
    public String showAddContactPage(Model model){
        Contact_Form contact_Form = new Contact_Form();
            
        model.addAttribute("contactForm", contact_Form);
    
        return"/user/addContact";
    }
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute("contactForm") Contact_Form contact_Form ,BindingResult bindingResult , Authentication authentication) {
       //processe the from data 


       //TODOO THE valedate the form data 
            //1 form valdation 
            if (bindingResult.hasErrors()) {
                System.out.println("------------------------------------ error--------------------------------------------------");
                
                return"user/addContact";
                
            }

       
        String username = Helper.getEmailFOLoggelUser(authentication);
        //processes the contacts picture 
        User user = userServices.getUserByEmail(username);
        //posess the contact data 
        Contact contact = new Contact();
        contact.setName(contact_Form.getName());
        contact.setAddress(contact_Form.getAddress());
        contact.setEmail(contact_Form.getEmail());
        contact.setDescription(contact_Form.getDescription());
        contact.setPhomeNumber(contact_Form.getPhomeNumber());
        contact.setFavarite(contact_Form.isFavarite());
        // contact.setPicture(contact_Form.getProfileImage());
        contact.setWebsitelink(contact_Form.getWebsitelink());
        contact.setLinkedIdlink(contact_Form.getLinkedIdlink());
        contact.setUser(user);

        contactService.saveContact(contact);
        System.out.println(contact_Form);

        // set the massage to redirect ot the 
        return "redirect:/user/contact/add";
    }
    

    @RequestMapping("/showAllContact")
    public String showAllContact(){
        return"/user/showAllContact";
    }
    

    
}
