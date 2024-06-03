package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Comentario;
import com.gamth.tfgdaniel.repositorio.IComentarioRepository;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.servicio.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComentarioImpl extends CRUD<Comentario,Integer> implements IComentarioService {
    @Autowired
    private IComentarioRepository comentarioRepository;

    @Override
    protected IGenericoRepository<Comentario, Integer> getRepository() {
        return comentarioRepository;
    }

    @Override
    public List<Comentario> findByIdPost(Integer idPost) {
        return comentarioRepository.findByIdPost(idPost);
    }

    @Override
    public List<Comentario> findByIdUsuario(Integer idUsuario) {
        return comentarioRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public List<Comentario> findByIdPostAndIdUsuario(Integer idPost, Integer idUsuario) {
        return comentarioRepository.findByIdPostAndIdUsuario(idPost, idUsuario);
    }

    @Override
    public List<Comentario> findByIdComentario(Integer idComentario) {
        return comentarioRepository.findByIdComentario(idComentario);
    }

}