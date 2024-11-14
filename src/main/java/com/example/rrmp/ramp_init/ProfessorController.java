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
public class ProfessorController {

    private final ProfessorBST professorBST = new ProfessorBST();

    public ProfessorController() {
        loadProfessors();
    }

    private void loadProfessors() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("professors.json").getInputStream();
            List<Professor> professors = mapper.readValue(inputStream, new TypeReference<List<Professor>>() {});

            for (Professor prof : professors) {
                professorBST.insert(prof);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/professors")
    public List<Professor> getProfessorsByCourse(@RequestParam String course) {
        List<Professor> sortedProfessors = professorBST.getSortedProfessors();

        return professorBST.getProfessorsInDescendingOrder()
                .stream()
                .filter(professor -> professor.getCourses().contains(course))
                .collect(Collectors.toList());
    }
}
