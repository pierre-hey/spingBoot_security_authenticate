package fr.hey.configuration;

import org.aspectj.weaver.tools.Trace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


//TODO : https://www.youtube.com/watch?v=1XnPLWJwiHM 2h00

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig {
//	@Autowired
//	private CustomAuthenticationProvider customAuthenticationProvider;
//	@Bean
//	public UserDetailsService userDetailService() {
//		return new CustomUserDetailsService();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	public void init(HttpSecurity http) throws Exception {
		// any method that adds another configurer
		// must be done in the init method
		http.csrf().disable();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				// http.csrf().disable()
				.authorizeHttpRequests((authz) -> {
					try {
						authz
//				.antMatchers("/h2/**").permitAll()          
//				.antMatchers("/home").permitAll()
								.antMatchers("/register", "/connection", "/h2/**", "/home").permitAll()
								.antMatchers("/dashboard").hasRole("ROOT")
								.antMatchers("/profile").hasAnyRole("ADMIN","USER")
								.anyRequest().authenticated()
						// .and().formLogin()
//				.and().logout().logoutSuccessUrl("/login")
								.and()
								// .sessionManagement()
								// .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
								// .and()
								.formLogin(form -> form
										.loginPage("/login")
										.permitAll()
										.defaultSuccessUrl("/home",true))
//								.loginProcessingUrl("/connection")
//								.defaultSuccessUrl("/home",true)
//								.failureUrl("/login.html?error=true")
								//.failureHandler(new AuthenticationFailureHandler()))
								
//								.and()
//							      .logout(
//							    		  logout -> logout                                                
//							              .logoutUrl("/my/logout")                                            
//							              .logoutSuccessUrl("/my/index")                                      
//							              .logoutSuccessHandler(logoutSuccessHandler)                         
//							              .invalidateHttpSession(true)                                        
//							              .addLogoutHandler(logoutHandler)                                    
//							              .deleteCookies("JSESSIONID") )
//							      .logoutUrl("/perform_logout")
//							      .deleteCookies("JSESSIONID")
								;

						;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}).csrf().ignoringAntMatchers("/h2/**", "/register", "/connection").and()
//		.csrf().ignoringAntMatchers("/register").and()
				.headers().frameOptions().disable().and()
//        .cors().disable()
				.httpBasic();
		return http.build();
	}

//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		//return (web) -> web.ignoring().antMatchers("/h2/**").antMatchers("/register");
//		return (web) -> web.ignoring().antMatchers("/h2/**");
//	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
