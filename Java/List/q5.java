/* 
 * Question number 5
 */

// This is a class for a node in a linked list. Each node has a value and a link to the next node.
class LNode {
    int val; // This is the value of the node.
    LNode next; // This is the next node in the list.

    // This is a constructor. It creates a new node with a given value.
    LNode(int x) {
        val = x; // Set the value of the node.
        next = null; // Set the next node to be null.
    }
}

public class Main {
    public static void main(String[] args) {
    /* Created nodes 
    * These lines create new nodes with the given values.
    */
    LNode node1 = new LNode(1);
    LNode node2 = new LNode(2);
    LNode node3 = new LNode(3);
    LNode node4 = new LNode(1);
    LNode node5 = new LNode(1);
    LNode node6 = new LNode(2);
    LNode node7 = new LNode(2);

    /*Connected nodes
    * These lines connect the nodes to form a linked list.
    */
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    node5.next = node6;
    node6.next = node7;

    /*Print original list
     * This line prints the original list.
     */
    printList(node1);

    /* Remove duplicates
     * This line removes duplicate nodes from the list.
     */
    removeDuplicates(node1);

    /* Print list after removing duplicates
     * This line prints the list after removing duplicates.
     */
    printList(node1);
}

    // This method removes duplicate nodes from the list.
    public static void removeDuplicates(LNode head) {
        if (head == null){
          return;  
    } 

    // This loop goes through each node in the list.
    LNode curr = head;
    while (curr != null) {
        LNode runner = curr;
        // This inner loop checks the rest of the list for duplicates of the current node.
        while (runner.next != null) {
            // If a duplicate is found, it is removed from the list.
            if (runner.next.val == curr.val) {
                runner.next = runner.next.next;
            } else {
                runner = runner.next;
            }
        }
        curr = curr.next;
    }
}
// This method prints the values of the nodes in the list.
public static void printList(LNode node) {
    while (node != null) {
        System.out.print(node.val + " "); // Print the value of the current node.
        node = node.next; // Move to the next node.
        }
        System.out.println(); // Print a newline at the end.
    }
}
