package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.io.util.Console;
import ecourse.examManagement.domain.QuestionType;

public class QuestionTypePrinter {

    public QuestionType getQuestionType() {
        System.out.println("""
                Question ype:\s
                 1. Multiple Choice\s
                 2. True or False\s
                 3. Short answer\s
                 4. Missing Words\s
                 5. Numerical\s
                 6. Matching""");
        QuestionType type;
        switch (Console.readInteger("Option:")) {
            case 1 -> {
                type = QuestionType.MULTIPLE_CHOICE;
            }
            case 2 -> {
                type = QuestionType.TRUE_FALSE;
            }
            case 3 -> {
                type = QuestionType.SHORT_ANSWER;
            }
            case 4 -> {
                type = QuestionType.MISSING_WORDS;
            }
            case 5 -> {
                type = QuestionType.NUMERICAL;
            }
            case 6 -> {
                type = QuestionType.MATCHING;
            }
            default -> type = null;
        }
        return type;
    }
}
