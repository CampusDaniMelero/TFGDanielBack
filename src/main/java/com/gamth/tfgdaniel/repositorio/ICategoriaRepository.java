package com.gamth.tfgdaniel.repositorio;

import com.gamth.tfgdaniel.modelo.Categoria;
import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.modeloDTO.PostDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository extends IGenericoRepository<Categoria,Integer> {

    List<Categoria> findByNombre(String nombreCategoria);}
