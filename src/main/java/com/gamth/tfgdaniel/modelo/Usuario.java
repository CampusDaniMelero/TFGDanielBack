package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre" , length = 100 , nullable = false)
    private String nombre;

    @Column(name = "apodo" , length = 100 , nullable = false)
    private String apodo;


    @Column(name = "correo" , length = 100 , nullable = false)
    private String correo;

    @Column(name = "password" , length = 100 , nullable = false)
    private String password;

    @Column(name = "fecha_registro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @Column(name = "img_perfil" , length = 200)
    private URL imgPerfil;

}
