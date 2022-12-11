// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

/**
 * This class prompts the user for a set of coordinates, and then 
 * converts them from polar to cartesian or vice-versa.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Paul Holden
 */
public class PointCPTest
{
  //Class methods *****************************************************

  /**
   * This method is responsible for the creation of the PointCP4
   * object.  This can be done in two ways; the first, by using the
   * command line and running the program using <code> java 
   * PointCP4Test &lt;coordtype (c/p)&gt; &lt;X/RHO&gt; &lt;Y/THETA&gt;
   * </code> and the second by getting the program to prompt the user.
   * If the user does not enter a valid sequence at the command line,
   * the program will prompte him or her.
   *
   * @param args[0] The coordinate type.  P for polar and C for
   *                cartesian.
   * @param args[1] The value of X or RHO.
   * @param args[2] The value of Y or THETA.
   */
  public static void main(String[] args)
  {
    PointCP4 point;
    PointCP4 point2;

    System.out.println("Cartesian-Polar Coordinates Conversion Program");

    // Check if the user input coordinates from the command line
    // If he did, create the PointCP4 object from these arguments.
    // If he did not, prompt the user for them.
    try
    {
      point = new PointCP4(args[0].toUpperCase().charAt(0), 
        Double.valueOf(args[1]).doubleValue(), 
        Double.valueOf(args[2]).doubleValue());

      point2 = new PointCP4(args[0].toUpperCase().charAt(0),
              Double.valueOf(args[1]).doubleValue(),
              Double.valueOf(args[2]).doubleValue());
    }
    catch(Exception e)
    {
      // If we arrive here, it is because either there were no
      // command line arguments, or they were invalid
      if(args.length != 0)
        System.out.println("Invalid arguments on command line");

      try
      {
        point = getInput();
        point2 = getInput();
      }
      catch(IOException ex)
      {
        System.out.println("Error getting input. Ending program.");
        return;
      }
    }
    System.out.println("\nYou entered:\n" + point);
    System.out.println("\nAfter asking to store as Cartesian:\n" + point.convertToCartesian());
    System.out.println("\nAfter asking to store as Polar:\n" + point.convertToPolar());
    System.out.println(point.getX());
    System.out.println(point.getY());
    System.out.println(point.getRho());
    System.out.println(point.getTheta());
    System.out.println("-------------");


    System.out.println("\nYou entered:\n" + point2);
    System.out.println("\nAfter asking to store as Cartesian:\n" + point2.convertToCartesian());
    System.out.println("\nAfter asking to store as Polar:\n" + point2.convertToPolar());
    System.out.println(point2.getX());
    System.out.println(point2.getY());
    System.out.println(point2.getRho());
    System.out.println(point2.getTheta());
    System.out.println("-------------");

  }

  /**
   * This method obtains input from the user and verifies that
   * it is valid.  When the input is valid, it returns a PointCP4
   * object.
   *
   * @return A PointCP4 constructed using information obtained 
   *         from the user.
   * @throws IOException If there is an error getting input from
   *         the user.
   */
  private static PointCP4 getInput() throws IOException
  {
    byte[] buffer = new byte[1024];  //Buffer to hold byte input
    boolean isOK = false;  // Flag set if input correct
    String theInput = "";  // Input information
    
    //Information to be passed to the constructor
    char coordType = 'A'; // Temporary default, to be set to P or C
    double a = 0.0;
    double b = 0.0;

    // Allow the user to enter the three different arguments
    for (int i = 0; i < 3; i++)
    {
      while (!(isOK))
      {
        isOK = true;  //flag set to true assuming input will be valid
          
        // Prompt the user
        if (i == 0) // First argument - type of coordinates
        {
          System.out.print("Enter the type of Coordinates you "
            + "are inputting ((C)artesian / (P)olar): ");
        }
        else // Second and third arguments
        {
          System.out.print("Enter the value of " 
            + (coordType == 'C' 
              ? (i == 1 ? "X " : "Y ")
              : (i == 1 ? "Rho " : "Theta ")) 
            + "using a decimal point(.): ");
        }

        // Get the user's input      
       
        // Initialize the buffer before we read the input
        for(int k=0; k<1024; k++)
        	buffer[k] = '\u0020';        
             
        System.in.read(buffer);
        theInput = new String(buffer).trim();
        
        // Verify the user's input
        try
        {
          if (i == 0) // First argument -- type of coordinates
          {
            if (!((theInput.toUpperCase().charAt(0) == 'C') 
              || (theInput.toUpperCase().charAt(0) == 'P')))
            {
              //Invalid input, reset flag so user is prompted again
              isOK = false;
            }
            else
            {
              coordType = theInput.toUpperCase().charAt(0);
            }
          }
          else  // Second and third arguments
          {
            //Convert the input to double values
            if (i == 1)
              a = Double.valueOf(theInput).doubleValue();
            else
              b = Double.valueOf(theInput).doubleValue();
          }
        }
        catch(Exception e)
        {
        	System.out.println("Incorrect input");
        	isOK = false;  //Reset flag as so not to end while loop
        }
      }

      //Reset flag so while loop will prompt for other arguments
      isOK = false;
    }
    //Return a new PointCP4 object
    return (new PointCP4(coordType, a, b));
  }
}
