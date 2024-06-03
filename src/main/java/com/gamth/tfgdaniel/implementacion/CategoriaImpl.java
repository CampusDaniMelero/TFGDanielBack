package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Categoria;
import com.gamth.tfgdaniel.repositorio.ICategoriaRepository;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.servicio.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaImpl extends CRUD<Categoria,Integer> implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    protected IGenericoRepository<Categoria, Integer> getRepository() {
        return categoriaRepository;
    }

    @Override
    public List<Categoria> findByNombre(String nombreCategoria) {
        return categoriaRepository.findByNombre(nombreCategoria);
    }
}
