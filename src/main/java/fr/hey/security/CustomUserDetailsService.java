package fr.hey.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.hey.entity.Utilisateur;
import fr.hey.repository.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}

}
