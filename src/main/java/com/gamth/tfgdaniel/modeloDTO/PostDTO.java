package com.gamth.tfgdaniel.modeloDTO;

import com.gamth.tfgdaniel.modelo.Categoria;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {
    private int idPost;

    @NotEmpty
    @Size(min = 1, max = 300)
    private String descripcionPost;

    @NotEmpty
    @Size(min = 4, max = 100)
    private String nombreVideojuego;



    @URL
    private String imgPost1;


    @NotNull
    private Integer idUsuario;

    @NotNull
    private List<Integer> categorias;


    public PostDTO castPostADto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setIdPost(post.getIdPost());
        postDTO.setDescripcionPost(post.getDescripcionPost());
        postDTO.setNombreVideojuego(post.getNombreVideojuego());

        if (post.getImgPost1() != null) {
            postDTO.setImgPost1(post.getImgPost1().toString());
        }

        if (post.getIdUsuario() != null) {
            postDTO.setIdUsuario(post.getIdUsuario().getIdUsuario());
        }

        if (post.getCategorias() != null) {
            postDTO.setCategorias(post.getCategorias().stream()
                    .map(Categoria::getIdCategoria)
                    .collect(Collectors.toList()));
        }


        return postDTO;
    }

    public Post castPost() {
        Post post = new Post();
        post.setIdPost(this.idPost);
        post.setDescripcionPost(this.descripcionPost);
        post.setNombreVideojuego(this.nombreVideojuego);

        if (this.imgPost1 != null && !this.imgPost1.isEmpty()) {
            try {
                post.setImgPost1(new java.net.URL(this.imgPost1));
            } catch (MalformedURLException e) {
                post.setImgPost1(null);
            }
        }



        if (this.idUsuario != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(this.idUsuario);
            post.setIdUsuario(usuario);
        }




        return post;
    }
}
