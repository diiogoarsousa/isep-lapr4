package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.examManagement.application.ListGradesOfExamController;


public class ListExamsUI extends AbstractUI {
    private final ListGradesOfExamController listGradesOfExamController = new ListGradesOfExamController();

    @Override
    protected boolean doShow() {
        System.out.println(listGradesOfExamController.listGradesOfExamsOfAllCourses());
        return false;
    }

    @Override
    public String headline() {
        return "List Exams";
    }
}
