package ecourse.app.teacher.console.presentation.listExamsInACourse;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.application.ListExamsInACourseController;
import ecourse.courseManagement.domain.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ecourse.app.common.console.utils.UiUtils.chooseCourse;
import static ecourse.app.common.console.utils.UiUtils.listArrowIndicator;

public class ListExamsInACourseUI extends AbstractUI {
    private static final Logger logger = LoggerFactory.getLogger(ListExamsInACourseUI.class);
    private final ListExamsInACourseController controller = new ListExamsInACourseController();

    @Override
    protected boolean doShow() {
        Course selectedCourse = chooseCourse();
        logger.info("Selected course: {}", selectedCourse.name());

        System.out.println(selectedCourse.name() + " exams:");
        controller.getExamsOfACourse(selectedCourse).stream().
                map(exam -> listArrowIndicator + exam).
                forEach(System.out::println);

        return false;
    }

    @Override
    public String headline() {
        return "List exams in a course";
    }
}

