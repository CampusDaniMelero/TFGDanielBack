package com.gamth.tfgdaniel.repositorio;

import com.gamth.tfgdaniel.modelo.Post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPostRepository extends IGenericoRepository<Post,Integer>
{
    List<Post> findByNombreVideojuego(String nombreVideojuego);

    @Query("SELECT p FROM Post p WHERE p.idPost = :idPost")

    Optional<Post> findByIdPost(@Param("idPost") Integer idPost);

    @Query("SELECT p FROM Post p WHERE p.nombreVideojuego = :nombreVideojuego AND p.idUsuario = :idUsuario")
    Optional<Post> findByNombreVideojuegoAndIdUsuario(@Param("nombreVideojuego") String nombreVideojuego, @Param("idUsuario") Integer idUsuario);

    @Query("SELECT p FROM Post p JOIN p.categorias c WHERE c.nombre = :nombreCategoria")
    List<Post> findByNombreCategoria(@Param("nombreCategoria") String nombreCategoria);

}
