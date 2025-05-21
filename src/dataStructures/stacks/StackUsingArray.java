package dataStructures.stacks;

import java.util.EmptyStackException; // For handling empty stack scenarios

public class StackUsingArray {

    private int[] arr; // Array to store stack elements
    private int top;       // Index of the top element
    private int capacity;  // Maximum capacity of the stack

    // Constructor to initialize the stack
    public StackUsingArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.top = -1; // Stack is initially empty
        System.out.println("Stack created with capacity: " + capacity);
    }

    // Method to add an element to the top of the stack
    public void push(int data) {
        if (isFull()) {
            // Throw StackOverflowError if the stack is full
            throw new StackOverflowError("Stack is full. Cannot push " + data + ".");
        }
        arr[++top] = data; // Increment top and then add the element
        System.out.println(data + " pushed to stack. Current size: " + size());
    }

    // Method to remove and return the top element of the stack
    public int pop() {
        if (isEmpty()) {
            // Throw EmptyStackException if the stack is empty
            throw new EmptyStackException();
        }
        int data = arr[top--]; // Get the top element and then decrement top
        System.out.println(data + " popped from stack. Current size: " + size());
        return data;
    }

    // Method to return the top element of the stack without removing it
    public int peek() {
        if (isEmpty()) {
            // Throw EmptyStackException if the stack is empty
            throw new EmptyStackException();
        }
        return arr[top];
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Method to return the current number of elements in the stack
    public int size() {
        return top + 1;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        System.out.println("--- Creating a stack with capacity 3 ---");
        StackUsingArray stack = new StackUsingArray(3);

        System.out.println("\n--- Testing isEmpty() and isFull() on new stack ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // true
        System.out.println("Is stack full? " + stack.isFull());   // false
        System.out.println("Stack size: " + stack.size());       // 0

        System.out.println("\n--- Pushing elements ---");
        stack.push(10); // 10
        stack.push(20); // 10, 20
        stack.push(30); // 10, 20, 30

        System.out.println("\n--- Testing isEmpty() and isFull() on full stack ---");
        System.out.println("Is stack empty? " + stack.isEmpty()); // false
        System.out.println("Is stack full? " + stack.isFull());   // true
        System.out.println("Stack size: " + stack.size());       // 3

        System.out.println("\n--- Attempting to push to a full stack (Overflow) ---");
        try {
            stack.push(40);
        } catch (StackOverflowError e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        System.out.println("\n--- Peeking element ---");
        try {
            System.out.println("Top element (peek): " + stack.peek()); // 30
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Stack size after peek: " + stack.size()); // 3

        System.out.println("\n--- Popping elements ---");
        try {
            System.out.println("Popped element: " + stack.pop()); // 30
            System.out.println("Popped element: " + stack.pop()); // 20
        } catch (EmptyStackException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is stack empty? " + stack.isEmpty()); // false
        System.out.println("Stack size: " + stack.size());       // 1
        System.out.println("Top element (peek): " + stack.peek()); // 10

        System.out.println("\n--- Popping remaining element ---");
        try {
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
            System.err.println("Error caught: EmptyStackException (as expected)");
        }

        System.out.println("\n--- Attempting to peek from an empty stack ---");
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            System.err.println("Error caught: EmptyStackException (as expected for peek)");
        }
        
        System.out.println("\n--- Test with capacity 1 ---");
        StackUsingArray stack2 = new StackUsingArray(1);
        stack2.push(100);
        System.out.println("Is stack2 full? " + stack2.isFull()); // true
        try {
            stack2.push(200);
        } catch (StackOverflowError e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Popped from stack2: " + stack2.pop());
        System.out.println("Is stack2 empty? " + stack2.isEmpty()); // true
        
        System.out.println("\n--- Test with invalid capacity ---");
        try {
            new StackUsingArray(0);
        } catch (IllegalArgumentException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        try {
            new StackUsingArray(-1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        
        System.out.println("\n--- Demonstration finished ---");
    }
}
