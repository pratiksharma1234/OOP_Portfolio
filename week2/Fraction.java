package week2;

import java.util.Scanner;

public class Fraction {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scan = new Scanner(System.in);

        // Prompt user for numerator
        System.out.print("Enter the numerator: ");
        int numerator = scan.nextInt();  // Read numerator as an integer

        // Prompt user for denominator
        System.out.print("Enter the denominator: ");
        int denominator = scan.nextInt();  // Read denominator as an integer

        // Check for division by zero
        if (denominator == 0) {
            System.out.println("Error: Division by zero is undefined.");
        } else {
            // Compute decimal equivalent
            double decimalEquivalent = (double) numerator / denominator;
            
            // Display the result
            System.out.println("Decimal equivalent: " + decimalEquivalent);
        }

        // Close the scanner
        scan.close();
    }
}
