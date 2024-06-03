package com.gamth.tfgdaniel.repositorio;

import com.gamth.tfgdaniel.modelo.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends IGenericoRepository<Like,Integer> {

    @Query("SELECT l FROM Like l WHERE l.idUsuario.idUsuario = :idUsuario AND l.idPost.idPost = :idPost")
    List<Like> findByUsuarioIdUsuarioAndPostIdPost(Integer idUsuario, Integer idPost);

    @Query("SELECT l FROM Like l WHERE l.idUsuario.idUsuario = :idUsuario")
    List<Like> findByUsuarioIdUsuario(Integer idUsuario);

    @Query("SELECT l FROM Like l WHERE l.idPost.idPost = :idPost")
    List<Like> findByPostIdPost(Integer idPost);

}
