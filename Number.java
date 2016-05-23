
//The class Number uses a DigitList to represent a number
//It adds, subtract and multiply Number type
//it implements Comparable <number> and has a
//compareTo method and equal method 
import java.util.ListIterator;

public class Number implements Comparable<Number> {
  
  protected DigitList number; //DigitList number
  protected int digitCount = 0; //number of digits in the list
  protected int decimalPlaces = 0; //number of digits after decimal place
  protected boolean negative = false; // sign (positive or negative)
 
  // Default Constructor 
  public Number()
  {
	   //initialize to zeros
	   number = new DigitList();
	   digitCount = 0;
	   decimalPlaces =0;
	   negative = false;
  }
  // Constructor that takes a String representation of a number 
  public Number(String str) throws BadNumberException
  {
	  //create a new DigitList
	  number = new DigitList();
	  digitCount = str.length();
	  decimalPlaces =0;
	  negative = false;
	  //Calls method accept()
	  accept(str);
  }
  
  // Builds a DigitList representation of the number represented by str 
  // after str has been validated and trimmed.   
  public void accept(String str) throws BadNumberException
  {
	  //validate number
	   validate(str);
	   
	   //convert the String to DigitList
	   int count =0;
	   for (int i = str.length()-1; i >= 0;i--)
	   {
		    char current = str.charAt(i);
		    if(current == '.')
		    {
		    	decimalPlaces = count;
		    }
		    else if(current == '-')
		    {
		    	negative = true;
		    }
		    else if(current == '+')
		    {
		    	//dont do anything 
		    }
		    else
		    {
			     number.addFirst(Integer.parseInt(current+"")); 
			     count++;
		    }
	   }
	   digitCount = count;
	  
	 
	   //trim the number
	   trim();
  }
 
  // Returns a Number that represents "this + n". 
  // Target Complexity: O(n)
  public Number add(Number n)
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	  int result = this.compareTo(n);
	  Number sum = new Number();
	  if (result > 0) //this > n
	  {
		  if (this.negative == false && n.negative == false)
		  {
			  return this.addAbsolute(n); //+
		  }
		  else if (this.negative == false && n.negative == true)
		  {
			  return this.subtractAbsolute(n); //+
		  }
		  else if (this.negative == true && n.negative == false)
		  {
			  sum = n.subtractAbsolute(this); //-
			  sum.negative = true; //change sign 
			  return sum;
		  }
		  else
		  {
			  sum = this.addAbsolute(n); //-
			  sum.negative = true; //change sign
			  return sum; 
		  }   
	  }
	  else if (result <= 0) // this < n
	  {
		  if (this.negative == false && n.negative == false)
		  {
			  return n.addAbsolute(this); //+
		  }
		  else if (this.negative == false && n.negative == true)
		  {
			  sum = n.subtractAbsolute(this); //-
			  sum.negative = true; //change the sign
			  return sum;
		  }
		  else if (this.negative == true && n.negative == false)
		  {
			  return n.subtractAbsolute(this); //+
		  }
		  else
		  {
			  sum = n.addAbsolute(this); //-
			  sum.negative = true; //change the sign
			  return sum;
		  } 
	  }
	  //return 0
	  Number num = new Number();
	  num.number.addFirst(0);
	  return num;
	  
  }
  //A method that subtract two numbers
  // Returns a Number that represents "this - n". 
  // Target Complexity: O(n)
  public Number subtract(Number n) 
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	  int result = this.compareTo(n);
	  Number difference = new Number();
	  if (result > 0) //this > n
	  {
		  if (this.negative == false && n.negative == false)
		  {
			  return this.subtractAbsolute(n);// +
		  }
		  else if (this.negative == false && n.negative == true)
		  {
			  return this.addAbsolute(n);//+
		  }
		  else if (this.negative == true && n.negative == false)
		  {
			  difference = this.addAbsolute(n); // -
			  difference.negative = true; //change sign
			  return difference;
		  }
		  else
		  {
			  return n.subtractAbsolute(this); //+
			 
		  }   
	  }
	  else if (result < 0) // this < n
	  {
		  if (this.negative == false && n.negative == false)
		  {
			  difference = n.subtractAbsolute(this); //-
			  difference.negative = true; //change sign
			  return difference;
		  }
		  else if (this.negative == false && n.negative == true)
		  {
			  return n.addAbsolute(this); //+
		  }
		  else if (this.negative == true && n.negative == false)
		  {
			  difference = n.addAbsolute(this); //-
			  difference.negative = true; //change sign
			  return difference;
		  }
		  else
		  {
			  difference = this.subtractAbsolute(n); //-
			  difference.negative = true; //change sign 
			  return difference;
		  } 
	  }
	  //if they are equal 
	  //returns 0
	  Number num = new Number();
	  num.number.addFirst(0);
	  return num;
  }
  // a method checks if the two numbers have the same sign
  public boolean sameSign (Number first, Number second)
  {
	  if(first.negative == second.negative)
	  {
		  return true;
	  }
	  else
		  return false;
  }
  //A method that multiplies two numbers
  // Returns a Number that represents "this * n". 
  // Target Complexity for multiplication of an n digit number and an m 
  // digit number: O(m x n)
  public Number multiply(Number n)
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	  Number product = new Number();
	  int nDigit;
	  product.number = new DigitList();
	  ListIterator<Integer> nItr = n.number.listIterator(0); 
	   //Iterate through digits of n
	   while (nItr.hasNext())
	   {
		   nDigit = nItr.next();
		   Number partialProduct = new Number();
		   partialProduct.number = new DigitList();
		   int thisDigit;
		   int carry = 0;
		   //iterate through digits of this
		   ListIterator<Integer> thisItr = this.number.listIterator(this.digitCount);
		   while (thisItr.hasPrevious())
		   {
			   thisDigit = thisItr.previous();
			   //add carry to thisDigit * nDigit
			   int newDigit = carry + thisDigit * nDigit;
			   carry = newDigit/10;
			   newDigit = newDigit % 10;
			   //add newDigit in front of number 
			   partialProduct.number.addFirst(newDigit);
			   partialProduct.digitCount++;
		   }
		   if(carry != 0)
		   {
			   //add carry to the front of the list
			   partialProduct.number.addFirst(carry);
			   partialProduct.digitCount++;
		   }
		   product.number.addLast(0);
		   product.digitCount++;
		   product = product.addAbsolute(partialProduct);
	   }
	  //set the decimal point
	  product.decimalPlaces = this.decimalPlaces + n.decimalPlaces;
	  //same sign +, different sign - 
	  if(!sameSign(this,n))
	  {
		  product.negative = true;
	  }
	  //trim the number
	  product.trim();
	  return product;
  }
 
  //Reverses the value of negative. 
  public void reverseSign()
  {
	   if(negative == false)
	   {
		   negative = true;
	   }
	   else
	   {
		   negative = false;
	   }
  }
 //A method that checks for equality of two numbers
  public boolean equals(Object rhs)
  {
	   if (this == rhs)
		   return true;
	   
	   if (!(rhs instanceof Number))
		   return false;
	   //cast to Number
	   Number other = (Number) rhs;
	   //use compareTo to determine equality 
	   int result = this.compareTo(other);
	   if (result == 0)
	   {
		   return true;
	   }
	   else
		   return false;
  }
 
  // Implements compareTo of Comparable
  //A method that compares two Numbers
  public int compareTo(Number n)
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	 int result = this.compareToAbsolute(n);
	 //take into consideration the negative values 
	 if(this.negative == true && n.negative == true)
	 {
		 return result * -1;
	 }
	 else if (this.negative == true && n.negative == false)
	 {
		 return -1;
	 }
	 else if (this.negative == false && n.negative == true)
	 {
		 return 1;
	 }
	 
	 
	 return result;
  }
 // A method that aligns two different numbers
 //used in the addAbsolute and subtractAbsolute methods
 public void alignNumber(Number first, Number second)
 {
	 //checks for decimal places
	 if(first.decimalPlaces != second.decimalPlaces)
	    {
		   //align
		   if (first.decimalPlaces > second.decimalPlaces)
		   {
			   //add 0 to n this.decimalPlace - n.decimalPlaces times 
			   int counter = first.decimalPlaces - second.decimalPlaces;
			   //loop that add zeros to the end of number
			   while (counter > 0)
			   {
				   second.number.addLast(0);
				   second.digitCount++;
				   second.decimalPlaces ++;
				   counter --;
			   }
		   }
		   else
		   {
			 //add 0 to this n.decimalPlace - this.decimalPlaces times 
			   int counter = second.decimalPlaces - first.decimalPlaces;
			 //loop that add zeros to the end of number
			   while (counter > 0)
			   {
				   first.number.addLast(0);
				   first.digitCount++;
				   first.decimalPlaces++;
				   counter --;
			   }
		   }
	    }
	   //checks for digit count
	   if (first.digitCount != second.digitCount)
	   {
		   if (first.digitCount > second.digitCount)
		   {
			   int counter = first.digitCount - second.digitCount;
			 //loop that add zeros to the front of number
			   while (counter > 0)
			   {
				   second.number.addFirst(0);
				   second.digitCount++;
				   counter--;
			   }
			   
		   }
		   else
		   {
			   int counter = second.digitCount - first.digitCount;
			 //loop that add zeros to the front of number
			   while (counter > 0)
			   {
				   first.number.addFirst(0);
				   first.digitCount++;
				   counter--;
			   }
		   }
	   }
 }
  //The next three methods perform operations disregarding the signs 
 
  //A method that adds two positive numbers
  // Target Complexity: O(n)
  protected Number addAbsolute(Number n)
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	  //if the two numbers need to be aligned 
	   if (this.decimalPlaces != n.decimalPlaces || 
			  this.digitCount != n.digitCount)
	   {
		  alignNumber(this,n);
	   }
	  
	   Number sum = new Number();
	   int carry = 0;
	   int thisDigit;
	   int nDigit;
	   sum.number = new DigitList();
	   
	   //create two iterators 
	   ListIterator<Integer> thisItr = this.number.listIterator(this.digitCount);
	   ListIterator<Integer> nItr = n.number.listIterator(n.digitCount);
	 
	   //Iterate through digit 
	   while (thisItr.hasPrevious() && nItr.hasPrevious())
	   {
		   //get the values
		   thisDigit = thisItr.previous();
		   nDigit = nItr.previous();
		   //add the values together with carry
		   int newDigit = nDigit +thisDigit + carry;
		   int theSum = newDigit %10;
		   //add theSum in front of the list
		   sum.number.addFirst(theSum);
		   sum.digitCount++;
		   carry = newDigit/10;
	   }
	  
	   //adjust decimal 
	   sum.decimalPlaces = this.decimalPlaces;
	   if (carry !=0)
	   {
		   //add carry to the front of the of number
		    sum.number.addFirst(carry);
		    sum.digitCount++;
	   }
	   //trim number
	   sum.trim();
	   return sum;
 }
  
  //A method that subtract two numbers
  // Target Complexity: O(n)
  protected Number subtractAbsolute(Number n)
  {
	  assert (this.digitCount >= 0 && n.digitCount >= 0 &&
			  this.decimalPlaces >= 0 && n.decimalPlaces >= 0&&
			  this.number.size >= 0 && n.number.size >= 0);
	  
	  //if the two numbers need to be aligned 
	  if (this.decimalPlaces != n.decimalPlaces || 
			  this.digitCount != n.digitCount)
	   {
		  alignNumber(this,n);
	   }
	   //create and initialize values 
	   Number difference = new Number();
	   int borrow = 0;
	   int thisDigit;
	   int nDigit;
	   difference.number = new DigitList();
	   
	   //create new iterators 
	   ListIterator<Integer> thisItr = this.number.listIterator(this.digitCount);
	   ListIterator<Integer> nItr = n.number.listIterator(n.digitCount);
	   
	   while (thisItr.hasPrevious() && nItr.hasPrevious())
	   {
		   //get the values
		   thisDigit = thisItr.previous();
		   nDigit = nItr.previous();
		   int newDigit = (thisDigit - nDigit) -borrow;
		   if (newDigit <0)
		   {
			   newDigit += 10;
			   borrow = 1;
		   }
		   else
		   {
			   borrow = 0;
		   }
		   //add to the front of the number
		   difference.number.addFirst(newDigit);
		   difference.digitCount++;
	   }
	   //adjust decimal 
	   difference.decimalPlaces = this.decimalPlaces;
	   //trim the number
	   difference.trim();
	   return difference;
  }
  //a method that compares two Number disregarding their sign
  // Target Complexity: O(n)
  protected int compareToAbsolute(Number n)
  {
	  if (this == n)
		   return 0;
	  //subtract the count from the decimal places
	  int thisCount = this.digitCount - this.decimalPlaces;
	  int nCount = n.digitCount - n.decimalPlaces;
	  //if the first one is larger than the second
	  if (thisCount > nCount)
	  {
		  return 1;
	  }
	  //if the first one is smaller than the second
	  else if (thisCount < nCount)
	  {
		  return -1;
	  }
	  //if both are at equal length 
	  else
	  {
		  //create new iterators to iterate digit by digit
		  ListIterator<Integer> thisItr = this.number.listIterator(0);
	 	  ListIterator<Integer> nItr = n.number.listIterator(0);
	 	  while (thisItr.hasNext() && nItr.hasNext())
	 	  {
	 		  int thisNode = thisItr.next();
	 		  int nNode = nItr.next();
	 		  //compare the digits one by one
	 		  if (thisNode > nNode)
	 		  {
	 			  return 1;
	 		  }
	 		  else if (thisNode < nNode)
	 		  {
	 			  return -1;
	 		  }
	 	  }
	  }
	  // if they are equal
	  return 0;
  }
 
  // Returns a String representation of the Number.
  public String toString()
  {
	   String answer ="";
	   int i =0;
	   int currentDigitCount = digitCount;
	   boolean control = true;
	   //get the first node
	   Node current = number.head.next;
	   while (current != number.tail)
	   {
		   // a loop that add zeros in front of the number
		    while (currentDigitCount - decimalPlaces < 0)
		    {
		    	answer = answer + "0";
		    	currentDigitCount++;
		    }
		    //add the decimal point
		    if (digitCount - decimalPlaces == i)
		    {
			     answer = answer + ".";
			     control = false;
	        }
		    //add the data to the string
		    answer = answer + (current.data);
		    //increment the current
		    current = current.next;
		    i++;
	   }
	   //add decimal places
	   if (currentDigitCount - decimalPlaces == 0 && control && !answer.equals(""))
	   {
		     answer = "." + answer;
       }
	   //if negative then add - to the front
	   if (negative)
	   {
		   answer = "-" + answer;
	   }
	   //if answer is an empty string then assign 0 
	   if (answer.equals(""))
	   {
		   answer = "0";
	   }
	   return answer;
  }
 
  // Removes all extra leading 0s which precede the decimal point 
  // and all extra trailing 0s which follow the decimal point. 
  public void trim()
  {
	   int control = 0;
	   //trim the leading 0s
	   while (control == 0)
	   {
		    //create an iterator 
		    ListIterator<Integer> itr = number.listIterator(0);
		    //if first node is 0
		    if (itr.hasNext() && itr.next() == 0)
		    {
		    	 //remove the zero
			     number.removeFirst();
			     digitCount--; 
		    }
		    else
		    {
		    	control = 1;
		    }
	   }
	   
	   control = 0;
	   //trim the trailing 0s
	   while (control == 0)
	   {
		    //create a new iterator that iterate backward 
		    ListIterator<Integer> itr = number.listIterator(digitCount);
		    //if 0 and has decimal places, then remove the last 0s
		    if (itr.hasPrevious() && itr.previous() == 0 && decimalPlaces != 0)
		    {
		    	 //remove the 0
			     number.removeLast();
			     //adjust decimal place
			     decimalPlaces --;
			     digitCount --;
		    }
		    else
		    {
		    	control = 1;
		    }
	   }   
  }
  
  //a method that checks each digit of a number
  public boolean isValid(String n)
  {
	  return (n.equals("0") || n.equals("1") || n.equals("2")
			 || n.equals("3") || n.equals("4") || n.equals("5")
			 ||n.equals("6") || n.equals("7") || n.equals("8")
			 ||n.equals("9") || n.equals(".")); 
}
  // Ensures that the string passed to accept() or to the constructor 
  // represents a valid number.
  // Throws BadNumberException if str is not valid.
  
  public void validate (String str) throws BadNumberException //throws BadNumberException 
  {
	    //check if the string is empty 
	  	if (str.length() == 0)
	  	{
	  		throw new BadNumberException("Error! bad number");
	  	}
		//check the first index of str to determine if it is negative 
	   	String first = str.charAt(0)+"";
	   	if (!isValid(first)&&!first.equals("-"))
	   	{
	   		throw new BadNumberException("Error! bad number");
	   	}
	   //check decimal
	   	int decimal = 0;
	   	
	   //check each character for valid number
	   for(int i =1; i <str.length();i++)
	   {
		   //check to make sure there is at most one decimal point
		    String current = str.charAt(i)+"";
		    if (current.equals("."))
		    {
		    	decimal++;
		    }
		    //throw exception if number is not a digit
		    if(!isValid(current) || decimal > 1)
		    {
		    	throw new BadNumberException("Error! bad number");
		    }
	   }
  }
     
  
}