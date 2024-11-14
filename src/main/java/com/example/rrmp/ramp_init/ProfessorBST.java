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
        root = insertRec(root, professor);
    }

    private BSTNode insertRec(BSTNode root, Professor professor) {
        if (root == null) {
            root = new BSTNode(professor);
            return root;
        }

        if (professor.getNumericRating() < root.professor.getNumericRating()) {
            root.left = insertRec(root.left, professor);
        } else if (professor.getNumericRating() > root.professor.getNumericRating()) {
            root.right = insertRec(root.right, professor);
        }

        return root;
    }

    public void loadFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Professor> professors = mapper.readValue(new File(filePath), new TypeReference<List<Professor>>() {});
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
}
