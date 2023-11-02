package com.vk.employee.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	
	@GetMapping("/")
	public String getDate(Model model) {
		model.addAttribute("theDate", new Date());
		return "home-page";
	}
	@GetMapping("/showLogin")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
	@GetMapping("/accessDenied")
	public String showAccessDenied() {
		return "access-denied";
	}
	

}
