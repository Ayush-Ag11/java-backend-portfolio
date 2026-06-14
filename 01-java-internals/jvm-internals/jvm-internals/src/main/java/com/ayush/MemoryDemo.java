package com.ayush;

public class MemoryDemo {

    // This object lives on the HEAP
    static String heapString = "I am on the heap";

    public static void main(String[] args) {
        System.out.println("Heap variable: " + heapString);
        stackMethod(1);
    }

    public static void stackMethod(int depth) {
        // 'depth' is a local variable — lives on the STACK
        // Each recursive call creates a new stack frame
        int localVar = depth * 10;

        System.out.println("Stack depth: " + depth +
                " | localVar: " + localVar);

        if (depth < 5) {
            stackMethod(depth + 1);
        }
        // When method returns, stack frame is destroyed
        // localVar is gone from memory
    }
}