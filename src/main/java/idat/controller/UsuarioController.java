package idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import idat.dto.UsuarioDtoRequest;
import idat.dto.UsuarioDtoresponse;
import idat.security.TokenUtil;
import idat.security.UserDetailService;

@Controller
public class UsuarioController {
	
	@Autowired
	private TokenUtil util;
	@Autowired
	private UserDetailService service;
	
	@RequestMapping(path = "/creartoken", method = RequestMethod.POST)
	public ResponseEntity<?> crearToken(@RequestBody UsuarioDtoRequest request){
		
		UserDetails  user = service.loadUserByUsername(request.getUsuario());
		return ResponseEntity.ok(new UsuarioDtoresponse(util.generateToken(user.getUsername())));
	}
}
