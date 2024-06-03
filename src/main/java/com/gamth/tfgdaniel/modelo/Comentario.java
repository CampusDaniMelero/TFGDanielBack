package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @Column(name = "textoComentario" , length = 500 , nullable = false)
    private String textoComentario;



    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idPost")
    private Post idPost;

}
