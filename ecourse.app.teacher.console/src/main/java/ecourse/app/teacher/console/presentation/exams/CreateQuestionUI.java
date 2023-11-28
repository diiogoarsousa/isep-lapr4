package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.examManagement.application.CreateQuestionController;
import ecourse.examManagement.domain.Question;
import ecourse.examManagement.domain.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionUI extends AbstractUI {

    private final CreateQuestionController theController = new CreateQuestionController();
    private final QuestionTypePrinter questionTypePrinter = new QuestionTypePrinter();

    @Override
    protected boolean doShow() {
        List<Question> list = createQuestionList();
        createQuestion(list);
        return false;
    }
    @Override
    public String headline() {
        return "Create Question";
    }

    private void createQuestion(final List<Question> list) {
        try {
            for (Question i : list) {
                final Question question = theController.createQuestion(i);
            }
            System.out.println("Questions created.");
        } catch (final ConcurrencyException e) {
            System.out.println("Problems with Data integrity");
        }
    }

    private List<Question> createQuestionList() {
        var option = 0;
        List<Question> list = new ArrayList<>();
        QuestionType type = questionTypePrinter.getQuestionType();
        do {
            option = Console.readInteger("0: Exit | 1: Create new Question");
            switch (option) {
                case 0 -> {
                    if (!list.isEmpty()) {
                        System.out.println("--------------");
                        return list;
                    } else {
                        System.out.println("Question's list cant be null.");
                        return null;
                    }
                }
                case 1 -> {
                    String question = Console.readLine("Insert the question:");
                    String answer = Console.readLine("Insert the correct answer to the question:");
                    list.add(new Question(type, question, answer));
                    System.out.println("Question Added.");
                    System.out.println("--------------");
                }
            }
        } while (option > 0 && option < 2);

        return list;
    }
}
