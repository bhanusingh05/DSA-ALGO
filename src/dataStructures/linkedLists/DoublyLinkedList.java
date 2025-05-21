package dataStructures.linkedLists;

public class DoublyLinkedList {

    private Node head; // Head of the list
    private Node tail; // Tail of the list
    private int size;  // To keep track of the number of elements

    // Inner class Node
    private static class Node {
        int data;
        Node next;
        Node prev;

        // Constructor for Node
        Node(int d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    // Constructor for DoublyLinkedList
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return size == 0; // or head == null
    }

    // Method to get the size of the list
    public int getSize() {
        return size;
    }

    // Method to insert a new node at the head of the list
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        System.out.println(data + " inserted at head.");
    }

    // Method to insert a new node at the tail of the list
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        System.out.println(data + " inserted at tail.");
    }

    // Method to insert a new node at a specific position (0-indexed)
    public void insertAtPosition(int data, int position) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position: " + position + ". Valid positions are 0 to " + size + ".");
            return;
        }
        if (position == 0) {
            insertAtHead(data);
            return;
        }
        if (position == size) {
            insertAtTail(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        // Traverse to the node currently at the target position
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        // Insert newNode before current
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
        System.out.println(data + " inserted at position " + position + ".");
    }

    // Method to delete the node at the head of the list
    public void deleteAtHead() {
        if (isEmpty()) {
            System.out.println("Deletion failed: List is empty.");
            return;
        }
        int deletedData = head.data;
        if (size == 1) { // Only one node
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        System.out.println(deletedData + " deleted from head.");
    }

    // Method to delete the node at the tail of the list
    public void deleteAtTail() {
        if (isEmpty()) {
            System.out.println("Deletion failed: List is empty.");
            return;
        }
        int deletedData = tail.data;
        if (size == 1) { // Only one node
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        System.out.println(deletedData + " deleted from tail.");
    }

    // Method to delete the first occurrence of a node with the given value
    public void deleteByValue(int data) {
        if (isEmpty()) {
            System.out.println("Deletion failed: List is empty.");
            return;
        }

        Node current = head;
        // Search for the node
        while (current != null && current.data != data) {
            current = current.next;
        }

        // If value not found
        if (current == null) {
            System.out.println("Deletion failed: Value " + data + " not found in the list.");
            return;
        }

        // If node to be deleted is head
        if (current == head) {
            deleteAtHead();
            return; // deleteAtHead already prints message and decrements size
        }
        // If node to be deleted is tail
        if (current == tail) {
            deleteAtTail();
            return; // deleteAtTail already prints message and decrements size
        }

        // Node is in the middle
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        System.out.println(data + " deleted from the list.");
    }

    // Method to search for a value in the list
    public boolean search(int data) {
        if (isEmpty()) {
            return false;
        }
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to display the elements of the list from head to tail
    public void displayForward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.print("List (forward): head <-> ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to display the elements of the list from tail to head
    public void displayBackward() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.print("List (backward): null <-> ");
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("head");
    }

    // Main method for demonstration
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("Is list empty? " + list.isEmpty());
        list.displayForward();
        list.displayBackward();

        System.out.println("\n--- Inserting elements ---");
        list.insertAtTail(10); // 10
        list.displayForward();
        list.insertAtHead(5);  // 5 <-> 10
        list.displayForward();
        list.insertAtTail(20); // 5 <-> 10 <-> 20
        list.displayForward();
        list.insertAtPosition(15, 2); // 5 <-> 10 <-> 15 <-> 20 (insert 15 at index 2)
        list.displayForward();
        list.insertAtPosition(2, 0);  // 2 <-> 5 <-> 10 <-> 15 <-> 20 (insert 2 at index 0)
        list.displayForward();
        list.insertAtPosition(25, 5); // 2 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25 (insert 25 at index 5 - end)
        list.displayForward();
        list.displayBackward();
        list.insertAtPosition(100, 10); // Attempt to insert out of bounds

        System.out.println("\nIs list empty? " + list.isEmpty() + ", Size: " + list.getSize());

        System.out.println("\n--- Searching elements ---");
        System.out.println("Search for 15: " + list.search(15));
        System.out.println("Search for 99 (not in list): " + list.search(99));

        System.out.println("\n--- Deleting elements ---");
        list.displayForward(); // 2 <-> 5 <-> 10 <-> 15 <-> 20 <-> 25
        
        System.out.println("Deleting head (2):");
        list.deleteAtHead();   // 5 <-> 10 <-> 15 <-> 20 <-> 25
        list.displayForward();
        list.displayBackward();

        System.out.println("Deleting tail (25):");
        list.deleteAtTail();   // 5 <-> 10 <-> 15 <-> 20
        list.displayForward();
        list.displayBackward();

        System.out.println("Deleting by value (10 - middle):");
        list.deleteByValue(10); // 5 <-> 15 <-> 20
        list.displayForward();
        list.displayBackward();

        System.out.println("Deleting by value (99 - not in list):");
        list.deleteByValue(99); 
        list.displayForward();

        System.out.println("Deleting head (5):");
        list.deleteAtHead();   // 15 <-> 20
        list.displayForward();
        
        System.out.println("Deleting tail (20):");
        list.deleteAtTail();   // 15
        list.displayForward();
        
        System.out.println("Deleting remaining (15 by value - becomes head and tail):");
        list.deleteByValue(15); // Empty
        list.displayForward();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());

        System.out.println("\n--- Operations on empty list ---");
        list.deleteAtHead(); // Attempt delete from empty
        list.deleteAtTail(); // Attempt delete from empty
        list.deleteByValue(5); // Attempt delete from empty
        list.displayForward();

        System.out.println("\n--- Test deleting single node list ---");
        list.insertAtHead(77);
        list.displayForward();
        System.out.println("Deleting head (77):");
        list.deleteAtHead();
        list.displayForward();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());

        list.insertAtTail(88);
        list.displayForward();
        System.out.println("Deleting tail (88):");
        list.deleteAtTail();
        list.displayForward();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());
        
        list.insertAtHead(99);
        list.displayForward();
        System.out.println("Deleting by value (99):");
        list.deleteByValue(99);
        list.displayForward();
        System.out.println("Is list empty? " + list.isEmpty() + ", Size: " + list.getSize());
    }
}
