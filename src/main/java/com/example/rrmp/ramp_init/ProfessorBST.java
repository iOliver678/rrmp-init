package com.example.rrmp.ramp_init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Binary Search Tree implementation for managing Professor objects.
 * Professors are ordered based on their numeric ratings.
 */
public class ProfessorBST {
    private BSTNode root;

    /**
     * Initializes an empty Binary Search Tree for professors.
     */
    public ProfessorBST() {
        root = null;
    }

     /**
     * Inserts a professor into the BST.
     * Only professors with valid ratings (> 0) are inserted.
     *
     * @param professor The Professor object to be inserted
     */
    public void insert(Professor professor) {
        if (professor.getNumericRating() > 0) { // Only insert professors with valid ratings
            root = insertRec(root, professor);
        }
    }

     /**
     * Recursively inserts a professor into the BST.
     *
     * @param root The root node of the current subtree
     * @param professor The Professor object to be inserted
     * @return The root node of the modified subtree
     */
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

    /**
     * Loads professor data from a JSON file and populates the BST.
     *
     * @param filePath Path to the JSON file containing professor data
     */
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

     /**
     * Returns a list of professors sorted by rating in ascending order.
     *
     * @return List<Professor> containing professors in ascending order of ratings
     */
    public List<Professor> getSortedProfessors() {
        List<Professor> sortedList = new ArrayList<>();
        inOrderRec(root, sortedList);
        return sortedList;
    }

    /**
     * Recursively performs in-order traversal to build sorted list.
     *
     * @param node The current node being processed
     * @param result The list to store the sorted professors
     */
    private void inOrderRec(BSTNode node, List<Professor> result) {
        if (node != null) {
            inOrderRec(node.left, result);
            result.add(node.professor);
            inOrderRec(node.right, result);
        }
    }

     /**
     * Returns a list of professors sorted by rating in descending order.
     *
     * @return List<Professor> containing professors in descending order of ratings
     */
    public List<Professor> getProfessorsInDescendingOrder() {
        List<Professor> result = new ArrayList<>();
        reverseInOrderRec(root, result);
        return result;
    }
    
    /**
     * Recursively performs reverse in-order traversal.
     *
     * @param node The current node being processed
     * @param result The list to store professors in descending order
     */
    private void reverseInOrderRec(BSTNode node, List<Professor> result) {
        if (node != null) {
            reverseInOrderRec(node.right, result);
            result.add(node.professor);
            reverseInOrderRec(node.left, result);
        }
    }

     /**
     * Searches for professors teaching a specific course.
     *
     * @param course The course name to search for
     * @return List<Professor> containing professors teaching the specified course
     */
    public List<Professor> searchByCourse(String course) {
        List<Professor> result = new ArrayList<>();
        searchByCourseRec(root, course, result); // Assuming `root` is a field in ProfessorBST
        return result;
    }

     /**
     * Recursively searches the BST for professors teaching a specific course.
     *
     * @param node The current node being processed
     * @param course The course name to search for
     * @param result The list to store matching professors
     */
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
