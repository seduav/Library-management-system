package lv.venta.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lv.venta.librarymanagementsystem.service.impl.UserDetailsManagerServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public UserDetailsManagerServiceImpl getService() {
		return new UserDetailsManagerServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider createAuthProvider() {
		PasswordEncoder encoder =
			    PasswordEncoderFactories.createDelegatingPasswordEncoder();
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder);
		provider.setUserDetailsService(getService());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/").permitAll()
				.requestMatchers("/error").permitAll()
                .requestMatchers("/access-denied").permitAll()
				.requestMatchers("/authors").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/author/**").hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/add-author").hasAuthority("ADMIN")
				.requestMatchers("/update-author/**").hasAuthority("ADMIN")
				.requestMatchers("/remove-author/**").hasAuthority("ADMIN")
				.requestMatchers("/books").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/search-book").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/search-book/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/add-book").hasAuthority("ADMIN")
                .requestMatchers("/update-book/**").hasAuthority("ADMIN")
                .requestMatchers("/remove-book/**").hasAuthority("ADMIN")
                .requestMatchers("/categories").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/category/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/add-category").hasAuthority("ADMIN")
                .requestMatchers("/update-category/**").hasAuthority("ADMIN")
                .requestMatchers("/remove-category/**").hasAuthority("ADMIN")
                .requestMatchers("/publishers").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/publisher/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/add-publisher").hasAuthority("ADMIN")
                .requestMatchers("/update-publisher/**").hasAuthority("ADMIN")
                .requestMatchers("/remove-publisher/**").hasAuthority("ADMIN")
				.requestMatchers("/h2-console/**").hasAuthority("ADMIN")
				);
		http.exceptionHandling(exp->exp.accessDeniedPage("/access-denied"));
		http.csrf(csrf->csrf.disable());
		http.headers(frame->frame.frameOptions(option->option.disable()));
		http.formLogin(form -> form.permitAll());
		return http.build();
	}

}