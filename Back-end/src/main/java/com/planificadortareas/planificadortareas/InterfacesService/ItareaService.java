package com.planificadortareas.planificadortareas.InterfacesService;

import java.util.List;
import java.util.Optional;

import com.planificadortareas.planificadortareas.Models.tarea;


public interface ItareaService {

    public String save(tarea tarea);

    public List<tarea> findAll();

    public Optional<tarea> findOne(String id);

    public int deleteForever(String id);

    // Filtro
    public List<tarea> filtroTarea(String filtro);
    
    public List<tarea> notificacionRegistro();

    public List<tarea> recordarTareaPendiente();

    // public List<tarea> actualizarContrasena();

    // public List<tarea> iniciosesionNotificar();
    
    // public List<tarea> notificacionRegistro();
    
}