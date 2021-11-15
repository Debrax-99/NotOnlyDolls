package com.notonlydolls.nodwebsite;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for security authentication in backoffice page
 * 
 * @author Ana Blanco Escudero
 * @since 12-11-21
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath:nodwebsite.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Admin password
	private final String ADMIN_PASS;
	
	// CONSTRUCTOR
	@Autowired
    public SecurityConfig(@Value("${admin_pass}") String pass) {
        this.ADMIN_PASS = pass;
    }
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode(ADMIN_PASS)).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/*").permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/admin", true)
        .failureUrl("/login.html?error=true")
        .and()
        .logout(logout -> logout
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((request, response, auth) -> {
                    for (Cookie cookie : request.getCookies()) {
                        String cookieName = cookie.getName();
                        Cookie cookieToDelete = new Cookie(cookieName, null);
                        cookieToDelete.setMaxAge(0);
                        response.addCookie(cookieToDelete);
                    }
                })
              );
    }
    
    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
}
