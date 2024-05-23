package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Usuario;

import java.util.Optional;

public interface IUsuarioService extends ICRUD<Usuario,Integer> {
    Optional <Usuario> findByApodo(String apodo);

}
