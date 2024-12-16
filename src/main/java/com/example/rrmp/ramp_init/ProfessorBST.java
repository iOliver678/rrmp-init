package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorBST {
    private BSTNode root;

    public ProfessorBST() {
        root = null;
    }

    public void insert(Professor professor) {
        if (professor.getNumericRating() > 0) { // Only insert professors with valid ratings
            root = insertRec(root, professor);
        }
    }

    private BSTNode insertRec(BSTNode root, Professor professor) {
        if (root == null) {
            return new BSTNode(professor);
        }

        double profRating = professor.getNumericRating();
        double rootRating = root.professor.getNumericRating();

        if (profRating < rootRating) {
            root.left = insertRec(root.left, professor);
        } else {
            root.right = insertRec(root.right, professor);
        }

        return root;
    }

    public void loadFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Professor> professors = mapper.readValue(new File(filePath), new TypeReference<List<Professor>>() {
            });
            for (Professor prof : professors) {
                insert(prof);
            }
            System.out.println("Professors loaded from JSON and inserted into BST");
        } catch (IOException e) {
            System.err.println("Failed to load professors from JSON: " + e.getMessage());
        }
    }

    public List<Professor> getSortedProfessors() {
        List<Professor> sortedList = new ArrayList<>();
        inOrderRec(root, sortedList);
        return sortedList;
    }

    private void inOrderRec(BSTNode node, List<Professor> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.professor);
            inOrderRec(node.right, result);
        }
    }

    public List<Professor> getProfessorsInDescendingOrder() {
        List<Professor> result = new ArrayList<>();
        reverseInOrderRec(root, result);
        return result;
    }

    private void reverseInOrderRec(BSTNode node, List<Professor> result) {
        if (node != null) {
            reverseInOrderRec(node.right, result);
            result.add(node.professor);
            reverseInOrderRec(node.left, result);
        }
    }

    public List<Professor> searchByCourse(String course) {
        List<Professor> result = new ArrayList<>();
        searchByCourseRec(root, course, result); // Assuming `root` is a field in ProfessorBST
        return result;
    }

    private void searchByCourseRec(BSTNode node, String course, List<Professor> result) {
        if (node != null) {
            searchByCourseRec(node.right, course, result);
            if (node.professor.getCourses().contains(course)) {
                result.add(node.professor);
            }
            searchByCourseRec(node.left, course, result);
        }
    }
}
