package com.example.rrmp.ramp_init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RampInitApplication implements CommandLineRunner {

	private final ProfessorBST professorBST = new ProfessorBST();

	public static void main(String[] args) {
		SpringApplication.run(RampInitApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Load JSON file from the resources directory
		professorBST.loadFromJson("/Users/oliver/Downloads/ramp-init/src/main/resources/professors.json");
		System.out.println("Data loaded into BST");
	}
}
