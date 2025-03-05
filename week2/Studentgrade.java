package week2;

public class Studentgrade {
    public static void main(String[] args) {
        // Print the top border
        System.out.println("///////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("==          Student Points          ==");
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////");

        // Print table header
        System.out.println();
        System.out.printf("%-12s %-6s %-6s %-6s%n", "Name", "Lab", "Bonus", "Total");
        System.out.printf("%-12s %-6s %-6s %-6s%n", "----", "---", "-----", "-----");

        // Print student data
        System.out.printf("%-12s %-6d %-6d %-6d%n", "pratik", 40, 8, (40 + 8));
        System.out.printf("%-12s %-6d %-6d %-6d%n", "sulav", 80, 8, (80 + 8));
        System.out.printf("%-12s %-6d %-6d %-6d%n", "simon", 39, 20, (39 + 20));
        System.out.printf("%-12s %-6d %-6d %-6d%n", "nishan", 48, 6, (48 + 6));
        System.out.printf("%-12s %-6d %-6d %-6d%n", "sugam", 45, 9, (45 + 9));
    }
}


