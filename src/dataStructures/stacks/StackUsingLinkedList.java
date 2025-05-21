package dataStructures.stacks;

import java.util.EmptyStackException; // For handling empty stack scenarios

public class StackUsingLinkedList {

    // Inner class Node to represent elements in the linked list
    private static class Node {
        int data;
        Node next;

        // Constructor for Node
        Node(int d) {
            data = d;
            next = null;
        }
    }

    private Node top; // Points to the top of the stack (head of the linked list)
    private int size;   // Current number of elements in the stack

    // Constructor to initialize the stack
    public StackUsingLinkedList() {
        top = null; // Stack is initially empty
        size = 0;
        System.out.println("Stack created using LinkedList.");
    }

    // Method to add an element to the top of the stack
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top; // New node points to the current top
        top = newNode;      // New node becomes the new top
        size++;
        System.out.println(data + " pushed to stack. Current size: " + size());
    }

    // Method to remove and return the top element of the stack
    public int pop() {
        if (isEmpty()) {
            // Throw EmptyStackException if the stack is empty
            throw new EmptyStackException();
        }
        int poppedData = top.data; // Get data from the top node
        top = top.next;            // Move top to the next node
        size--;
        System.out.println(poppedData + " popped from stack. Current size: " + size());
        return poppedData;
    }

    // Method to return the top element of the stack without removing it
    public int peek() {
        if (isEmpty()) {
            // Throw EmptyStackException if the stack is empty
            throw new EmptyStackException();
        }
        return top.data;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == null; // Or size == 0
    }

    // Method to return the current number of elements in the stack
    public int size() {
        return size;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        System.out.println("--- Creating a stack using LinkedList ---");
        StackUsingLinkedList stack = new StackUsingLinkedList();

        System.out.println("\n--- Testing isEmpty() on new stack ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // true
        System.out.println("Stack size: " + stack.size());       // 0

        System.out.println("\n--- Pushing elements ---");
        stack.push(10); // 10
        stack.push(20); // 20 -> 10
        stack.push(30); // 30 -> 20 -> 10
        stack.push(40); // 40 -> 30 -> 20 -> 10 (top is 40)

        System.out.println("\n--- Testing isEmpty() on non-empty stack ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // false
        System.out.println("Stack size: " + stack.size());       // 4

        System.out.println("\n--- Peeking element ---");
        try {
            System.out.println("Top element (peek): " + stack.peek()); // 40
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Stack size after peek: " + stack.size()); // 4

        System.out.println("\n--- Popping elements ---");
        try {
            System.out.println("Popped element: " + stack.pop()); // 40
            System.out.println("Popped element: " + stack.pop()); // 30
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is stack empty? " + stack.isEmpty()); // false
        System.out.println("Stack size: " + stack.size());       // 2
        try {
            System.out.println("Top element (peek) after pops: " + stack.peek()); // 20
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }


        System.out.println("\n--- Popping remaining elements ---");
        try {
            System.out.println("Popped element: " + stack.pop()); // 20
            System.out.println("Popped element: " + stack.pop()); // 10
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is stack empty? " + stack.isEmpty()); // true
        System.out.println("Stack size: " + stack.size());       // 0

        System.out.println("\n--- Attempting to pop from an empty stack (Underflow) ---");
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.err.println("Error caught: EmptyStackException (as expected for pop)");
        }

        System.out.println("\n--- Attempting to peek from an empty stack ---");
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            System.err.println("Error caught: EmptyStackException (as expected for peek)");
        }
        
        System.out.println("\n--- Pushing after empty ---");
        stack.push(100);
        System.out.println("Is stack empty? " + stack.isEmpty()); // false
        System.out.println("Stack size: " + stack.size());       // 1
        System.out.println("Top element (peek): " + stack.peek()); // 100
        System.out.println("Popped element: " + stack.pop()); // 100
        System.out.println("Is stack empty? " + stack.isEmpty()); // true

        System.out.println("\n--- Demonstration finished ---");
    }
}
