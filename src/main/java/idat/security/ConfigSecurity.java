package idat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder arg0) throws Exception {
		arg0.inMemoryAuthentication()
		.withUser("teresa")
		.password(encrip().encode("123"))
		.roles("ADMIN");
		
		arg0.inMemoryAuthentication()
		.withUser("julio")
		.password(encrip().encode("123"))
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity arg0) throws Exception {
		arg0.authorizeRequests()
		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers("/producto/*").access("hasRole('ADMIN')")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
	}
	
	@Bean
	//Que este metodo lo puedo utilizar en cualquier parte
	public PasswordEncoder encrip() {
		return new BCryptPasswordEncoder();
	}
	
/*	public UserDetailsService userdetail() {
		
		InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
		manager.createUser(
					User.withUsername("profesor")
					.password(encrip().encode("123"))
					.roles("ADMIN")
					.build()
				);
		return manager;
	}
	
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http)  throws Exception{
		http.authorizeRequests()
		.antMatchers("/producto/*").access("hasRole('ADMIN')")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		return http.build();
	}*/

}
