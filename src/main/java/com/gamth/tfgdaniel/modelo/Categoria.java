package com.gamth.tfgdaniel.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;
    @Column(name = "nombreCategoria" , length = 80)
    private String nombre;



    @ManyToMany(mappedBy = "categorias")
    private List<Post> posts;

}
