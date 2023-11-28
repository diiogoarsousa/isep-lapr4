package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.application.CreateExamController;
import ecourse.examManagement.domain.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static ecourse.app.common.console.utils.UiUtils.chooseCourse;

public class CreateExamUI extends AbstractUI {
    private final CreateExamController controller = new CreateExamController();
    private final QuestionTypePrinter questionTypePrinter = new QuestionTypePrinter();

    @Override
    protected boolean doShow() {
        final String examTitle = Console.readLine("Exam Title:");
        System.out.println("--------------");
        Header header = createHeader();
        System.out.println("--------------");
        Course course = chooseCourse();
        List<Section> section = createSection();
        Calendar date = Console.readCalendar("Exam Date (dd-mm-yyyy):");
        createExam(examTitle, header, course, section, date);
        return false;
    }


    private void createExam(String title, Header header, Course course, final List<Section> list, Calendar date) {
        try {
            final Exam exam = controller.createExam(title, header, course, list, date);
            if (exam != null) {
                System.out.println("SUCCESS!");
            } else {
                System.out.println("It was not possible to create the exam");
            }
        } catch (final ConcurrencyException e) {
            System.out.println("Problems with Data integrity");
        }
    }

    private List<Section> createSection() {
        var option = 0;
        List<Section> list = new ArrayList<>();

        System.out.println("""
                Exam type:\s
                 1. Automatic\s
                 2. Manual""");
        int examType = Console.readInteger("Option:");
        do {
            option = Console.readInteger("0: Exit | 1: Create new Section");
            switch (option) {
                case 0:
                    if (!list.isEmpty()) {
                        System.out.println("--------------");
                        return list;
                    } else {
                        System.out.println("Section's list cant be null.");
                    }
                    break;
                case 1:
                    String description = Console.readLine("Section description:");
                    QuestionType type = questionTypePrinter.getQuestionType();
                    switch (examType) {
                        case 1:
                            int numberQuestions = Console.readInteger("Number of Questions:");
                            final List<Question> iterable = (List<Question>) this.controller.listQuestionsType(type);
                            Collections.shuffle(iterable);
                            List<Question> randomQuestions = iterable.subList(0, numberQuestions);
                            list.add(new Section(randomQuestions, description, type));
                            System.out.println("--------------");
                            break;
                        case 2:
                            List<Question> questionList = selectQuestion(type);
                            list.add(new Section(questionList, description, type));
                            System.out.println("--------------");
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 0);

        return list;
    }


    private List<Question> selectQuestion(QuestionType type) {
        final List<Question> list = new ArrayList<>();
        var option = 0;
        final List<Question> iterable = (List<Question>) this.controller.listQuestionsType(type);
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Questions");
        } else {
            do {
                var cont = 1;
                System.out.println("Select Question to add to the Exam\n");
                System.out.printf("%-6s%-30s%n", "Nº:", "Question");
                for (final Question question : iterable) {
                    System.out.printf("%-6d%-30s%n", cont, " " + question.question() + " ");
                    cont++;
                }
                option = Console.readInteger("Enter Question nº to add Exam or 0 to finish ");
                if (option == 0) {
                    System.out.println("Question selected");
                    return list;
                } else {
                    list.add(iterable.get(option - 1));
                    iterable.remove(iterable.get(option - 1));
                }
            } while (option != 0);
        }
        return null;
    }

    @Override
    public String headline() {
        return "Create a Exam";
    }

    private Header createHeader() {
        FeedbackTypeEnum feedbackType;
        GradeTypeEnum gradeType;

        System.out.println("""
                Feedback type:\s
                 1. None\s
                 2. On Submission\s
                 3. After Closing""");

        int feedbackOption;
        do {
            feedbackOption = Console.readInteger("Option:");
            switch (feedbackOption) {
                case 1:
                    feedbackType = FeedbackTypeEnum.NONE;
                    break;
                case 2:
                    feedbackType = FeedbackTypeEnum.ON_SUBMISSION;
                    break;
                case 3:
                    feedbackType = FeedbackTypeEnum.AFTER_CLOSING;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    feedbackType = null;
                    break;
            }
        } while (feedbackType == null);

        System.out.println("""
                Grade type:\s
                 1. None\s
                 2. On Submission\s
                 3. After Closing""");

        int gradeOption;
        do {
            gradeOption = Console.readInteger("Option:");
            switch (gradeOption) {
                case 1:
                    gradeType = GradeTypeEnum.NONE;
                    break;
                case 2:
                    gradeType = GradeTypeEnum.ON_SUBMISSION;
                    break;
                case 3:
                    gradeType = GradeTypeEnum.AFTER_CLOSING;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    gradeType = null;
                    break;
            }
        } while (gradeType == null);

        String description = Console.readLine("Description:");
        return new Header(feedbackType, description, gradeType);
    }

}
