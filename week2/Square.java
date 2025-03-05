package week2;

import java.util.Scanner;

public class Square {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scan = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter the length of the square's side: ");
        int side = scan.nextInt();  // Read user input as an integer

        // Compute perimeter and area
        int perimeter = 4 * side;  // Perimeter of a square = 4 * side
        int area = side * side;    // Area of a square = side * side

        // Display the results
        System.out.println("Perimeter of the square: " + perimeter);
        System.out.println("Area of the square: " + area);

        // Close the scanner
        scan.close();
    }
}