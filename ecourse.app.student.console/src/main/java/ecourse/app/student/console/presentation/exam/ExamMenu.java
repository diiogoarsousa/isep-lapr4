package ecourse.app.student.console.presentation.exam;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import ecourse.app.student.console.presentation.exam.listFutureExams.ListFutureExamsUI;
import ecourse.app.student.console.presentation.examTaken.ListGradesUI;
import ecourse.app.student.console.presentation.examTaken.TakeExamUI;
public class ExamMenu extends Menu {
    private static final String TITLE = "Exams";
    private static final String SEPARATOR_LABEL = "--------------";
    private static final String RETURN = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int FUTURE_EXAMS = 1;
    private static final int LIST_GRADES = 2;
    private static final int TAKE_EXAM = 3;


    public ExamMenu() {
        super(TITLE);
        buildCoursesMenu();
    }

    private void buildCoursesMenu() {
        addItem(FUTURE_EXAMS, "List future exams", () -> new ListFutureExamsUI().show());
        addItem(LIST_GRADES, "List grades", () -> new ListGradesUI().show());
        addItem(TAKE_EXAM, "Take exam", () -> new TakeExamUI().show());


        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
