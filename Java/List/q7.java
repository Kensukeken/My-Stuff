/*
 * Question number 7.
 */

// A single box in our list is called a "Node"
class LNode {
    int val; // The number inside the box
    LNode next; // The next box in the list
    
    // We make a new box and put a given number inside it
    LNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedList {
    LNode head;

    // Our list starts empty.
    public LinkedList() {
        this.head = null;
    }

    // We can add a new number to the beginning of our list
    public void add(int data) {
        LNode newNode = new LNode(data);
        newNode.next = head;
        head = newNode;
    }

    // Let's create a new list that's a copy of our original list
    public LinkedList clone() {
        LinkedList newList = new LinkedList();
        LNode curr = head;
        
        // Go through each box in the original list
        while (curr != null) {
            newList.add(curr.val);
            curr = curr.next;
        }
        return newList;
    }

    // Convert our list into a string so we can print it
    public String toString() {
        String result = "";
        LNode curr = head;
        
        while (curr != null) {
            result += curr.val + " ";
            curr = curr.next;
        }
        return result;
    }

    // Let's test our list by adding numbers and printing it.
    public static void main(String[] args) {
        LinkedList list = new LinkedList(); // We're creating a new linked list named "list"
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("Original List: " + list);
        
        LinkedList clonedList = list.clone(); // Same thing goes in line 60 but different name called "clonedList"
        System.out.println("Cloned List: " + clonedList);
    }
}
