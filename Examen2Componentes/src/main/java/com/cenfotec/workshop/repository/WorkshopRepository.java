package com.cenfotec.workshop.repository;

import com.cenfotec.workshop.domain.Workshop;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkshopRepository extends MongoRepository<Workshop, String> {

    List<Workshop> findByNombreWorkshop(String word);

    List<Workshop> findByCategoria(String categoria);

    List<Workshop> findByPalabrasClave(String palabrasClave);

    List<Workshop> findByAutor(String autor);

}
