import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private String password;

    // Constructors, getters, and setters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    // Constructors, getters, and setters
    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Exam {
    private String examTitle;
    private List<Question> questions;

    // Constructors, getters, and setters
    public Exam(String examTitle) {
        this.examTitle = examTitle;
        this.questions = new ArrayList<>();
    }

    public String getExamTitle() {
        return examTitle;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class Answer {
    private Question question;
    private int selectedOption;

    // Constructors, getters, and setters
    public Answer(Question question, int selectedOption) {
        this.question = question;
        this.selectedOption = selectedOption;
    }

    public Question getQuestion() {
        return question;
    }

    public int getSelectedOption() {
        return selectedOption;
    }
}

class ExamTaker {
    private String username;
    private List<Answer> answers;

    // Constructors, getters, and setters
    public ExamTaker(String username) {
        this.username = username;
        this.answers = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void answerQuestion(Question question, int selectedOption) {
        Answer answer = new Answer(question, selectedOption);
        answers.add(answer);
    }
}

class ExamService {
    public void conductExam(Exam exam, ExamTaker examTaker) {
        List<Question> examQuestions = exam.getQuestions();
        double score = calculateScore(examTaker.getAnswers(), examQuestions);
        System.out.println("Score: " + score);
    }

    public double calculateScore(List<Answer> userAnswers, List<Question> correctAnswers) {
        int correctCount = 0;
        for (int i = 0; i < userAnswers.size(); i++) {
            if (userAnswers.get(i).getSelectedOption() == correctAnswers.get(i).getCorrectOption()) {
                correctCount++;
            }
        }
        return (double) correctCount / correctAnswers.size() * 100;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        // Example usage

        // Create questions
        Question question1 = new Question("What is the capital of France?", new String[]{"Paris", "Berlin", "Rome", "Madrid"}, 0);
        Question question2 = new Question("Which programming language is this system written in?", new String[]{"Java", "Python", "C++", "JavaScript"}, 0);

        // Create an exam
        Exam exam = new Exam("General Knowledge");
        exam.addQuestion(question1);
        exam.addQuestion(question2);

        // Create a user
        ExamTaker user = new ExamTaker("john_doe");

        // User answers questions
        user.answerQuestion(question1, 0);
        user.answerQuestion(question2, 0);

        // Conduct the exam and calculate the score
        ExamService examService = new ExamService();
        examService.conductExam(exam, user);
    }
}
