package com.planificadortareas.planificadortareas.Taks;

// import java.sql.Date;
// import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.planificadortareas.planificadortareas.Models.tarea;
import com.planificadortareas.planificadortareas.Service.emailService;
import com.planificadortareas.planificadortareas.Service.tareaService;

@Component
public class Taks {

    @Autowired
    private tareaService data;

    @Autowired
    private emailService email;

    @Scheduled(cron = "0 * * * * *")
    public void sendNotificationRegistrocron() {
// traer toda la lista que notificado sea null, recorrer el arreglo 1*1 enviando el correo electronico y luego actualizando este registro con la fecha que se esta enviando la notificacion
        var listaTarea=data.notificacionRegistro();
        for(tarea tarea:listaTarea){
            System.out.println("tarea Registro Exitoso "+ 
            tarea.getTitle());
            email.notificacionRegistro(tarea);
            tarea.setNotificado(new Date());
            data.save(tarea);
        }
    }

    @Scheduled(cron= "0 56 17 * * *" )
    public void sendNotificationcronRecordarConDiasDeAnterioridad(){

        var listaTarea=data.recordarTareaPendiente();
        for(tarea tarea:listaTarea){
            System.out.println("recordatorio con 5 dias de anticipacion"+
            tarea.getTitle());
            email.recordarTareaPendiente(tarea);
        }

    }

    // @Scheduled(cron= "0 35 8 * * *" )
    // public void sendNotificationcroniniciosesionNotificar(){

    //     var listaUsuario=data.iniciosesionNotificar();
    //     for(usuario usuario:listaUsuario){
    //         System.out.println("Usuario inicia sesion pronto en la Mafia"+
    //         usuario.getNombre());
    //         email.iniciosesionNotificar(usuario);
    //     }

    // }


}

