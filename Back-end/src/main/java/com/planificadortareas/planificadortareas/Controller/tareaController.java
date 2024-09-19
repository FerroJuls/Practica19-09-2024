package com.planificadortareas.planificadortareas.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planificadortareas.planificadortareas.InterfacesService.ItareaService;
import com.planificadortareas.planificadortareas.Models.tarea;

@RequestMapping("/api/v1/tarea")
@RestController

public class tareaController {
    
    @Autowired
    private ItareaService tareaService;

    // @Autowired
    // private emailService emailService;


    @PostMapping("/tasks")
    public ResponseEntity<Object> save (@ModelAttribute("tarea") tarea tarea){
        tareaService.save(tarea);
        // emailService.enviarCorreoBienvenida(tarea.getCorreo());
        return new ResponseEntity<>(tarea, HttpStatus.OK);
    }
    
    @GetMapping("/tasks")
    public ResponseEntity<Object> findAll() {
        var listaTarea = tareaService.findAll();
        return new ResponseEntity<>(listaTarea, HttpStatus.OK);
    }

    // @GetMapping("/mayoriaedad")
    // public ResponseEntity<Object> finEdad() {
    //     var listaTarea = tareaService.cambiarTipoDocumento();
    //     return new ResponseEntity<>(listaTarea, HttpStatus.OK);
    // }

    @GetMapping("/busquedaFiltros/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro) {
        var listaTarea = tareaService.filtroTarea(filtro);
        return new ResponseEntity<>(listaTarea, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> findOne(@PathVariable String id) {
        Optional<tarea> tareaOptional = tareaService.findOne(id);
        if (tareaOptional.isPresent()) {
            return new ResponseEntity<>(tareaOptional.get(), HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea no encontrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    // @DeleteMapping("/tasks/{id}")
    // public ResponseEntity<Object> deleteForever(@PathVariable String id) {
    //     tareaService.deleteForever(id);
    //     return new ResponseEntity<>("Tarea eliminada Permanentemente", HttpStatus.OK);
    // }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteForever(@PathVariable String id) {
        Optional<tarea> tareaOptional = tareaService.findOne(id);
        if (tareaOptional.isPresent()) {
            tareaService.deleteForever(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea eliminada con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tarea no eliminada, no se encontro");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    
    // @DeleteMapping("/tasks/{id}")
    // public ResponseEntity<Object> deleteForever(@PathVariable String id) {
    //     tareaService.deleteForever(id);
    //     Object response = new Object("Tarea eliminada permanentemente");
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @ModelAttribute("tarea") tarea tareaUpdate) {
        var tarea = tareaService.findOne(id).get();

        if (tarea != null) {

            tarea.setTitle(tareaUpdate.getTitle());
            tarea.setDue_date(tareaUpdate.getDue_date());
            tarea.setAssigned_to(tareaUpdate.getAssigned_to());
            tarea.setStatus(tareaUpdate.getStatus());

            tareaService.save(tarea);
            return new ResponseEntity<>(tarea, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Error tarea No encontrado", HttpStatus.BAD_REQUEST);
        }
    }

}
