package fr.hey.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.hey.entity.Role;
import fr.hey.entity.Utilisateur;
import fr.hey.repository.UtilisateurRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		System.out.println("EMAIL : " + email);
		
		Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		if(passwordEncoder.matches(password, utilisateur.getPassword())) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(utilisateur.getAuthorities().getAuthority()));
			
			System.out.println("###############################");
			System.out.println(new UsernamePasswordAuthenticationToken(email, password, getRoles(utilisateur.getRoles())));
			System.out.println("###############################");
			
			return new UsernamePasswordAuthenticationToken(email, password, getRoles(utilisateur.getRoles()));
		}
		else {
			throw new BadCredentialsException("Invalid credentials");
		}

	}

	private Collection<SimpleGrantedAuthority> getRoles(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> list = new HashSet<>();
		for(Role auth : roles) {
			list.add(new SimpleGrantedAuthority(auth.getRole()));
		}
		return list;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
