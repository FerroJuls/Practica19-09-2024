package com.planificadortareas.planificadortareas.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tarea")
public class tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "title" , nullable = false, length = 100)
    private String title;

    @Column(name = "due_date", nullable = false, length = 11)
    private Date  due_date;

    @Column(name = "assigned_to", nullable = false, length = 100)
    private String assigned_to;

    @Column(name = "notificado", nullable = true, length = 40)
    private Date notificado;

    // @Column(name = "status", nullable = false, length = 30)
    // private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusTarea status;
    
}