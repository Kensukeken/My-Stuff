/*
 * Question number 2 
*/

class LNode{
  int val; 
  LNode next; 
  LNode prev; 
  LNode(int x){val = x;} 
}

// This is our Queue class. It has a head which points to the start of the list and a tail which points to the end of the list.
class Queue{
  LNode head; // The first box in our list
  LNode tail; // The last box in our list

  // This function adds a new box with a specific number to the end of our list
  public void enqueue(int val){
    LNode newNode = new LNode(val); // Create a new box with the given number
    if(tail != null){ // If there is a last box
      tail.next= newNode; // Set the next box of the last box to our new box
      newNode.prev = tail; // Set the previous box of our new box to the last box
    }
    tail = newNode; // Our new box is now the last box
    if(head == null){ // If there is no first box
      head = newNode; // Our new box is now the first box
    }
  }

  // This function removes the first box from our list and returns its number
  public int dequeue(){
    if(head == null){ // If there is no first box
      throw new RuntimeException(); // Throw an error
    }
    int val = head.val; // Get the number from the first box
    head = head.next; // The next box is now the first box
    if(head != null){ // If there is a first box
      head.prev = null; // The first box has no previous box
    } else{
      tail = null; // If there is no first box, there is also no last box
    }
    return val; // Return the number from the first box
  }

  // This function converts our list into a string so we can print it
  public String toString() {
    StringBuilder sb = new StringBuilder(); // Start with an empty string
    sb.append("{"); // Add an opening brace to our string
    LNode curr = head; // Start at the first box
    while (curr != null) { // While there are still boxes left
      sb.append(curr.val); // Add the number from the current box to our string
      if (curr.next != null) { // If there is a next box
        sb.append(", "); // Add a comma and a space to our string
      }
      curr = curr.next; // Move to the next box
    }
    sb.append("}"); // Add a closing brace to our string
    return sb.toString(); // Return our string
  }
}

// This is our main class where we test our Queue
public class Main{   
  public static void main (String [] args){
    Queue queue = new Queue(); // We're creating a new queue
    queue.enqueue(10); 
    queue.enqueue(20); 
    queue.enqueue(30); 
    System.out.println(queue); // Print the queue

    int val = queue.dequeue(); // Remove the first number from the queue and get it
    System.out.println(val); // Print the number
  }
}
