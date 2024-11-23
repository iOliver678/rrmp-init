package com.example.rrmp.ramp_init;

import com.example.rrmp.ramp_init.Professor;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:5174" })
public class ProfessorController {

    private final ProfessorBST professorBST;

    public ProfessorController() {
        this.professorBST = new ProfessorBST();
        loadProfessors();
    }

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
                    System.out.println("Inserted professor: " + prof.getTFname() + " " + prof.getTLname() +
                            " Rating: " + prof.getOverall_rating() +
                            " NumRatings: " + prof.getTNumRatings() +
                            " Class: " + prof.getRating_class());
                }
            }

            System.out.println("BST construction completed");
        } catch (IOException e) {
            System.err.println("Error loading professors: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/professors")
    public List<Professor> getProfessorsByCourse(@RequestParam String course) {
        List<Professor> professors = professorBST.getProfessorsInDescendingOrder()
                .stream()
                .filter(professor -> professor.getCourses().contains(course))
                .collect(Collectors.toList());

        System.out.println("Found " + professors.size() + " professors for course: " + course);
        return professors;
    }
}
