package ecourse.app.student.console.presentation.examTaken;
import ExamRecognizer.ExamLexer;
import ExamRecognizer.ExamParser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.examManagement.domain.Exam;
import ecourse.examManagement.domain.Question;
import ecourse.examManagement.domain.Section;
import ecourse.examTaken.application.TakeExamController;
import ecourse.examTaken.domain.ExamTaken;
import ecourse.listFutureExams.application.ListFutureExamsController;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TakeExamUI extends AbstractUI {
    private final ListFutureExamsController controller = new ListFutureExamsController();

    private final TakeExamController takeExamController = new TakeExamController();

    /**
     * Checks if the user resolved the exam before
     * If not, the exam is resolved and the grade is calculated
     * If yes, the user is informed that the exam has already been solved
     */
    @Override
    protected boolean doShow() {
        Exam exam = selectExam();
        final Optional<ExamTaken> examTaken = takeExamController.takeExam(exam);
        examTaken.ifPresentOrElse(
                b -> System.out.println("This exam has already been solved"),
                () -> {
                    String examString = readExam(Objects.requireNonNull(exam));
                    final int grade = calcGrade(examString);
                    takeExamController.saveExamTaken(exam, grade);
                });
        return false;
    }

    @Override
    public String headline() {
        return "Take Exam";
    }

    /**
     * @param exam Transforms the exam into a String in order to be recognized by parser grammar
     */
    private String readExam(Exam exam) {
        StringBuilder start = new StringBuilder("Title \"" +
                exam.title() +
                "\" Feedback " +
                exam.header().feedback() +
                " Grade " +
                exam.header().grade() +
                " Description " +
                "\"none\" ");
        // exam.header().description();
        System.out.println("Exam: " + exam.title() + "\n");
        for (Section i : exam.getSections()) {
            System.out.println("Section: \"" + i.getTitle() + "\" - " + i.getQuestionType());
            System.out.println("|--------------------------------------------|\n");
            List<Question> questions = i.getQuestions();
            start.append("{ Description \"").append(i.getTitle()).append("\" ");
            for (Question j : questions) {
                System.out.println("Question: " + j.question());
                start.append(i.getQuestionType()).append(" \"").append(j.question()).append("\" \"").append(j.correctAnswer()).append("\" 10 \"").append(Console.readLine("Answer: ")).append("\" ");

            }
            start.append("} ");
            System.out.println("|--------------------------------------------|\n");
        }
        System.out.println("Exam finished");
        start.append("END");
        return start.toString();
    }

    /**
     * @return the grade of the exam
     */
    private int calcGrade(String start) {
        CharStream inputExam = CharStreams.fromString(start);
        ExamLexer lexerExam = new ExamLexer(inputExam);
        CommonTokenStream tokensExam = new CommonTokenStream(lexerExam);
        ExamParser parserExam = new ExamParser(tokensExam);
        parserExam.start();
        return parserExam.totalScore;
    }

    /**
     * @return the exam selected by the user
     */
    private Exam selectExam() {
        final List<Exam> list = new ArrayList<>();
        final List<Exam> iterable = this.controller.getMyExams();
        if (!iterable.iterator().hasNext()) {
            System.out.println("You have no exams to take");
        } else {
            var cont = 1;
            System.out.println("Select Exam to do\n");
            System.out.printf("%-6s%-30s%-30s%n", "Nº:", "Exam Title", "Course");
            for (final Exam exam : iterable) {
                list.add(exam);
                System.out.printf("%-6d%-30s%-30s%n", cont, exam.title(), exam.course().name());
                cont++;
            }
            final var option = Console.readInteger("Enter Exam nº to do or 0 to finish ");
            if (option == 0)
                System.out.println("No exam selected");
            else
                return list.get(option - 1);

        }
        return null;
    }
}