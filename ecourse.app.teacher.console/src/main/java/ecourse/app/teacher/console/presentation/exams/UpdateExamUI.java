package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.examManagement.application.UpdateExamController;
import ecourse.examManagement.domain.Exam;

public class UpdateExamUI extends AbstractUI {

    private final UpdateExamController theController = new UpdateExamController();

    @Override
    protected boolean doShow() {
        final Iterable<Exam> iterable = this.theController.listExams();
        for (Exam i : iterable) {
            System.out.println(i.title());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Update Exam's";
    }
}
