package fr.hey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.hey.entity.Role;
import fr.hey.entity.Utilisateur;
import fr.hey.model.UserModel;
import fr.hey.repository.UtilisateurRepository;

@RestController
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public Utilisateur register(@RequestBody UserModel userModel) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(userModel.getEmail());
		utilisateur.setPassword(passwordEncoder.encode(userModel.getPassword()));
		utilisateur.setRoles(userModel.getAuthorities());

		return utilisateurRepository.save(utilisateur);
	}

	@PostMapping("/connection")
	public ResponseEntity<HttpStatus> login(@RequestBody UserModel userModel) throws Exception {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid credentials");
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		
	}

	@GetMapping("/dashboard")
//	@PreAuthorize("hasRole('ROOT')")
	public String dashboard() {
		return "Contenu du DashBoard user";
	}

	@GetMapping("/profile")
//	@PreAuthorize("hasRole('USER')")
	public String profile() {
		return "Contenu du profile";
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

}
