package com.gamth.tfgdaniel.implementacion;


import com.gamth.tfgdaniel.modelo.Usuario;
import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.repositorio.IUsuarioRepository;
import com.gamth.tfgdaniel.servicio.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioImpl extends CRUD<Usuario,Integer> implements IUsuarioService {
    @Autowired
    private IUsuarioRepository  usuarioRepo;

    @Override
    protected IGenericoRepository<Usuario, Integer> getRepository() {
        return usuarioRepo;
    }

    @Override
    public Optional<Usuario> findByApodo(String apodo) {
        return usuarioRepo.findByApodo(apodo);
    }
}
