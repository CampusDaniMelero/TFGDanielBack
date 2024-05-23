package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private int idPost;

    @Column(name = "descripcionPost" , length = 500 , nullable = false)
    private String descripcionPost;

    @Column(name = "nombreVideojuego" , length = 100 , nullable = false)
    private String nombreVideojuego;

    @Column(name = "fechaPost" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaPost;

    @Column(name = "imgPost" , length = 300)
    private String imgPost;

    @Column(name = "imgPost2" , length = 300)
    private String imgPost2;

    @Column(name = "imgPost3" , length = 300)
    private String imgPost3;



    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria idCategoria;
}
