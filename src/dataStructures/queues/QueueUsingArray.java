package dataStructures.queues;

import java.util.NoSuchElementException; // For handling empty queue scenarios

public class QueueUsingArray {

    private int[] arr;        // Array to store queue elements
    private int front;        // Index of the front element
    private int rear;         // Index of the rear element
    private int capacity;     // Maximum capacity of the queue
    private int currentSize;  // Current number of elements in the queue

    // Constructor to initialize the queue
    public QueueUsingArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = 0;       // Front is at the beginning of the array
        this.rear = -1;       // Rear is -1 as the queue is initially empty
        this.currentSize = 0; // Queue is initially empty
        System.out.println("Linear Queue created with capacity: " + capacity);
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return currentSize == capacity;
    }

    // Method to return the current number of elements in the queue
    public int size() {
        return currentSize;
    }

    // Method to add an element to the rear of the queue
    public void enqueue(int data) {
        if (isFull()) {
            // Throw IllegalStateException if the queue is full
            throw new IllegalStateException("Queue is full. Cannot enqueue " + data + ".");
        }
        // In a linear queue, rear always moves forward.
        // No wrap-around logic for rear in this simple linear implementation.
        rear = rear + 1; // Increment rear. For the first element, rear becomes 0.
        arr[rear] = data;
        currentSize++;
        System.out.println(data + " enqueued. Front: " + front + ", Rear: " + rear + ", Size: " + currentSize);
    }

    // Method to remove and return the front element of the queue
    public int dequeue() {
        if (isEmpty()) {
            // Throw NoSuchElementException if the queue is empty
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        int dequeuedData = arr[front];
        // In a linear queue, front always moves forward.
        // The space of dequeued elements is not reused in this simple linear queue.
        front = front + 1;
        currentSize--;
        System.out.println(dequeuedData + " dequeued. Front: " + front + ", Rear: " + rear + ", Size: " + currentSize);
        
        // Optional: If all elements are dequeued, reset pointers to save space
        // for potential (but unlikely in this simple linear model) reuse or just for clarity.
        // However, standard linear queues typically don't reset like this unless they become completely empty
        // and are designed for some form of limited reuse or to indicate a truly "fresh" state.
        // For this basic example, we'll keep it simple and not reset aggressively.
        // If currentSize becomes 0, it means front has passed rear (or rear is -1 and front is 0).
        if (isEmpty()) {
             // Reset pointers to initial state when queue becomes completely empty
             // This makes the linear queue behave more predictably for future enqueues if any were allowed
             // after becoming full and then empty without being circular.
            this.front = 0;
            this.rear = -1;
            System.out.println("Queue became empty, pointers reset. Front: " + this.front + ", Rear: " + this.rear);
        }
        return dequeuedData;
    }

    // Method to return the front element of the queue without removing it
    public int peek() {
        if (isEmpty()) {
            // Throw NoSuchElementException if the queue is empty
            throw new NoSuchElementException("Queue is empty. Cannot peek.");
        }
        return arr[front];
    }

    // Main method for demonstration
    public static void main(String[] args) {
        System.out.println("--- Creating a Linear Queue with capacity 3 ---");
        QueueUsingArray queue = new QueueUsingArray(3);

        System.out.println("\n--- Testing isEmpty() and isFull() on new queue ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // true
        System.out.println("Is queue full? " + queue.isFull());   // false
        System.out.println("Queue size: " + queue.size());       // 0
        System.out.println("Initial state: Front: " + queue.front + ", Rear: " + queue.rear);


        System.out.println("\n--- Enqueuing elements ---");
        queue.enqueue(10); // Front: 0, Rear: 0
        queue.enqueue(20); // Front: 0, Rear: 1
        queue.enqueue(30); // Front: 0, Rear: 2

        System.out.println("\n--- Testing isEmpty() and isFull() on full queue ---");
        System.out.println("Is queue empty? " + queue.isEmpty()); // false
        System.out.println("Is queue full? " + queue.isFull());   // true
        System.out.println("Queue size: " + queue.size());       // 3

        System.out.println("\n--- Attempting to enqueue to a full queue ---");
        try {
            queue.enqueue(40);
        } catch (IllegalStateException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("State after full: Front: " + queue.front + ", Rear: " + queue.rear + ", Size: " + queue.size());

        System.out.println("\n--- Peeking element ---");
        try {
            System.out.println("Front element (peek): " + queue.peek()); // 10
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Queue size after peek: " + queue.size()); // 3

        System.out.println("\n--- Dequeuing elements (Highlighting Linear Queue Behavior) ---");
        try {
            // Array: [10, 20, 30], Front: 0, Rear: 2, Size: 3
            System.out.println("Dequeued element: " + queue.dequeue()); // 10. Front: 1, Rear: 2, Size: 2. Array state: [_, 20, 30]
            System.out.println("Front element (peek): " + queue.peek());   // 20
            
            // Array: [_, 20, 30], Front: 1, Rear: 2, Size: 2
            System.out.println("Dequeued element: " + queue.dequeue()); // 20. Front: 2, Rear: 2, Size: 1. Array state: [_, _, 30]
            System.out.println("Front element (peek): " + queue.peek());   // 30

        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is queue empty? " + queue.isEmpty()); // false
        System.out.println("Queue size: " + queue.size());       // 1
        System.out.println("State: Front: " + queue.front + ", Rear: " + queue.rear + ", Size: " + queue.size());


        System.out.println("\n--- Dequeuing remaining element ---");
        try {
            // Array: [_, _, 30], Front: 2, Rear: 2, Size: 1
            System.out.println("Dequeued element: " + queue.dequeue()); // 30. Front: 0, Rear: -1, Size: 0 (due to reset)
        } catch (NoSuchElementException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        System.out.println("Is queue empty? " + queue.isEmpty()); // true
        System.out.println("Queue size: " + queue.size());       // 0
        System.out.println("State after becoming empty: Front: " + queue.front + ", Rear: " + queue.rear);


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
        
        System.out.println("\n--- Test with invalid capacity ---");
        try {
            new QueueUsingArray(0);
        } catch (IllegalArgumentException e) {
            System.err.println("Error caught: " + e.getMessage());
        }
        
        System.out.println("\n--- Enqueuing again after queue was emptied (Linear Queue Limitation) ---");
        // In this simple linear queue, once 'rear' reaches capacity-1, enqueue cannot happen
        // even if 'front' has moved and there is space, unless we implement more complex logic
        // (which would lead to a circular queue).
        // Our current 'enqueue' only checks currentSize against capacity.
        // Our 'dequeue' resets front and rear when the queue becomes empty,
        // which allows reuse from the beginning.
        
        System.out.println("Re-initializing queue for clarity on linear behavior after empty and reset.");
        queue = new QueueUsingArray(2);
        queue.enqueue(100); // F:0, R:0
        queue.enqueue(200); // F:0, R:1 (Queue is full)
        System.out.println("Is queue full? " + queue.isFull()); // true

        queue.dequeue();    // F:1, R:1. Dequeued 100.
        System.out.println("After dequeue 1: Front: " + queue.front + ", Rear: " + queue.rear + ", Size: " + queue.size());

        // At this point, front = 1, rear = 1, capacity = 2, currentSize = 1. The queue is NOT full.
        // However, in a *very* simple linear queue where rear only increments and doesn't wrap,
        // if rear had reached capacity-1, new enqueues would fail.
        // Our implementation uses `rear = rear + 1` and `arr[rear]`.
        // If rear became, say, 2 (for capacity 3) and then we dequeued, rear stays at 2.
        // The next enqueue would try `rear = 2 + 1 = 3`, which is out of bounds if capacity is 3.
        // Let's test this specific scenario.

        System.out.println("\n--- Testing Linear Queue 'Stuck' Rear Phenomenon (if not reset) ---");
        QueueUsingArray q2 = new QueueUsingArray(2); // Capacity 2
        q2.enqueue(1); // F:0, R:0, Size:1. arr:[1, _]
        q2.enqueue(2); // F:0, R:1, Size:2. arr:[1, 2]. Queue is full. Rear is at capacity-1.
        
        q2.dequeue();  // F:1, R:1, Size:1. Dequeued 1. arr:[_, 2]
                       // Now, arr[0] is "logically" empty. Front is 1. Rear is 1.
                       // The queue is not full (size=1, capacity=2).
                       // Can we enqueue?
        System.out.println("q2 state: Front: " + q2.front + ", Rear: " + q2.rear + ", Size: " + q2.size());
        try {
            // Next enqueue will do: rear = rear + 1 = 1 + 1 = 2.
            // arr[2] would be an ArrayIndexOutOfBoundsException for capacity 2.
            // Our current `isFull()` (currentSize == capacity) would prevent this specific one.
            // Let's make it not full.
            // q2.dequeue(); // This would make it empty and reset. Let's avoid that for this test.
            
            // The primary issue in a simple linear queue is that rear cannot go beyond capacity-1.
            // If rear is at capacity-1, and the queue is NOT full (because we dequeued items from front),
            // a naive `enqueue` that only increments `rear` and checks `isFull` might still try to write
            // past the array boundary if `isFull` was the *only* guard.
            // Our `rear = rear + 1` is the key.
            // If rear is already at capacity-1, `rear + 1` is `capacity`.
            // The assignment `arr[rear]` (i.e. `arr[capacity]`) would be an error.
            // Our `isFull()` check (currentSize == capacity) correctly prevents enqueues when full.
            // The "stuck" phenomenon refers to the inability to use freed space at the *beginning* of the array.
            // Our implementation *will* hit ArrayIndexOutOfBounds if we try to enqueue when:
            //   1. rear == capacity - 1
            //   2. currentSize < capacity (i.e., queue is not full because items were dequeued from front)
            // This is because the `isFull()` check will pass, then `rear` will increment beyond `capacity-1`.
            
            // Let's demonstrate this specific issue:
            System.out.println("Attempting to enqueue into q2 which is not full, but rear is at capacity-1.");
            // q2 state: Front: 1, Rear: 1, Size: 1, Capacity: 2. arr:[_, 2]
            // isFull() is false.
            // enqueue(3) will: rear = 1 + 1 = 2. arr[2] = 3; -> ArrayIndexOutOfBoundsException
            q2.enqueue(3); // This should cause an error if not handled.
            // Our implementation DOES NOT explicitly handle this ArrayIndexOutOfBounds for `rear`
            // beyond the `isFull()` check. It relies on `isFull()` being the primary guard.
            // If `isFull()` is false, but `rear` is `capacity-1`, then `rear+1` will be `capacity`.
            // `arr[capacity]` IS an `ArrayIndexOutOfBoundsException`.

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error caught as expected (ArrayIndexOutOfBounds): " + e.getMessage() + " - Illustrates linear queue limitation.");
        } catch (IllegalStateException e) {
             System.err.println("Error caught (IllegalStateException): " + e.getMessage());
        }
        
        System.out.println("\n--- Demonstration finished ---");
    }
}
