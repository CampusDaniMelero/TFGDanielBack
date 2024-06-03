package com.gamth.tfgdaniel.modeloDTO;

import com.gamth.tfgdaniel.modelo.Comentario;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComentarioDTO {
    private int idComentario;

    @NotEmpty
    @Size(min = 1, max = 500)
    private String textoComentario;



    @NotNull
    private int idUsuario;

    @NotNull
    private int idPost;


    public ComentarioDTO castComentarioADto(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setIdComentario(comentario.getIdComentario());
        comentarioDTO.setTextoComentario(comentario.getTextoComentario());
        comentarioDTO.setIdUsuario(comentario.getIdUsuario().getIdUsuario());
        comentarioDTO.setIdPost(comentario.getIdPost().getIdPost());
        return comentarioDTO;
    }

    public Comentario castComentario(){
        Comentario comentario = new Comentario();
        comentario.setIdComentario(this.idComentario);
        comentario.setTextoComentario(this.textoComentario);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(this.idUsuario);
        comentario.setIdUsuario(usuario);

        Post post = new Post();
        post.setIdPost(this.idPost);
        comentario.setIdPost(post);

        return comentario;
    }
}
