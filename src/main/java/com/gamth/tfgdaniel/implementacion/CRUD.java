package com.gamth.tfgdaniel.implementacion;

import com.gamth.tfgdaniel.repositorio.IGenericoRepository;
import com.gamth.tfgdaniel.servicio.ICRUD;

import java.util.List;

public abstract class CRUD<T,ID> implements ICRUD<T,ID> {
    protected abstract IGenericoRepository<T,ID> getRepository();
    @Override
    public T crear(T t) {
        return getRepository().save(t);
    }

    @Override

    public T modificar(T t) {
        return getRepository().save(t);
    }

    @Override
    public void eliminar(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public T consultarUno(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> consultarTodos() {
        return getRepository().findAll();
    }
}
