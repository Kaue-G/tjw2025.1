package br.edu.ifce.meuprimeirospringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifce.meuprimeirospringboot.serviceImpl.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	//codificação de senha
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    // provedor de autenticação do Spring Security
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
		var authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService((UserDetailsService) userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
				//.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
                .clearAuthentication(true)
				.permitAll()
			);
		
		return http.build();
	}
}
