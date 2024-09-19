package com.planificadortareas.planificadortareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PlanificadortareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanificadortareasApplication.class, args);
	}

}
