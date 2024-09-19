package com.planificadortareas.planificadortareas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planificadortareas.planificadortareas.Interfaces.Itarea;
import com.planificadortareas.planificadortareas.InterfacesService.ItareaService;
import com.planificadortareas.planificadortareas.Models.tarea;

@Service 
public class tareaService implements ItareaService {

    @Autowired
    private Itarea data;

    @Override
    public String save(tarea tarea) {
        data.save(tarea);
        return tarea.getId();
    }

    @Override
    public List<tarea> findAll(){
        List<tarea> listaTarea = (List<tarea>) data.findAll();
        return listaTarea;
    }

    @Override
    public Optional<tarea> findOne(String id){
        Optional<tarea> tarea = data.findById(id);
        return tarea;
    }

    @Override
    public int deleteForever(String id){
        data.deleteById(id);
        return 1;
    }

    // Filtro
    
    @Override
    public List<tarea> filtroTarea(String filtro) {
        List<tarea> listaTarea = data.filtroTarea(filtro);
        return listaTarea;
    }

    @Override
    public List<tarea> notificacionRegistro() {
        List<tarea> listaTarea = data.notificacionRegistro();
        return listaTarea;
    }

    
    @Override
    public List<tarea> recordarTareaPendiente() {
        List<tarea> listaTarea = data.recordarTareaPendiente();
        return listaTarea;
    }

    
    // @Override
    // public List<usuario> actualizarContrasena() {
    //     List<usuario> listaUsuario = data.actualizarContrasena();
    //     return listaUsuario;
    // }

    
    // @Override
    // public List<usuario> iniciosesionNotificar() {
    //     List<usuario> listaUsuario = data.iniciosesionNotificar();
    //     return listaUsuario;
    // }

    
    // @Override
    // public List<usuario> notificacionRegistro() {
    //     List<usuario> listaUsuario = data.notificacionRegistro();
    //     return listaUsuario;
    // }

    

}
