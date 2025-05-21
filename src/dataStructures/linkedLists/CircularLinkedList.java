package dataStructures.linkedLists;

public class CircularLinkedList {

    private Node tail; // Points to the last node of the list. tail.next is the head.
    private int size;  // To keep track of the number of elements

    // Inner class Node
    private static class Node {
        int data;
        Node next;

        // Constructor for Node
        Node(int d) {
            data = d;
            next = null; // Will be set appropriately during insertion
        }
    }

    // Constructor for CircularLinkedList
    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return tail == null;
    }

    // Method to get the size of the list
    public int getSize() {
        return size;
    }

    // Method to insert a new node at the head of the list
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail; // Points to itself
        } else {
            newNode.next = tail.next; // New node points to the current head
            tail.next = newNode;      // Tail's next (which was head) now points to the new node
        }
        size++;
        System.out.println(data + " inserted at head.");
    }

    // Method to insert a new node at the tail of the list
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            tail = newNode;
            tail.next = tail; // Points to itself
        } else {
            newNode.next = tail.next; // New node's next points to the current head
            tail.next = newNode;      // Current tail's next points to the new node
            tail = newNode;           // New node becomes the new tail
        }
        size++;
        System.out.println(data + " inserted at tail.");
    }

    // Method to delete the node at the head of the list
    public void deleteAtHead() {
        if (isEmpty()) {
            System.out.println("Deletion failed: List is empty.");
            return;
        }
        int deletedData = tail.next.data; // Head's data
        if (tail.next == tail) { // Only one node in the list
            tail = null;
        } else {
            tail.next = tail.next.next; // Tail's next (head) bypasses the old head
        }
        size--;
        System.out.println(deletedData + " deleted from head.");
    }

    // Method to delete the first occurrence of a node with the given value
    public void deleteByValue(int data) {
        if (isEmpty()) {
            System.out.println("Deletion failed: List is empty or value not found.");
            return;
        }

        Node head = tail.next;
        Node current = head;
        Node previous = tail; // Previous to head is tail

        // Traverse the list to find the node
        // We need to stop if we've checked all nodes or found the data
        do {
            if (current.data == data) {
                // Case 1: Node to delete is the only node
                if (size == 1) { // or current == tail && current.next == current
                    tail = null;
                }
                // Case 2: Node to delete is the head (tail.next)
                else if (current == head) { // current == tail.next
                    tail.next = head.next; // Tail points to the new head
                    // If the deleted node was also the tail (which means it was the only node, handled above)
                    // This check is more for clarity, 'size == 1' handles it.
                }
                // Case 3: Node to delete is the tail
                else if (current == tail) {
                    previous.next = tail.next; // previous node now points to head
                    tail = previous;           // Update tail
                }
                // Case 4: Node is in the middle
                else {
                    previous.next = current.next; // Bypass the current node
                }
                size--;
                System.out.println(data + " deleted from the list.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head); // Iterate until we are back at the head

        System.out.println("Deletion failed: Value " + data + " not found in the list.");
    }


    // Method to search for a value in the list
    public boolean search(int data) {
        if (isEmpty()) {
            return false;
        }
        Node current = tail.next; // Start from head
        do {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        } while (current != tail.next); // Iterate until we are back at the head
        return false;
    }

    // Method to display the elements of the list
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.print("List (tail -> " + tail.data + "): head -> ");
        Node current = tail.next; // Start from head
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != tail.next); // Iterate until we are back at the head
        System.out.println("(head again: " + current.data + ")");
    }

    // Main method for demonstration
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());
        list.display();

        System.out.println("\n--- Inserting elements ---");
        list.insertAtHead(10); // List: 10 (tail & head)
        list.display();
        list.insertAtTail(20); // List: 10 -> 20 (tail is 20)
        list.display();
        list.insertAtHead(5);  // List: 5 -> 10 -> 20 (tail is 20, head is 5)
        list.display();
        list.insertAtTail(30); // List: 5 -> 10 -> 20 -> 30 (tail is 30)
        list.display();
        list.insertAtHead(1);  // List: 1 -> 5 -> 10 -> 20 -> 30 (tail is 30, head is 1)
        list.display();
        System.out.println("Size: " + list.getSize());


        System.out.println("\n--- Searching elements ---");
        System.out.println("Search for 10: " + list.search(10)); // true
        System.out.println("Search for 30: " + list.search(30)); // true
        System.out.println("Search for 1: " + list.search(1));   // true
        System.out.println("Search for 99 (not in list): " + list.search(99)); // false

        System.out.println("\n--- Deleting elements ---");
        // Current list: 1 -> 5 -> 10 -> 20 -> 30 (tail is 30, head is 1)
        list.display();

        System.out.println("Deleting head (1):");
        list.deleteAtHead(); // List: 5 -> 10 -> 20 -> 30 (tail is 30, head is 5)
        list.display();
        System.out.println("Size: " + list.getSize());

        System.out.println("Deleting by value (20 - middle):");
        list.deleteByValue(20); // List: 5 -> 10 -> 30 (tail is 30, head is 5)
        list.display();
        System.out.println("Size: " + list.getSize());

        System.out.println("Deleting by value (30 - tail):");
        list.deleteByValue(30); // List: 5 -> 10 (tail is 10, head is 5)
        list.display();
        System.out.println("Size: " + list.getSize());
        
        System.out.println("Deleting by value (5 - new head):");
        list.deleteByValue(5);  // List: 10 (tail is 10, head is 10)
        list.display();
        System.out.println("Size: " + list.getSize());

        System.out.println("Deleting by value (99 - not in list):");
        list.deleteByValue(99);
        list.display();

        System.out.println("Deleting remaining (10 - only node):");
        list.deleteByValue(10); // List: empty
        list.display();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());

        System.out.println("\n--- Operations on empty list ---");
        list.deleteAtHead();    // Attempt delete from empty
        list.deleteByValue(5);  // Attempt delete from empty
        System.out.println("Search for 5 in empty list: " + list.search(5)); // false
        list.display();

        System.out.println("\n--- Test deleting single node list variations ---");
        list.insertAtHead(77); // List: 77
        list.display();
        System.out.println("Deleting head (77):");
        list.deleteAtHead();
        list.display();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());

        list.insertAtTail(88); // List: 88
        list.display();
        System.out.println("Deleting by value (88):");
        list.deleteByValue(88);
        list.display();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());
        
        System.out.println("\n--- Test deleting head when it's also tail (after some ops) ---");
        list.insertAtHead(100);
        list.insertAtTail(200); // 100 -> 200 (tail 200)
        list.display();
        list.deleteByValue(100); // delete head -> 200 (tail 200)
        list.display();
        list.deleteByValue(200); // delete tail (becomes only node) -> empty
        list.display();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());
    }
}
