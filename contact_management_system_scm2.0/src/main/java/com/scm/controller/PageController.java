package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PageController {
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
	public String postMethodName() {
		System.out.println("PageController.postMethodName()");
		return  "register";
	}
	// procssing methods
	@RequestMapping( value ="do_register", method=RequestMethod.POST)
	public String procssingRegister(){
		System.out.println("PageController.procssingRegister()");
		return "redirect:/register";
	}
	

	
	

}
