package QnA;

public class QuizTime {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        quiz.add(new Question("Who is the prime minister of nepal?", "kp sharma oli"));
        quiz.add(new Question("4 * 4 equals?", "16"));
        quiz.add(new Question("what is the national bird of nepal?", "danphe"));
        quiz.add(new Question("what is the national animal of nepal?", "cow"));
        quiz.add(new Question("which language is most spoken in nepal?", "nepali"));
        quiz.add(new Question("what is the capital city of nepal?", "kathmandu"));
        

        quiz.giveQuiz();
    }
}

