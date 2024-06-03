package com.gamth.tfgdaniel.modeloDTO;

import com.gamth.tfgdaniel.modelo.Like;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private int idLike;
    private int idUsuario;
    private int idPost;

    public LikeDTO castLikeADto(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setIdLike(like.getIdLike());
        likeDTO.setIdUsuario(like.getIdUsuario().getIdUsuario());
        likeDTO.setIdPost(like.getIdPost().getIdPost());
        return likeDTO;
    }

    public Like castLike(){
        Like like = new Like();
        like.setIdLike(this.idLike);
        return like;
    }
}
