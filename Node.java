
//Node class that has previous, next, and data
//It initializes data members and 
//creates toString for the Node 
//DigitList uses the Node class to create the list
public class Node {
  protected Node prev; //previous Node
  protected Node next; //next Node
  protected int data; //the data of the Node
 
  //default constructor 
  public Node()
  {
	  this.data = 0;
	  this.prev = null;
	  this.next = null;
  }
  // Constructor initializes data members.
  // Target Complexity: O(1)
  public Node(int data, Node prev, Node next)
  {
	  this.data = data;
	  this.prev = prev;
	  this.next = next;
  }
 
  // Create a pretty representation of the Node
  // Format: [data]. Example: [3]
  // Target Complexity: O(1)
  public String toString()
  {
	  return "[" + data + "]";
  }
} 