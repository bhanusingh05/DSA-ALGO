package dataStructures.queues;

import java.util.NoSuchElementException; // For handling empty queue scenarios

public class QueueUsingLinkedList {

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

    private Node front; // Points to the front of the queue
    private Node rear;  // Points to the rear of the queue
    private int size;   // Current number of elements in the queue

    // Constructor to initialize the queue
    public QueueUsingLinkedList() {
        front = null;
        rear = null;
        size = 0;
        System.out.println("Queue created using LinkedList.");
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0; // Or front == null
    }

    // Method to return the current number of elements in the queue
    public int size() {
        return size;
    }

    // Method to add an element to the rear of the queue
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            // If the queue is empty, the new node is both front and rear
            front = newNode;
            rear = newNode;
        } else {
            // Otherwise, add the new node to the end and update rear
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println(data + " enqueued. Front: " + (front != null ? front.data : "null") + ", Rear: " + (rear != null ? rear.data : "null") + ", Size: " + size);
    }

    // Method to remove and return the front element of the queue
    public int dequeue() {
        if (isEmpty()) {
            // Throw NoSuchElementException if the queue is empty
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        int dequeuedData = front.data; // Get data from the front node
        front = front.next;            // Move front to the next node
        size--;

        // If the queue becomes empty after dequeuing, rear should also be null
        if (isEmpty()) {
            rear = null;
        }
        System.out.println(dequeuedData + " dequeued. Front: " + (front != null ? front.data : "null") + ", Rear: " + (rear != null ? rear.data : "null") + ", Size: " + size);
        return dequeuedData;
    }

    // Method to return the front element of the queue without removing it
    public int peek() {
        if (isEmpty()) {
            // Throw NoSuchElementException if the queue is empty
            throw new NoSuchElementException("Queue is empty. Cannot peek.");
        }
        return front.data;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        System.out.println("--- Creating a Queue using LinkedList ---");
        QueueUsingLinkedList queue = new QueueUsingLinkedList();

        System.out.println("\n--- Testing isEmpty() on new queue ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // true
        System.out.println("Queue size: " + queue.size());       // 0
        System.out.println("Initial state: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null"));


        System.out.println("\n--- Enqueuing elements ---");
        queue.enqueue(10); // Front: 10, Rear: 10
        queue.enqueue(20); // Front: 10, Rear: 20
        queue.enqueue(30); // Front: 10, Rear: 30

        System.out.println("\n--- Testing isEmpty() on non-empty queue ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // false
        System.out.println("Queue size: " + queue.size());       // 3

        System.out.println("\n--- Peeking element ---");
        try {
            System.out.println("Front element (peek): " + queue.peek()); // 10
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Queue size after peek: " + queue.size()); // 3
        System.out.println("State after peek: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null"));


        System.out.println("\n--- Dequeuing elements ---");
        try {
            // Queue: 10 -> 20 -> 30 (Front: 10, Rear: 30)
            System.out.println("Dequeued element: " + queue.dequeue()); // 10. Front: 20, Rear: 30
            System.out.println("Front element (peek): " + queue.peek());   // 20
            
            // Queue: 20 -> 30 (Front: 20, Rear: 30)
            System.out.println("Dequeued element: " + queue.dequeue()); // 20. Front: 30, Rear: 30
            System.out.println("Front element (peek): " + queue.peek());   // 30

        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is queue empty? " + queue.isEmpty()); // false
        System.out.println("Queue size: " + queue.size());       // 1
        System.out.println("State: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null"));


        System.out.println("\n--- Dequeuing remaining element (handles rear pointer update) ---");
        try {
            // Queue: 30 (Front: 30, Rear: 30)
            System.out.println("Dequeued element: " + queue.dequeue()); // 30. Front: null, Rear: null
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is queue empty? " + queue.isEmpty()); // true
        System.out.println("Queue size: " + queue.size());       // 0
        System.out.println("State after becoming empty: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null"));


        System.out.println("\n--- Attempting to dequeue from an empty queue ---");
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }

        System.out.println("\n--- Attempting to peek from an empty queue ---");
        try {
            queue.peek();
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        
        System.out.println("\n--- Enqueuing again after queue was emptied ---");
        queue.enqueue(100); // Front: 100, Rear: 100
        System.out.println("Is queue empty? " + queue.isEmpty()); // false
        System.out.println("Queue size: " + queue.size());       // 1
        System.out.println("Front element (peek): " + queue.peek()); // 100
        System.out.println("State: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null"));

        queue.enqueue(200); // Front: 100, Rear: 200
        System.out.println("State: Front: " + (queue.front != null ? queue.front.data : "null") + ", Rear: " + (queue.rear != null ? queue.rear.data : "null") + ", Size: " + queue.size());
        
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeue 100. Front: 200, Rear: 200
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeue 200. Front: null, Rear: null
        System.out.println("Is queue empty? " + queue.isEmpty()); // true

        System.out.println("\n--- Demonstration finished ---");
    }
}
