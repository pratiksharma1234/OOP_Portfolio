package week2;

import java.util.Scanner;

public class Milestokilometer {
    public static void main(String[] args) {
        // Constant conversion factor
        final double KM_PER_MILE = 1.60935;

        // Create a Scanner object for user input
        Scanner scan = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter the distance in miles: ");
        double miles = scan.nextDouble();  // Read user input as a floating point value

        // Convert miles to kilometers
        double kilometers = miles * KM_PER_MILE;

        // Display the result
        System.out.println(miles + " miles is equal to " + kilometers + " kilometers.");

        // Close the scanner
        scan.close();
    }
}

