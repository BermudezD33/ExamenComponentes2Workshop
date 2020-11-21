package com.cenfotec.workshop.service;

import com.cenfotec.workshop.domain.Categoria;
import com.cenfotec.workshop.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository repo;

    @Override
    public List<Categoria> getAllCategorias() {
        return repo.findAll();
    }

    @Override
    public void saveCategoria(Categoria newCategoria) {
        repo.save(newCategoria);
    }

    @Override
    public Optional<Categoria> get(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Categoria> findByNombreCategoria(String name) {
        return repo.findByNombreCategoria(name);
    }
}
