package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.examManagement.application.UpdateQuestionController;
import ecourse.examManagement.domain.Question;

public class UpdateQuestionUI extends AbstractUI {

    private final UpdateQuestionController theController = new UpdateQuestionController();

    @Override
    protected boolean doShow() {
        final Iterable<Question> iterable = this.theController.listQuestions();
        for (Question i : iterable) {
            System.out.println(i.question());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Update Exam's";
    }
}
