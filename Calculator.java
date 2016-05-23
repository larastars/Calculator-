//A class that has the main method 
//It represents the current value of calculator
import java.util.Scanner;

public class Calculator {
	
	public static void main(String [] args) throws BadNumberException
	{
		Scanner scan = new Scanner(System.in);
		String value = "";
		Number num = new Number();
		
		System.out.println("---------------------Menu---------------------");
		String answer = "";
		do
		{
			System.out.println();
			System.out.println("Enter E for a Value");
			System.out.println("Enter A for Add");
			System.out.println("Enter S for Subtract");
			System.out.println("Enter M for Multiply");
			System.out.println("Enter R for Reverse Sign");
			System.out.println("Enter C for Clear");
			System.out.println("Enter Q to quit");
			System.out.println("------------------------------------------");
			answer = scan.nextLine();
			if (answer.equalsIgnoreCase("e"))
			{
				int control = 0;
				do
				{
					try
					{
						System.out.println("Enter a value: ");
						value = scan.nextLine();
						num = new Number(value);
						control = 0;
					}
					catch (BadNumberException e)
					{
						e.printStackTrace();
						control =1;
					}
				}while (control != 0);
				
				System.out.println("value = " + num);
			}
			else if (answer.equalsIgnoreCase("a"))
			{
				int control =0;
				do
				{
					try
					{
						System.out.println("Enter a value to add: ");
						String addition = scan.nextLine();
						num = num.add(new Number (addition));
						control = 0;
					}
					catch (BadNumberException e)
					{
						e.printStackTrace();
						control =1;
					}
				}while (control != 0);
				 System.out.println("value = " + num);
			}
			else if (answer.equalsIgnoreCase("s"))
			{
				int control =0;
				do
				{
					try
					{
						 System.out.println("Enter a value to subtract: ");
						 String subtraction = scan.nextLine();
						 num = num.subtract(new Number (subtraction));
						 control = 0;
					}
					catch (BadNumberException e)
					{
						e.printStackTrace();
						control =1;
					}
				}while (control != 0);
			    System.out.println("value = " + num);
	
			}
			else if (answer.equalsIgnoreCase("m"))
			{
				int control =0;
				do
				{
					try
					{
					     System.out.println("Enter a value to multiply: ");
						 String multiplication = scan.nextLine();
						 num = num.multiply(new Number (multiplication));
						 control = 0;
					}
					catch (BadNumberException e)
					{
						e.printStackTrace();
						control =1;
					}
				}while (control != 0);
				 System.out.println("value = " + num);
			}
			else if (answer.equalsIgnoreCase("r"))
			{
				 num.reverseSign();
				 System.out.println("value = " + num);
			}
			else if (answer.equalsIgnoreCase("c"))
			{
				 num = new Number();
				 System.out.println("value = " + num);
			}
			else
			{
				System.out.println("Error! ");
			}
			
		}while (!answer.equalsIgnoreCase("q"));
		
		
		
	}

}
