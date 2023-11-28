package ecourse.app.teacher.console.presentation.exams;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class ExamMenu extends Menu {
    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;
    private static final int CREATE_EXAM = 1;
    private static final int UPDATE_EXAM = 2;
    private static final int CREATE_QUESTION = 3;
    private static final int UPDATE_QUESTION = 4;
    private static final int LIST_EXAMS = 5;

    public ExamMenu() {
        super("Exams");
        buildExamsMenu();
    }

    private void buildExamsMenu() {
        addItem(CREATE_EXAM, "Create Exam", new CreateExamUI()::show);
        addItem(UPDATE_EXAM, "Update Exam", Actions.SUCCESS);
        addItem(CREATE_QUESTION, "Create Question", new CreateQuestionUI()::show);
        addItem(UPDATE_QUESTION, "Update Question", Actions.SUCCESS);
        addItem(LIST_EXAMS, "List Exams", new ListExamsUI()::show);
        addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
    }
}
