package com.ayush;

import java.util.ArrayList;
import java.util.List;

public class OOMDemo {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        try {
            while (true) {
                // Each iteration allocates 1MB on heap
                list.add(new byte[1024 * 1024]);
                System.out.println("Allocated: " + list.size() + " MB");
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM hit after: " + list.size() + " MB");
            System.out.println("Error message: " + e.getMessage());
        }
    }
}