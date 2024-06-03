package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.modelo.Like;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.repositorio.ILikeRepository;
import com.gamth.tfgdaniel.servicio.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeImpl extends CRUD<Like,Integer> implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    protected IGenericoRepository<Like, Integer> getRepository() {
        return likeRepository;
    }

    @Override
    public List<Like> findByUsuarioIdUsuarioAndPostIdPost(Integer idUsuario, Integer idPost) {
        return likeRepository.findByUsuarioIdUsuarioAndPostIdPost(idUsuario, idPost);
    }

    @Override
    public List<Like> findByUsuarioIdUsuario(Integer idUsuario) {
        return likeRepository.findByUsuarioIdUsuario(idUsuario);
    }

    @Override
    public List<Like> findByPostIdPost(Integer idPost) {
        return likeRepository.findByPostIdPost(idPost);
    }
}
