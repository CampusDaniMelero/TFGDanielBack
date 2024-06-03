package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Post;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.repositorio.IPostRepository;
import com.gamth.tfgdaniel.servicio.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostImpl extends CRUD<Post,Integer> implements IPostService{
    @Autowired
    private IPostRepository postRepo;

    @Override
    protected IGenericoRepository<Post, Integer> getRepository() {
        return postRepo;
    }

    @Override
    public List<Post> findByNombreVideojuego(String nombreVideojuego) {
        return postRepo.findByNombreVideojuego(nombreVideojuego);
    }

    @Override
    public Optional<Post> findByNombreVideojuegoAndIdUsuario(String nombreVideojuego, Integer idUsuario) {
        return postRepo.findByNombreVideojuegoAndIdUsuario(nombreVideojuego, idUsuario);
    }

    @Override
    public List<Post> findByNombreCategoria(String nombreCategoria) {
        return postRepo.findByNombreCategoria(nombreCategoria);
    }

    @Override
    public Optional<Post> findByIdPost(Integer idPost) {
        return postRepo.findByIdPost(idPost);
    }




}
