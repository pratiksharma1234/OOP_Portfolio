package Postcode;

import java.util.Scanner;

public class PostCodeReader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PostEntry[] entries = new PostEntry[25];
        int count = 0;

        System.out.println("Enter up to 25 entries (FirstName LastName PostCode).");
        System.out.println("Use space, tab, or comma as separator. Type 'done' to stop:");

        while (scanner.hasNextLine() && count < 25) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("done")) break;

            String[] parts = line.trim().split("[\\t, ]+"); // Accepts tab, comma, or space
            if (parts.length == 3) {
                entries[count] = new PostEntry(parts[0], parts[1], parts[2]);
                count++;
            } else {
                System.out.println("Invalid input format. Use: FirstName LastName PostCode (space, tab, or comma)");
            }
        }

        System.out.println("\nCollected Entries:");
        for (int i = 0; i < count; i++) {
            entries[i].display();
        }

        scanner.close();
    }
}
