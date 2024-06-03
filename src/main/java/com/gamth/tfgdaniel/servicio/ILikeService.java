package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Like;

import java.util.List;

public interface ILikeService extends ICRUD<Like,Integer>{
    List<Like> findByUsuarioIdUsuarioAndPostIdPost(Integer idUsuario, Integer idPost);

    List<Like> findByUsuarioIdUsuario(Integer idUsuario);

    List<Like> findByPostIdPost(Integer idPost);
}
