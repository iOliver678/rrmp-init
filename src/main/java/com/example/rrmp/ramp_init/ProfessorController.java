package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * REST Controller for handling professor-related HTTP requests.
 */

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:5174" })
public class ProfessorController {

    private final ProfessorBST professorBST;

    /**
     * Initializes the controller and loads professor data.
     */
    public ProfessorController() {
        this.professorBST = new ProfessorBST();
        loadProfessors();
    }

     /**
     * Loads professor data from JSON file and initializes the BST.
     * Only professors with valid ratings are inserted into the BST.
     */
    private void loadProfessors() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("professors-all.json").getInputStream();
            List<Professor> professors = mapper.readValue(inputStream, new TypeReference<List<Professor>>() {
            });

            System.out.println("Loaded " + professors.size() + " professors from JSON");

            for (Professor prof : professors) {
                if (prof.getNumericRating() > 0) {
                    professorBST.insert(prof);
                }
            }

            System.out.println("BST construction completed");
        } catch (IOException e) {
            System.err.println("Error loading professors: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Searches for professors teaching a specific course.
     *
     * @param course The course name to search for
     * @return List<Professor> containing professors teaching the specified course
     */
    @GetMapping("/professors")
    public List<Professor> searchByCourse(@RequestParam String course) {
        return professorBST.searchByCourse(course);
    }

}
