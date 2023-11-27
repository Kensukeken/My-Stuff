/*
 * Question number 3
*/
class LNode {
  int val; 
  LNode next; 
  LNode prev; 
  LNode(int x) {val = x;}  
}

// This is our DoubleLinkedList class. It has a head which points to the start of the list.
class DoubleLinkedList{
  LNode head; // The first box in our list

  // This function deletes a box with a specific number
  public void delete(int val){
    LNode curr = head; // Start at the first box
    while(curr != null){ // While there are still boxes left
      if(curr.val == val){ // If we found the box with the number
        if(curr.prev != null){ // If there is a box before this one
          curr.prev.next = curr.next; // Set the next box of the previous box to the next box of the current box
        } else{
          head = curr.next; // If there is no previous box, set the head to the next box
        }
        if(curr.next != null){ // If there is a box after this one
          curr.next.prev = curr.prev; // Set the previous box of the next box to the previous box of the current box
        }
        return; // We found and deleted the box, so we can stop
      }
      curr = curr.next; // Move to the next box
    }   
  }

  // This function deletes a specific box
  public void delete(LNode node){
    if(node.prev != null){ // If there is a box before this one
      node.prev.next = node.next; // Set the next box of the previous box to the next box of this box
    } else{
      head = node.next; // If there is no previous box, set the head to the next box
    }
    if(node.next != null){ // If there is a box after this one
      node.next.prev= node.prev; // Set the previous box of the next box to the previous box of this box
    }
  }

  // This function deletes the box at a specific position
  public void deleteAt(int postion){
    LNode curr = head; // Start at the first box
    for(int i = 0; i < postion && curr != null; i++){ // Move to the box at the given position
      curr = curr.next;
    } 
    if(curr != null){ // If there is a box at the given position
      delete(curr); // Delete it
    }
  }

  // This function prints all the boxes in our list
  public void printList(){
    LNode curr = head; // Start at the first box
    while(curr != null){ // While there are still boxes left
      System.out.println(curr.val + ""); // Print the number in the current box
      curr = curr.next; // Move to the next box
    }
    System.out.println(); // Print a newline at the end
  }
}

// This is our main class where we test our DoubleLinkedList
public class Main{
    public static void main (String args []){
    DoubleLinkedList dll = new  DoubleLinkedList(); // We're creating a new double linked list named "dll"
    dll.head = new LNode(1); // The first box in our list has the number 1
    dll.head.next = new LNode(2); // The second box in our list has the number 2
    dll.head.next.prev = dll.head; // The second box's previous box is the first box
    dll.head.next.next = new LNode(3); // The third box in our list has the number 3
    dll.head.next.next.prev = dll.head.next; // The third box's previous box is the second box
    dll.head.next.next.next = new LNode(4); // The fourth box in our list has the number 4
    dll.head.next.next.next.prev = dll.head.next.next; // The fourth box's previous box is the third box
    dll.printList(); // Print the list
    dll.delete(2); // Delete the box with the number 2
    dll.printList(); // Print the list
    dll.deleteAt(1); // Delete the box at position 1
    dll.printList(); // Print the list
  }
}
