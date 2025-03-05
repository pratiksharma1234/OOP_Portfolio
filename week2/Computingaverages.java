package week2;

import java.util.Scanner;
public class Computingaverages
{
    public static void main(String[] args)
    {
       int val1, val2, val3;
       double average;
       Scanner scan = new Scanner(System.in);

       // get three values from user
       System.out.println("Please enter three integers and " +
                       "I will compute their average");

       val1 = scan.nextInt();  // Read first integer
       val2 = scan.nextInt();  // Read second integer
       val3 = scan.nextInt();  // Read third integer

       // compute the average
       average = (val1 + val2 + val3) / 3.0;

       // print the average
       System.out.println("The average is: " + average);

       scan.close(); // Close scanner
    }
}
