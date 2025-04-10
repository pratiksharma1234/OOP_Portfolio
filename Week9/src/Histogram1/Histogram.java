package Histogram1;

import java.util.ArrayList;
import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers between 1 and 100 (type -1 to stop):");
        while (true) {
            int input = scanner.nextInt();
            if (input == -1) break;
            if (input >= 1 && input <= 100) {
                numbers.add(input);
            } else {
                System.out.println("Invalid input. Enter a number between 1 and 100.");
            }
        }

        int[] ranges = new int[10]; // 10 ranges: 1-10, 11-20, ..., 91-100

        for (int num : numbers) {
            int index = (num - 1) / 10;
            ranges[index]++;
        }

        for (int i = 0; i < ranges.length; i++) {
            int start = i * 10 + 1;
            int end = start + 9;
            System.out.printf("%2d -%3d | ", start, end);
            for (int j = 0; j < ranges[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}

