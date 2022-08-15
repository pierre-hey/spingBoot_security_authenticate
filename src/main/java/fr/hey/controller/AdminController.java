package fr.hey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("home")
	public String adminHome() {
		return("Admin home :X");
	}
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return("Admin dashboard :X");
	}
}
