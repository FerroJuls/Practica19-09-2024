package com.planificadortareas.planificadortareas.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planificadortareas.planificadortareas.Models.tarea;

@Repository
public interface Itarea extends CrudRepository<tarea,String> {

    @Query ("SELECT t FROM tarea t WHERE t.title LIKE %?1% OR t.assigned_to LIKE %?1% OR t.status LIKE %?1%")
    List<tarea> filtroTarea(String filtro);

    @Query("SELECT t FROM tarea t WHERE t.notificado is NULL")
    List<tarea> notificacionRegistro();

    @Query("SELECT t FROM tarea t WHERE t.status = 'Pendiente' AND DATEDIFF(NOW(), t.due_date) <= 5")
    List<tarea> recordarTareaPendiente();

    // @Query ("SELECT u FROM tarea u WHERE u.estado = 'Activo' AND TIMESTAMPDIFF(YEAR, u.nacimiento, NOW())>=18 AND td = 'TI'")
    // List<tarea> cambiarTipoDocumento();

    // @Query("SELECT u FROM tarea u WHERE u.estado = 'Activo' AND DATEDIFF(NOW(), u.iniciosesion) >= 30")
    // List<tarea> iniciosesionNotificar();

    // @Query("SELECT u FROM tarea u WHERE u.notificado is NULL")
    // List<tarea> notificacionRegistro();

}
