
/**
 * 
 */
package fr.eni.encheres.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Classe en charge de
 * 
 * /** Classe en charge de
 * 76890adb5ad38784cdf5798f20f52aa0126b1b64
 * 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 14:20:21
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}

	@Bean
	UserDetailsManager getUsers(DataSource source) {

		var manager = new JdbcUserDetailsManager(source);
		manager.setUsersByUsernameQuery("select u.email,u.mot_de_passe,1 from Utilisateurs u where email=?");
		manager.setAuthoritiesByUsernameQuery("select u.email,u.administrateur from Utilisateurs u where email=?");
		return manager;
	}

	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
 
		security.authorizeHttpRequests((requests) ->
		//=========TO DO POUR TESTER
//		requests.anyRequest().permitAll());
//		security.csrf().disable().
		//=========FIN POUR TESTER
		requests.requestMatchers("/", "/inscription", "/css/**", "/js/**")
				.permitAll().anyRequest().authenticated())
				.formLogin((form) -> form.usernameParameter("email").passwordParameter("motDePasse")
						.loginPage("/connexion").defaultSuccessUrl("/", true).permitAll())
				.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());

		return security.build();
	}
	//Controle session
//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//	    return new HttpSessionEventPublisher();
//	}



}
