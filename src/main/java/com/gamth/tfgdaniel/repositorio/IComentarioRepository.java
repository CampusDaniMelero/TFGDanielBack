package com.gamth.tfgdaniel.repositorio;

import com.gamth.tfgdaniel.modelo.Comentario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioRepository extends IGenericoRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c WHERE c.idPost.idPost = :idPost")
    List<Comentario> findByIdPost(@Param("idPost") Integer idPost);

    @Query("SELECT c FROM Comentario c WHERE c.idUsuario.idUsuario = :idUsuario")
    List<Comentario> findByIdUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT c FROM Comentario c WHERE c.idPost.idPost = :idPost AND c.idUsuario.idUsuario = :idUsuario")
    List<Comentario> findByIdPostAndIdUsuario(@Param("idPost") Integer idPost, @Param("idUsuario") Integer idUsuario);

    List<Comentario> findByIdComentario(Integer idComentario);
}
