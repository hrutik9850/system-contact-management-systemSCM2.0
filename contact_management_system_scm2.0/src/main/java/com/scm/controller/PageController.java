package com.scm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.scm.entity.User;
import com.scm.form.Userform;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;





@Controller
public class PageController {
	@Autowired
	private UserServices userServices;

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public Model home(Model model) {
		model.addAttribute("Name", "Hrutik Dharamkar");
		model.addAttribute("linkedinId", "https://www.linkedin.com/in/hrutik-dharamkar-754711270/");
		model.addAttribute("githube", "https://github.com/login/hrutik9850");
		
		return model;

	}
	//  about 

	@RequestMapping("/about")
	public String aboutPage(){
		System.out.println("PageController.aboutPage()");
		return "about";
	}
	// services

	@RequestMapping("/services")
	public String servicesPage(){
		System.out.println("PageController.servicesPage()");
		return "services";
	}

	// content
	@RequestMapping("/content")
	public String contentPage() {
		System.out.println("PageController.getMethodName()");
		return "content";
	}
	// login


	@GetMapping("/login")
	public String loginPage() {
		System.out.println("PageController.getMethodName()");
		return "login";
	}

	// register

	@GetMapping("/register")
	public String postMethodName(Model model) {
		System.out.println("PageController.postMethodName()");
		Userform userform = new Userform();
		// userform.setName("hrutik");
		// userform.setEmail("hruti@gmail.com");
		// userform.setPassword("@3433");
		// userform.setPhoneNumber("434333322");
		// userform.setAbout("I am a java develapler and spring boot ");
		model.addAttribute("userfrom", userform);

		return  "register";
	}
	// procssing methods
	@RequestMapping( value ="do_register", method=RequestMethod.POST)
	public String procssingRegister(@Valid @ModelAttribute("userfrom") Userform userform ,BindingResult rBindingResult ,HttpSession session){
		System.out.println("PageController.procssingRegister()");
		System.out.println("________________________________________");
		//fetch form data 
		//userForm
		 System.out.println(userform);
		// //validate form data 
		if(rBindingResult.hasErrors()){
			System.out.println("******************************************************");
			return"register";

		}
		// //TOO: validate userFomr 
		// // userform  data  conver to user 
		
		/**  User user = User.builder()
		 .name(userform.getName())
		 .email(userform.getEmail())
		 .phoneNumber(userform.getPhoneNumber())
		 .password(userform.getPassword())
		 .about(userform.getAbout())
		 .build();
		 */
		User user = new User();
		user.setName(userform.getName());
		user.setEmail(userform.getEmail());
		user.setPassword(userform.getPassword());
		user.setPhoneNumber(userform.getPhoneNumber());
		user.setAbout(userform.getAbout());
		
		 User savuser = userServices.savaUser(user);
		
		// add massage in HttpSession
		Message message = Message .builder().content("Registration successfull").type(MessageType.green).build();
		session.setAttribute("message",message);
		 //save to database 
		 //userservoce
		 //message ="Register succesfull"
		 // redirectio login page 
		 return "redirect:/register";
	}
	

	
	

}
