package week4;
import java.util.Random;
import java.util.Scanner;

public class Dice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.print("How many sides does die 1 have? ");
        int sides1 = scan.nextInt();
        System.out.print("How many sides does die 2 have? ");
        int sides2 = scan.nextInt();
        
        int total1 = 0, total2 = 0;
        
        for (int i = 1; i <= 3; i++) {
            int roll1 = rand.nextInt(sides1) + 1;
            int roll2 = rand.nextInt(sides2) + 1;
            total1 += roll1;
            total2 += roll2;
            System.out.println("Die 1 roll " + i + " = " + roll1);
            System.out.println("Die 2 roll " + i + " = " + roll2);
        }
        
        System.out.println("Die 1 rolled a total of " + total1 + " and averaged " + (total1 / 3.0));
        System.out.println("Die 2 rolled a total of " + total2 + " and averaged " + (total2 / 3.0));
    }
}
