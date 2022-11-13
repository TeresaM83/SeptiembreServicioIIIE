package idat.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import idat.model.Usuario;
import idat.repository.UsuarioRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository r;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u= r.findByUsuario(username);
		List<GrantedAuthority> listaroles= new ArrayList<>();
		GrantedAuthority rol = new SimpleGrantedAuthority(u.getRol());
		listaroles.add(rol);
		
		if(u!=null) {
			return new User(u.getUsuario(),
					u.getContrasenia(),
					listaroles);
		}else {
			throw new UsernameNotFoundException("Usuario no existe");
		}
	}

}
