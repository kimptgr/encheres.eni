/**
 * 
 */
package fr.eni.encheres.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 14:20:21
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	UserDetailsManager getUsers(DataSource source) {

		var manager = new JdbcUserDetailsManager(source);
		
		manager.setUsersByUsernameQuery("select u.email,u.mot_de_passe,u.pseudo,u.no_utilisateur from Utilisateurs u where email=?;");
		manager.setAuthoritiesByUsernameQuery("select u.email, u.pseudo, u.administrateur from Utilisateurs u where email=?;");

		return manager;
	}

	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(auth -> {
			

			auth.anyRequest().permitAll();
		});
		
		security.formLogin(Customizer.withDefaults());
		
		return security.build();
	}

}
