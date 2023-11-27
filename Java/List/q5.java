/* 
 * Question number 6
 */

// This is a class for a node in a linked list. Each node has a value and a link to the next node.
class LNode {
    int val;
    LNode next;
    LNode(int x) {
        val = x; 
        next = null;
    }
}

public class Main {
    public static void main(String[] args) {
        // Created nodes with values 1, 2, and 3. These lines create new nodes with the given values.
        LNode node1 = new LNode(1);
        LNode node2 = new LNode(2);
        LNode node3 = new LNode(3);

        // Connecting nodes to form a list: 1 -> 2 -> 3. These lines connect the nodes to form a linked list.
        node1.next = node2;
        node2.next = node3;

        /* Print the original list: 1 2 3
         * This line prints the original list.
         */
        printList(node1);

        /* Reverse the list
         * This line reverses the list.
         */ 
        LNode reversedHead = reverse(node1);

        /* Print the reversed list: 3 2 1
         * This line prints the reversed list.
         */
        printList(reversedHead);
    }

    /* Method to reverse a linked list
     *This method takes the head of a linked list and reverses the list.
     */
    public static LNode reverse(LNode head) {
        LNode prev = null; // This will be the previous node.
        LNode curr = head; // This will be the current node.
        // This loop goes through each node in the list.
        while (curr != null) {
            LNode nextTemp = curr.next; // This is the next node.
            curr.next = prev; // The current node's next is set to the previous node.
            prev = curr; // The current node becomes the previous node.
            curr = nextTemp; // Move to the next node.
        }
        return prev; // Return the head of the reversed list.
    }

    /* Method to print the elements of a linked list
     * This method takes the head of a linked list and prints the value of each node.
     */
    public static void printList(LNode node) {
        // This loop goes through each node in the list.
        while (node != null) {
            System.out.print(node.val + " "); // Print the value of the current node.
            node = node.next; // Move to the next node.
        }
        System.out.println(); // Print a newline at the end.
    }
}
