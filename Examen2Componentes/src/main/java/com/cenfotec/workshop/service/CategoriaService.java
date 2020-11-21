package com.cenfotec.workshop.service;

import com.cenfotec.workshop.domain.Categoria;
import com.cenfotec.workshop.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public List<Categoria> getAllCategorias();

    public void saveCategoria(Categoria newCategoria);

    public Optional<Categoria> get(String id);

    public List<Categoria> findByNombreCategoria(String name);


}
