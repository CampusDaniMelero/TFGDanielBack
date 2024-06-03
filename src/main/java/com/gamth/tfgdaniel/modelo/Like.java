package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLike;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post idPost;

}
