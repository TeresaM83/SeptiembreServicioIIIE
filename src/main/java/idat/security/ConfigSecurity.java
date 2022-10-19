package idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailService service;
	
	@Autowired
	private TokenFilter filter;
	
	@Autowired
	private EntryPoint point;
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder arg0) throws Exception {
		/*arg0.inMemoryAuthentication()
		.withUser("teresa")
		.password(encrip().encode("123"))
		.roles("ADMIN");
		
		arg0.inMemoryAuthentication()
		.withUser("julio")
		.password(encrip().encode("123"))
		.roles("USER");*/
		
		arg0.userDetailsService(service).passwordEncoder(encrip());
	}

	@Override
	protected void configure(HttpSecurity arg0) throws Exception {
		/*arg0.authorizeRequests()
		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers("/producto/*").hasAnyRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();*/
		
		arg0.authorizeRequests()
		.antMatchers("/crearToken").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(point)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
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
