package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService extends ICRUD<Usuario,Integer> {
    Optional <Usuario> findByApodo(String apodo);
    List<Usuario> findByApodoLike(String apodo);

    Optional<Usuario> findById(Integer idUsuario);

    Usuario actualizarPassword(Integer idUsuario, String nuevaPassword);


    UserDetailsService userDetailsService();
}
