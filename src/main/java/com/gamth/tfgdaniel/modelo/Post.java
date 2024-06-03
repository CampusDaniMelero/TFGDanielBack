package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URL;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;

    @Column(name = "descripcionPost" , length = 500 , nullable = false)
    private String descripcionPost;

    @Column(name = "nombreVideojuego" , length = 100 , nullable = false)
    private String nombreVideojuego;



    @Column(name = "imgPost1" , length = 500)
    private URL imgPost1;




    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToMany
    @JoinTable(name = "post_categoria",
            joinColumns = @JoinColumn(name = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "idCategoria"))
    private List<Categoria> categorias;
}
