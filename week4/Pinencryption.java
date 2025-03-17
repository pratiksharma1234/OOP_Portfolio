package week4;

import java.util.Random;
import java.util.Scanner;

public class Pinencryption {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.print("Enter a 4-digit PIN to encrypt: ");
        int pin = scan.nextInt();
        
        String hexPin = Integer.toHexString(pin);
        String randHex1 = Integer.toHexString(rand.nextInt(64536) + 1000);
        String randHex2 = Integer.toHexString(rand.nextInt(64536) + 1000);
        
        System.out.println("Your encrypted PIN: " + randHex1 + hexPin + randHex2);
    }
}
