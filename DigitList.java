
//A class that has a linked list of Nodes
//The list contains a dummy head and tail Node
//It takes care of the DigitList of a number
//The class add and remove nodes and 
//has a DigitList iterator 

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
public class DigitList {
  protected Node head;  // reference to dummy node at the front
  protected Node tail;  // reference to dummy node at the end
  protected int size;   // number of nodes
  protected int modCount; // number of modifications made
 
  // Workhorse constructor. Initialize variables: head and tail to dummy 
  // nodes, size to 0, modCount to 1. Initially, head.next references  
  // the tail node; tail.prev references the head node. An empty list
  // (having size 0) has only the two dummy nodes in it.
  DigitList()
  {
	   head = new Node();
	   tail = new Node();
	   head.next = tail;
	   tail.prev = head;
	   size = 0;
	   modCount =1;
  }
  // Add digit x to the front of the list. 
  // Target Complexity: O(1)
  public void addFirst(int x)
  {
	   Node newNode = new Node(x,head, head.next);
	   head.next.prev = newNode;
	   head.next = newNode;
	   size++;
	   modCount ++;
  }
 
  // Returns the high-order digit (at the front of the list). 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public int getFirst()
  {
	   if (size == 0)
	   {
	    throw new NoSuchElementException("Error! the list is empty");
	   }
	   return head.next.data;
  }
 
  // Removes the high-order digit at the front of the list. 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public void removeFirst()
  {
	   if (size == 0)
	   {
	    throw new NoSuchElementException("Error! the list is empty");
	   }
	   Node nextNode = head.next.next;
	   head.next = nextNode;
	   nextNode.prev = head;
	   size --;
	   modCount ++;
  }
 
  // Add digit x to the end of the list. 
  // Target Complexity: O(1)
  public void addLast(int x)
  {
	   Node prevNode = tail.prev;
	   //add at the tail
	   Node newNode = new Node(x,prevNode,tail);
	   prevNode.next = newNode;
	   tail.prev = newNode;
	   size++;
	   modCount++;

  }
 
  // Returns the low-order digit (at the end of the list). 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public int getLast()
  {
	   if (size == 0)
	   {
	    throw new NoSuchElementException("Error! the list is empty");
	   }
	   return tail.prev.data; 
  }
 
  // Removes the low-order digit at the end of the list. 
  // Throws NoSuchElementException if the list is empty
  // Target Complexity: O(1)
  public void removeLast()
  {
	   if (size == 0)
	   {
	    throw new NoSuchElementException("Error! the list is empty");
	   }
	   Node prevNode = tail.prev.prev;
	   prevNode.next = tail;
	   tail.prev = prevNode;
	   size--;
	   modCount++;
  }
 
  // Returns a pretty representation of the nodes in the list.
  // (Note that a DigitList has no notion of a decimal point.)
  // Example: [3][1][4][1][6]
  // O(number of digits)
  public String toString()
  {
	   String answer = "";
	   Node current = head.next;
	   while (current != tail)
	   {
	    answer = answer + "[" + current.data + "]";
	    current = current.next;
	   }
	   return answer; 
  }
 
  // Returns the current size of the list
  // Target Complexity: O(1)
  public int size()
  {
	  return size;
  }
 
  // Obtains a ListIterator object used to traverse the list
  // bidirectionally.
  // Returns an iterator positioned prior to the first element
  public ListIterator<Integer> listIterator( )
  {
	  return new DigitListIterator( 0 );
  }
 
  // Obtains a ListIterator object used to traverse the list
  // bidirectionally.
  // Returns an iterator positioned prior to the requested element.
  // Parameter idx is the index to start the iterator. 
  // Throws IndexOutOfBoundsException if idx is not between 0 
  // and size(), inclusive.
  public ListIterator<Integer> listIterator( int idx )
  {
	   if (idx < 0 || idx > size())
	   {
	    throw new IndexOutOfBoundsException("Error! the index is out of limit");
	   }
	    return new DigitListIterator( idx );
  }
 
// This class implements the ListIterator interface for the
// DigitList.  It maintains a notion of a current position and an
// implicit reference to the DigitList through the syntax
// DigitList.this.  
public class DigitListIterator implements ListIterator<Integer>{
	
    // Current node
    protected Node current; 
    // Necessary for implementing previous()
    protected boolean lastMoveWasPrev = false;    
    // How many modifications iterator expects
    protected int expectedModCount = modCount;      
    
    // Construct an iterator
    public DigitListIterator( int idx )
	    {
	     if (idx < 0 || idx > size())
	     {
	       throw new IndexOutOfBoundsException("Error! the index is out of limit");
	     }
	     if (idx == 0)
	     {
	    	 current = head;
	     }
	     
	     else
	     {
		      current = head.next;
		      int i =0;
		      //loop until the idx = index of node in the list
		      while (i != idx -1)
		      {
		       current = current.next;
		       i++;
		      }
	     }
    }
    
    // Returns true if this list iterator has more elements when 
    // traversing the list in the forward direction. (In other words, 
    // returns true if next() would return an element rather than 
    // throwing an exception.)
    public boolean hasNext( )
    {
    	if (expectedModCount != modCount)
    	{
    		throw new ConcurrentModificationException("Error! the list has been modified");
    	}
    	return (current.next != tail);
    }
    
    // Returns the next element in the list and advances the cursor
    // position.
    // Throws: NoSuchElementException if the iteration has no next 
    // element.
    public Integer next( )
    {
    	if (expectedModCount != modCount)
    	{
    		throw new ConcurrentModificationException("Error! the list has been modified");
    	}
    	if (hasNext())
     	{
        	lastMoveWasPrev = false;
    		current = current.next;
    		return current.data;
     	}
    	else
    		return null;
    }
    
    // Returns true if this list iterator has more elements when 
    // traversing the list in the reverse direction.     
    public boolean hasPrevious( )
    {
    	if (expectedModCount != modCount)
    	{
    		throw new ConcurrentModificationException("Error! the list has been modified");
    	}
    
    	return (current != head); 
    }
     
    // Returns the previous element in the list and moves the cursor 
    // position backwards. This method may be called repeatedly to 
    // iterate through the list backwards, or intermixed with calls to 
    // next() to go back and forth. (Note that alternating calls to 
    // next and previous will return the same element repeatedly.)
    // Throws: NoSuchElementException if the iteration has no next 
    // element.
    public Integer previous( )
    {
    	if (expectedModCount != modCount)
    	{
    		throw new ConcurrentModificationException("Error! the list has been modified");
    	}
    	if (!hasPrevious())
    	{
    		throw new NoSuchElementException("Error! the element does not exist");
    	}
    	lastMoveWasPrev = true;
    	int data = current.data;
    	current = current.prev;
    	return data; 
    }
    
    // The following methods may be optionally implemented but will
    // not be tested and will not garner any additional credit.
    public int nextIndex()
    {
      throw new RuntimeException();
    }
    // OPTIONAL: Return the integer index associated with the element
    // that would be returned by previous()
    public int previousIndex()
    {
      throw new RuntimeException();
    }
    // OPTIONAL: Inserts the specified element into the list. 
    public void add(Integer x)
    {
      throw new RuntimeException();
    }
 
    // The following operations are part of the ListIterator interface
    // but are not supported by DigitLists or their iterators. Both
    // will throw UnsupportedOperationException exceptions if invoked.
    public void set(Integer x)
    {
      throw new UnsupportedOperationException();
    }
    public void remove( ) 
    {
      throw new UnsupportedOperationException();
    }
  }
}


