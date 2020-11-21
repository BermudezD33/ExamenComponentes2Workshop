package com.cenfotec.workshop.service;

import com.cenfotec.workshop.domain.Actividad;
import com.cenfotec.workshop.domain.Workshop;
import com.cenfotec.workshop.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    WorkshopRepository repo;

    @Override
    public List<Workshop> getAllWorkshops() {
        return repo.findAll();
    }

    @Override
    public Optional<Workshop> getById(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Workshop> getByAutor(String autor) {
        return repo.findByAutor(autor);
    }

    @Override
    public List<Workshop> getByCategoria(String categoria) {
        return repo.findByCategoria(categoria);
    }

    @Override
    public List<Workshop> getByPalabrasClave(String palabrasClave) {
        return repo.findByPalabrasClave(palabrasClave);
    }

    @Override
    public void saveWorkshop(Workshop newWorkshop) {
        int tiempoTotal = 0;
        for (Actividad actividad: newWorkshop.getListaActividades()) {

            tiempoTotal += actividad.getTiempo();

        }
        newWorkshop.setTiempoDuracion(tiempoTotal);
        repo.save(newWorkshop);
    }

    @Override
    public List<Workshop> findWorkshopByName(String name) {
        return repo.findByNombreWorkshop(name);
    }
}
