package week4;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Formatoutput {
    public static void main(String[] args) {
        final double OUNCES_PER_POUND = 16.0;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter the price per pound: ");
        double pricePerPound = scan.nextDouble();
        
        System.out.print("Enter the weight in ounces: ");
        double weightOunces = scan.nextDouble();
        
        double weight = weightOunces / OUNCES_PER_POUND;
        double totalPrice = pricePerPound * weight;
        
        NumberFormat money = NumberFormat.getCurrencyInstance();
        DecimalFormat fmt = new DecimalFormat("0.00");
        
        System.out.println("\n***** CS Deli *****");
        System.out.println("Unit Price: " + money.format(pricePerPound) + " per pound");
        System.out.println("Weight: " + fmt.format(weight) + " pounds");
        System.out.println("TOTAL: " + money.format(totalPrice));
    }
}

