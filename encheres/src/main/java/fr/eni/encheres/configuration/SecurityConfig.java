
/**
 * 
 */
package fr.eni.encheres.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new NoOpPasswordEncoder();
//	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    return new DelegatingPasswordEncoder("bcrypt", encoders);
}
	@Bean
	UserDetailsManager getUsers(DataSource source) {

		var manager = new JdbcUserDetailsManager(source);
		
		manager.setUsersByUsernameQuery("select u.email,u.mot_de_passe,1 from Utilisateurs u where email=?;");
		manager.setAuthoritiesByUsernameQuery("select u.email, u.administrateur from Utilisateurs u where email=?;");

		return manager;
	}

	@Bean
	SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(auth -> {
			
//			auth.requestMatchers(HttpMethod.GET, "/films/detail").hasAnyRole("MEMBRE","ADMIN");
//			auth.requestMatchers(HttpMethod.GET, "/films/edit").hasAnyRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/inscription").permitAll();
			auth.requestMatchers(HttpMethod.POST, "/inscription").permitAll();
			auth.requestMatchers(HttpMethod.GET, "/*").permitAll();
			auth.requestMatchers(HttpMethod.GET, "/css/*").permitAll();
			auth.requestMatchers(HttpMethod.GET, "/images/*").permitAll();
			auth.anyRequest().denyAll();
		});

		security.formLogin(Customizer.withDefaults());
		
		return security.build();
	}

}


