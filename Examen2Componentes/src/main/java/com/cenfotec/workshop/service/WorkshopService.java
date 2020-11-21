package com.cenfotec.workshop.service;

import com.cenfotec.workshop.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {
    public List<Workshop> getAllWorkshops();

    public Optional<Workshop> getById(String id);

    public List<Workshop> getByAutor(String autor);

    public List<Workshop> getByCategoria(String categoria);

    public List<Workshop> getByPalabrasClave(String palabrasClave);

    public void saveWorkshop(Workshop newWorkshop);

    public List<Workshop> findWorkshopByName(String name);
}