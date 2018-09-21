package pt.edge.performancescanner;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class PerformanceScannerSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	    // require all requests to be authenticated except for the resources
	    http.authorizeRequests().antMatchers("/javax.faces.resource/**","/css/**","/resources/**").permitAll();
	    http.authorizeRequests().anyRequest().authenticated();
	    // login
	    http.formLogin().loginPage("/Login.xhtml").permitAll().successHandler(successHandler)
	        .failureUrl("/Login.xhtml?error=true");
	    // logout
	    http.logout().logoutSuccessUrl("/Login.xhtml");
	    
	    http
        .authorizeRequests()
        .antMatchers("/admin/*").hasRole("ADMIN");

	    // not needed as JSF 2.2 is implicitly protected against CSRF
	    http.csrf().disable();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
		//		.url("ldap://localhost:10389/dc=springframework,dc=org").and().passwordCompare()
		//		.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
		//		.passwordAttribute("userPassword");
		//		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("john")
		.password(passwordEncoder.encode("111")).roles("USER").and().withUser("jane")
		.password(passwordEncoder.encode("222")).roles("USER","ADMIN");
	}

}


