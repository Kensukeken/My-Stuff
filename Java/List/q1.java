/*
 * Hia Al Saleh 
 * Question number 1 
*/
// This is our Node class. It has data (the value of the node) and next (which points to the next node in the list).
class LNode {
  int val; // The number inside the box
  LNode next; // The next box in the list
  LNode prev; // The previous box in the list
  LNode(int x) {val = x;}  // We make a new box and put a given number inside it
}

// This is our Stack class. It has a top which points to the top of the stack.
class Stack {
  LNode top; // The top box in our stack

  // This function adds a new box with a specific number to the top of our stack
  public void push(int val) {
    LNode newNode = new LNode(val); // Create a new box with the given number
    newNode.next = top; // Set the next box of our new box to the old top box
    top = newNode; // Our new box is now the top box
  }

  // This function removes the top box from our stack and returns its number
  public int pop() {
    if (top == null) { // If there is no top box
      throw new RuntimeException(); // Throw an error
    }
    int val = top.val; // Get the number from the top box
    top = top.next; // The next box is now the top box
    return val; // Return the number from the top box
  }

  // This function prints all the boxes in our stack
  public void print(){
    LNode current = top; // Start at the top box
    while(current != null){ // While there are still boxes left
      System.out.print(current.val); // Print the number in the current box
      current = current.next; // Move to the next box
    }
    System.out.println(); // Print a newline at the end
  }
}

// This is our main class where we test our Stack
public class Main{
    public static void main(String[] args) {
    Stack s = new Stack(); // We're creating a new stack named "s"
    s.push(1); // Add the number 1 to the stack
    s.push(2); // Add the number 2 to the stack
    s.push(3); // Add the number 3 to the stack
    s.push(4); // Add the number 4 to the stack
    s.print(); // Print the stack
    }
}
