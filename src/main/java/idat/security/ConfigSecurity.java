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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailService service;
	
	@Bean
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

	
	@Bean
	//Que este metodo lo puedo utilizar en cualquier parte
	public PasswordEncoder encrip() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	

}
