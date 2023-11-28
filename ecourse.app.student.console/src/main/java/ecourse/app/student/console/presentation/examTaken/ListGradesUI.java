package ecourse.app.student.console.presentation.examTaken;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.examTaken.application.ListGradesController;
import ecourse.examTaken.domain.ExamTaken;

import static ecourse.app.common.console.utils.UiUtils.listArrowIndicator;

public class ListGradesUI extends AbstractUI {

    ListGradesController controller = new ListGradesController();

    /**
     * @return true if the user wants to exit the menu
     */
    @Override
    protected boolean doShow() {
        final Iterable<ExamTaken> iterable = this.controller.listMyGrades();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Grades.");
        } else {
            System.out.println("Grades:");
            for (ExamTaken examTaken : iterable) {
                System.out.println(listArrowIndicator + examTaken.exam().title() + " - " + examTaken.grade());
            }
        }
        return false;
    }

    /**
     * @return the headline of the UI
     */

    @Override
    public String headline() {
        return "List Grades";
    }
}
