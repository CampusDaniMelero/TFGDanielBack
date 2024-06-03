package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Comentario;

import java.util.List;

public interface IComentarioService extends ICRUD<Comentario,Integer>{
    List<Comentario> findByIdPost(Integer idPost);

    List<Comentario> findByIdUsuario(Integer idUsuario);

    List<Comentario> findByIdPostAndIdUsuario(Integer idPost, Integer idUsuario);

    List<Comentario> findByIdComentario(Integer idComentario);

}
