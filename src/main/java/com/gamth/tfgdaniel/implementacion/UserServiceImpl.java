package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.repositorio.IUsuarioRepository;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl extends CRUD<Usuario,Integer> implements IUsuarioService {
    private final IUsuarioRepository iUsuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Usuario> findByApodo(String apodo) {
        return iUsuarioRepository.findByApodo(apodo);
    }

    @Override
    public List<Usuario> findByApodoLike(String apodo) {
        return iUsuarioRepository.findByApodoLike(apodo);
    }

    @Override
public Optional<Usuario> findById(Integer idUsuario) {
        return iUsuarioRepository.findById(idUsuario);
    }


    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return iUsuarioRepository.findByApodo(username)
                        .map(user -> new User(user.getUsername(), user.getPassword(), new ArrayList<>()))
                        .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
            }
        };
    }



    @Override
    protected IGenericoRepository<Usuario, Integer> getRepository() {
        return iUsuarioRepository;
    }

    public Usuario actualizarPassword(Integer idUsuario, String nuevaPassword) {
        Usuario usuario = consultarUno(idUsuario);
        if (usuario != null) {
            usuario.setPassword(passwordEncoder.encode(nuevaPassword));
            return modificar(usuario);
        }
        return null;
    }

}
