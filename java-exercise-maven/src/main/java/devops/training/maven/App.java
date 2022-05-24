package devops.training.maven;

import java.util.Scanner;
import devops.training.draw.*;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static void printHelp(String[] options)
	{
		for(String option : options)
		{
			System.out.println(option);
		}
	}
	
	public static void getUserInput()
	{
		System.out.print("\nEnter command: ");
	}
	
	public static void main(String[] args)
	{
		String[] help = {"Command         Description",
				         "C w h           Should create a new canvas of width w and height h.",
				         "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported.",
				         "                Horizontal and vertical lines will be drawn using the 'x' character.",
				         "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2",
				         "                Horizontal and vertical lines will be drawn using the 'x' character",
				         "B x y c         Should fill the entire area connected to (x,y) with \"colour\" c.",
				         "                The behavior of this is the same as that of the \"bucket fill\" tool in paint programs",
				         "Q               Should quit the program."
		                };
		
		printHelp(help);
		parseCmd par = new parseCmd();
		canvas obj = new canvas();
		try (Scanner reader = new Scanner(System.in)) 
		{
			while(true)
			{
			    getUserInput(); 
			    String cmd = reader.nextLine();
			    if(par.handleCommand(cmd, obj) == false)
			    {
			        printHelp(help);
		        }
			}
		}
	}
}
