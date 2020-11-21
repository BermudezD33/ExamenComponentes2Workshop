package com.cenfotec.workshop.repository;

import com.cenfotec.workshop.domain.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {
    List<Categoria> findByNombreCategoria(String word);

}
