package com.planificadortareas.planificadortareas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.planificadortareas.planificadortareas.Models.tarea;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public String notificacionRegistro(tarea tarea){
        try{
            String asunto = "¡Registro de Tarea Exitoso!";
            String cuerpo = ""
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
                + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
                + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\"> Tarea Registrada: " + tarea.getTitle() + "</h1>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">La tarea <strong></strong> ha sido registrada correctamente.</p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Fecha de vencimiento: <strong>" + tarea.getDue_date() + "</strong></p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Estado: <strong>" + tarea.getStatus() + "</strong></p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">No dudes en contactarnos si necesitas más ayuda.</p>\r\n"
                + "</div>\r\n"
                + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
                + "</div>\r\n"
                + "</body>";
            
            var retorno = enviarCorreo(tarea.getAssigned_to(), asunto, cuerpo);
            if (retorno) {
                return "se envió correctamente";
            } else {
                return "No se pudo enviar";
            }

        }catch (Exception e) {
            // TODO: handle exception
            return "Error al envíar "+e.getMessage();
        }
    }

    public String recordarTareaPendiente(tarea tarea){
        try {
            String asunto = "Recordatorio: Tarea Próxima a Caducar";
            String cuerpo = ""
                + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
                + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
                + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">Recordatorio de Tarea: " + tarea.getTitle() + "</h1>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Te recordamos que la tarea <strong>" + tarea.getTitle() + "</strong> está próxima a caducar.</p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Fecha de vencimiento: <strong>" + tarea.getDue_date() + "</strong></p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Estado: <strong>" + tarea.getStatus() + "</strong></p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Solo faltan 5 días o menos para que esta tarea caduque. Asegúrate de completarla a tiempo.</p>\r\n"
                + "  <p style=\"font-size: 16px; color: #555555;\">Si necesitas ayuda, no dudes en contactarnos.</p>\r\n"
                + "</div>\r\n"
                + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
                + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
                + "</div>\r\n"
                + "</body>";
        
            var retorno = enviarCorreo(tarea.getAssigned_to(), asunto, cuerpo);
            if (retorno) {
                return "se envió correctamente";
            } else {
                return "No se pudo enviar";
            }
        

        }catch (Exception e) {
            // TODO: handle exception
            return "Error al envíar "+e.getMessage();
        }
    }


    // public String actualizarContraseña(usuario usuario){
    //     try{
    //         String asunto = "Actualiza tu contraseña";
    //         String cuerpo = ""
    //             + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
    //             + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
    //             + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">¡Hola! "+usuario.getNombre()+"</h1>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Es hora de actualizar tu contraseña para mantener tu cuenta segura.</p>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Te recomendamos hacer esto cada cierto tiempo para proteger tu información.</p>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Accede a tu cuenta y cambia tu contraseña en la sección de configuración.</p>\r\n"
    //             + "</div>\r\n"
    //             + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
    //             + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
    //             + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
    //             + "</div>\r\n"
    //             + "</body>";


            

    //         var retorno=enviarCorreo(usuario.getCorreo(),asunto,cuerpo);
    //         if(retorno) {
    //             return "se envió correctamente";
    //         }else {
    //             return "No se pudo envíar";
    //         }

    //     }catch (Exception e) {
    //         // TODO: handle exception
    //         return "Error al envíar "+e.getMessage();
    //     }
    // }


    // public String cambiarTipoDocumento(usuario usuario ){
    //     try{
    //         String asunto = "Es hora de actualizar tu tipo de documento";
    //         String cuerpo = ""
    //             + "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
    //             + "<div style=\"max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\r\n"
    //             + "  <h1 style=\"font-size: 24px; font-weight: bold; color: #333333;\">¡Hola! "+usuario.getNombre()+"</h1>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Queremos recordarte que es el momento de actualizar tu tipo de documento. Es un proceso rápido que te ayudará a mantener tus datos al día en LaMafia.</p>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Por favor, accede a tu cuenta y realiza la actualización lo antes posible.</p>\r\n"
    //             + "  <p style=\"font-size: 16px; color: #555555;\">Gracias por tu colaboración.</p>\r\n"
    //             + "</div>\r\n"
    //             + "<div style=\"text-align: center; margin-top: 20px;\">\r\n"
    //             + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Términos y condiciones</a> | \r\n"
    //             + "  <a href=\"#\" style=\"font-size: 14px; color: #888888; text-decoration: none;\">Política de privacidad</a>\r\n"
    //             + "</div>\r\n"
    //             + "</body>";


            

    //         var retorno=enviarCorreo(usuario.getCorreo(),asunto,cuerpo);
    //         if(retorno) {
    //             return "se envió correctamente";
    //         }else {
    //             return "No se pudo envíar";
    //         }

    //     }catch (Exception e) {
    //         // TODO: handle exception
    //         return "Error al envíar "+e.getMessage();
    //     }
    // }


    public boolean enviarCorreo(String destinatario,String asunto,String cuerpo) throws MessagingException {
            try {
                MimeMessage message=javaMailSender.createMimeMessage();
                MimeMessageHelper helper=new MimeMessageHelper(message,true);
                
                helper.setTo(destinatario);
                helper.setSubject(asunto);
                helper.setText(cuerpo,true);
                
                javaMailSender.send(message);
                return true;
            }catch (Exception e) {

                return false;
            }
            
    }


}
