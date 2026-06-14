package com.ayush;

public class StackOverflowDemo {

    public static void main(String[] args) {
        infiniteRecursion(1);
    }

    public static void infiniteRecursion(int depth) {
        // No base case — stack keeps growing forever
        System.out.println("Depth: " + depth);
        infiniteRecursion(depth + 1);
    }
}