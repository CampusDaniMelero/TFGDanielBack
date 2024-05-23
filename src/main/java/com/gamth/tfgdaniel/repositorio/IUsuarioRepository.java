package com.gamth.tfgdaniel.repositorio;

import com.gamth.tfgdaniel.modelo.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends IGenericoRepository<Usuario,Integer>{
    Optional<Usuario> findByApodo(String apodo);

}
