package fr.hey.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}


}