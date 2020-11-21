package com.cenfotec.workshop.web;


import com.cenfotec.workshop.domain.Actividad;
import com.cenfotec.workshop.domain.Categoria;
import com.cenfotec.workshop.domain.Workshop;
import com.cenfotec.workshop.service.CategoriaService;
import com.cenfotec.workshop.service.WordDocumentService;
import com.cenfotec.workshop.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


@Controller
public class WorkshopController {

    @Autowired
    WorkshopService workshopService;

    @Autowired
    CategoriaService categoriaService;

    static WordDocumentService wordDocumentService = new WordDocumentService();


    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value = {"/registrarWorkshop", "/registrarWorkshop/{id}"})
    public String getAgregarWorkshop(@PathVariable(value = "id", required = false) String id, Model model) {
        if (id == null) {
            model.addAttribute("workshop", new Workshop());
        } else {
            model.addAttribute("workshop", workshopService.getById(id).get());
        }
        model.addAttribute("categorias", categoriaService.getAllCategorias());

        return "registrarWorkshop";
    }

    @PostMapping(value = {"/registrarWorkshop", "/registrarWorkshop/{id}"})
    public String postAgregarWorkshop(@PathVariable(value = "id", required = false) String id, Workshop workshop, Model model) {
        if (id != null) {
            workshop.setId(id);
        }
        workshopService.saveWorkshop(workshop);
        return "redirect:/listarWorkshop";
    }

    @GetMapping("/listarWorkshop")
    public String getWorkshops(Model model) {
        model.addAttribute("workshops", workshopService.getAllWorkshops());
        model.addAttribute("categorias", categoriaService.getAllCategorias());

        return "listarWorkshop";
    }

    @GetMapping("/registrarCategoria")
    public String getAgregarCategoria(Model model) {
        model.addAttribute(new Categoria());
        return "registrarCategoria";
    }

    @PostMapping("/registrarCategoria")
    public String postAgregarCategoria(Categoria categoria, BindingResult result, Model model) {
        categoriaService.saveCategoria(categoria);
        return "home";
    }

    @GetMapping("/listarCategoria")
    public String getCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "listarCategoria";
    }

    @GetMapping("/editarCategoria/{id}")
    public String editarCategoriaGet(@PathVariable("id") String id, Model model) {
        Categoria categoria = categoriaService.get(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("categoria", categoria);
        return "editarCategoria";

    }

    @PostMapping("/editarCategoria/{id}")
    public String editarCategoriaPost(@PathVariable("id") String id, Categoria categoria,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            categoria.setId(id);
            return "editarCategoria";
        }

        categoriaService.saveCategoria(categoria);
        model.addAttribute("categoria", categoriaService.getAllCategorias());
        return "redirect:/listarCategoria";

    }

    @GetMapping("/registrarWorkshop/{id}/registrarActividad")
    public String getAgregarActividad(@PathVariable(value = "id", required = false) String id, Model model) {
        model.addAttribute("workshop", workshopService.getById(id).get());
        model.addAttribute("actividad", new Actividad());

        return "registrarActividad";
    }

    @PostMapping("/registrarWorkshop/{id}/registrarActividad")
    public String postAgregaActividad(@PathVariable(value = "id", required = false) String id, Actividad actividad, Model model) {
        Workshop workshop = workshopService.getById(id).get();
        workshop.getListaActividades().add(actividad);
        workshopService.saveWorkshop(workshop);
        return "redirect:/listarWorkshop";
    }

    @GetMapping("/listarWorkshopXAutor/{autor}")
    public String getWorkshopXAutor(@PathVariable(value = "autor") String autor, Model model) {
        model.addAttribute("workshop", workshopService.getByAutor(autor));
        return "listarWorkshopXAutor";
    }

    @GetMapping("/filtroAutor")
    public String getWorkshopXAutorVacio(Model model) {
        model.addAttribute("workshop", new Workshop());
        return "filtroAutor";
    }

    @GetMapping("/filtroCategoria")
    public String getWorkshopXCategoriaVacio(Model model) {
        model.addAttribute("workshop", new Workshop());
        model.addAttribute("categoria", categoriaService.getAllCategorias());
        return "filtroCategoria";
    }

    @GetMapping("/filtroKey")
    public String getWorkshopXKeyVacio(Model model) {
        model.addAttribute("workshop", new Workshop());
        return "filtroKey";
    }

    @GetMapping("/listarWorkshopXCategoria/{categoria}")
    public String getWorkshopXCategoria(@PathVariable(value = "categoria") String categoria, Model model) {
        model.addAttribute("workshop", workshopService.getByCategoria(categoria));
        return "listarWorkshopXCategoria";
    }

    @GetMapping("/listarWorkshopXKey/{palabrasClave}")
    public String getWorkshopXKeyWord(@PathVariable(value = "palabrasClave") String palabrasClave, Model model) {
        model.addAttribute("workshop", workshopService.getByPalabrasClave(palabrasClave));
        return "listarWorkshopXKey";
    }

    @GetMapping(value = "/workshops/{id}/doc", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> generadorDoc(@PathVariable(value = "id") String id, Model model) throws Exception {
        Workshop workshop = workshopService.getById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition" , "attachment; fileName=Workshop.docx");
        ByteArrayOutputStream outputStream = wordDocumentService.handleSimpleDoc(workshop);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
