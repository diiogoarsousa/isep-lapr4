import ExamRecognizer.ExamLexer;
import ExamRecognizer.ExamParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1. Read Exams Repository
                    2. Read Questions Repository
                    3. Read Answers Repository
                    0. Exit""");

            int option = scanner.nextInt();
            switch (option) {
                case 0 -> {
                    System.out.println("############################\nBye bye!\n############################");
                    return;
                }
                case 1 -> {
                    readExams();
                }
                case 2 -> {
                    readQuestions();
                }
                case 3 -> {
                    readAnswers();
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        }
    }

    private static void readExams() throws IOException {
        CharStream inputExam = CharStreams.fromFileName("ecourse.antlr/src/main/java/Repository/exams.txt");
        ExamLexer lexer = new ExamLexer(inputExam);
        ExamParser parser = new ExamParser(new CommonTokenStream(lexer));
        parser.start();
    }

    private static void readQuestions() throws IOException {
        CharStream inputExam = CharStreams.fromFileName("ecourse.antlr/src/main/java/Repository/answers.txt");
        ExamLexer lexer = new ExamLexer(inputExam);
        ExamParser parser = new ExamParser(new CommonTokenStream(lexer));
        parser.start();

    }

    private static void readAnswers() throws IOException {
        CharStream inputExam = CharStreams.fromFileName("ecourse.antlr/src/main/java/Repository/answers.txt");
        ExamLexer lexer = new ExamLexer(inputExam);
        ExamParser parser = new ExamParser(new CommonTokenStream(lexer));
        parser.start();
    }
}