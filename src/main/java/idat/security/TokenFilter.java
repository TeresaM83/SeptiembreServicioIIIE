package idat.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class TokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailService s;
	@Autowired
	private TokenUtil util;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String tokenHeader= request.getHeader("Authorization");
		String token= null;
		String username= null;
		
		if(tokenHeader!=null && tokenHeader.startsWith("Tere ")) {
			token= tokenHeader.substring(5);
			try {
				username= util.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.warn("Token invalido");
			}catch (ExpiredJwtException e) {
				logger.warn("Token expirado");
			}
		}else {
			logger.warn("Token inavalidoelse");
		}
				
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user = this.s.loadUserByUsername(username);
			if(util.validateToken(token, user)) {
				UsernamePasswordAuthenticationToken usertokn= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				
				usertokn.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usertokn);
			}
		}
		filterChain.doFilter(request, response);

	}

}
