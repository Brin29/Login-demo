package login.demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UsuarioServiceImpl {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void guardar(UsuarioModel usuarioModel){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuarioModel.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
        usuarioRepository.save(usuarioModel);
    }

    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByNombre(nombre);
        if(usuarioModel == null){
            System.out.println("Usuario o password incorrectos");
            throw new UsernameNotFoundException("Usuario o password incorrectos");
        }
        return new User(usuarioModel.getNombre(), usuarioModel.getPassword(), mapearAutoridadesRoles(usuarioModel.getRol()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(RolModel rol){
        return Collections.singleton(new SimpleGrantedAuthority(rol.getNombre()));
    }
}
