package com.example.rrmp.ramp_init;

/**
 * Represents a node in the Binary Search Tree storing Professor data.
 * Each node contains a professor object and references to left and right child nodes.
 */
public class BSTNode {
    Professor professor;
    BSTNode left, right;

    /**
     * Creates a new BST node with the given professor data.
     *
     * @param professor The Professor object to be stored in this node
     */
    public BSTNode(Professor professor) {
        this.professor = professor;
        this.left = null;
        this.right = null;
    }
}
