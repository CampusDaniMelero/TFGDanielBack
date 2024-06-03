package com.gamth.tfgdaniel.servicio;

import com.gamth.tfgdaniel.modelo.Categoria;

import java.util.List;

public interface ICategoriaService extends ICRUD<Categoria,Integer> {
    List<Categoria> findByNombre(String nombreCategoria);

}
