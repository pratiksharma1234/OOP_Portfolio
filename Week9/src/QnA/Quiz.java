package QnA;

import java.util.Scanner;

public class Quiz {
    private Question[] questions;
    private int count;

    public Quiz() {
        questions = new Question[25];
        count = 0;
    }

    public void add(Question q) {
        if (count < questions.length) {
            questions[count++] = q;
        } else {
            System.out.println("Quiz is full.");
        }
    }

    public void giveQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < count; i++) {
            System.out.println("Q" + (i + 1) + ": " + questions[i].getQuestionText());
            String userAnswer = scanner.nextLine();
            if (questions[i].isCorrect(userAnswer)) {
                score++;
            }
        }

        System.out.println("\nQuiz Complete!");
        System.out.println("Score: " + score + " out of " + count);
        scanner.close();
    }
}

