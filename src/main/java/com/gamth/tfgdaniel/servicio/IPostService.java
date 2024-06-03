package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService extends ICRUD<Post,Integer>{

    List<Post> findByNombreVideojuego(String nombreVideojuego);


    Optional<Post> findByIdPost(Integer idPost);

    Optional<Post> findByNombreVideojuegoAndIdUsuario(String nombreVideojuego, Integer idUsuario);

    List<Post> findByNombreCategoria(String nombreCategoria);


}
