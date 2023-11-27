/*
 * Question number 4
 */

// This is our LinkedList class. It has a head which points to the start of the list.
class LinkedList {
    Node head;  // head of list

    /* This is our Node class. 
    It has data (the value of the node) and next (which points to the next node in the list). 
    */
    class Node {
        int data;
        Node next;
        // Constructor to create a new node with a given value
        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Function to insert a new node in sorted order.
    public void sortedInsert(Node newNode) {
        Node curr;

        // Special case for head node
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            head = newNode;
        } else {
            // Locate the node before point of insertion
            curr = head;
            while (curr.next != null && curr.next.data < newNode.data)
                curr = curr.next;

            newNode.next = curr.next;
            curr.next = newNode;
        }
    }

    // Function to create a new node with given data
    Node newNode(int data) {
        Node x = new Node(data);
        return x;
    }

    // Function to print the linked list
   public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}

// This is our main class where we test our LinkedList
public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList(); // We're creating a new linked list named "list"
        // Creating nodes
        LinkedList.Node node1 = list.new Node(1); 
        LinkedList.Node node2 = list.new Node(2); 
        LinkedList.Node node3 = list.new Node(3); 
        // Inserting nodes in sorted order
        list.sortedInsert(node1); 
        list.sortedInsert(node3); 
        list.sortedInsert(node2); 
        // Printing the linked list
        list.printList(); // Print the list
    }
}
