package ecourse.app.student.console.presentation.exam.listFutureExams;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.listFutureExams.application.ListFutureExamsController;

public class ListFutureExamsUI extends AbstractUI {
    private final ListFutureExamsController controller = new ListFutureExamsController();


    @Override
    protected boolean doShow() {
        controller.listFutureExams();
        return false;
    }

    @Override
    public String headline() {
        return "List future exams";
    }
}
