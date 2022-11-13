package idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	//http://localhost:8090/oauth/token
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
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter= new JwtAccessTokenConverter();
		converter.setSigningKey("teresad");
		return converter;
	}
	
	@Bean
	public JwtTokenStore  tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	

}
