package com.example.rrmp.ramp_init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the RAMP (Rate A My Professor) application.
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 */
@SpringBootApplication
public class RampInitApplication {
	
	 /**
     * The main entry point of the application.
     * This method starts the Spring application context and runs the RAMP application.
     *
     * @param args Command line arguments passed to the application
     */
	public static void main(String[] args) {
		SpringApplication.run(RampInitApplication.class, args);
	}
}
