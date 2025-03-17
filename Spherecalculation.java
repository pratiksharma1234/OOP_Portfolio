package week4;

import java.util.Scanner;

public class Spherecalculation {
    public static void main(String[] args) {
        final double PI = Math.PI;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter the radius of the sphere: ");
        double radius = scan.nextDouble();
        
        double volume = (4.0 / 3.0) * PI * Math.pow(radius, 3);
        double surfaceArea = 4 * PI * Math.pow(radius, 2);
        
        System.out.printf("Volume: %.4f\n", volume);
        System.out.printf("Surface Area: %.4f\n", surfaceArea);
    }
}
