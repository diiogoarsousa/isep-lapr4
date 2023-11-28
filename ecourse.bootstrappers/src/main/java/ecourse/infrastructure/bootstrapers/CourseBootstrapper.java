package ecourse.infrastructure.bootstrapers;

import eapli.framework.actions.Action;
import ecourse.courseManagement.application.CreateCourseController;
import ecourse.courseManagement.domain.CourseStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CourseBootstrapper implements Action {
    private static final Logger logger = LoggerFactory.getLogger(CourseBootstrapper.class);
    final CreateCourseController createCourseController = new CreateCourseController();

    @Override
    public boolean execute() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        try {
            createCourse("RCOMP", CourseStateEnum.IN_PROGRESS, "01-01-2023", "01-01-2024", 1, 5, "João");
            createCourse("SCOMP", CourseStateEnum.CLOSE, "02-03-2024", "02-03-2025", 10, 500, "Ana");
            createCourse("LPROG", CourseStateEnum.CLOSE, "01-06-2025", "01-06-2026", 5, 50, "Maria");
            createCourse("LAPR4", CourseStateEnum.CLOSE, "01-09-2026", "01-09-2027", 20, 100, "Pedro");
            createCourse("EAPLI", CourseStateEnum.CLOSE, "01-03-2029", "01-03-2030", 3, 30, "Rui");
            createCourse("ARQCP", CourseStateEnum.CLOSE, "01-12-2027", "01-12-2028", 15, 200, "Marta");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private void createCourse(final String courseName, final CourseStateEnum courseState,
                              final String startDateStr, final String endDateStr,
                              final Integer minEnrolments, final Integer maxEnrolments,
                              final String headTeacher) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        Date startDate = dateFormat.parse(startDateStr);
        calendar.setTime(startDate);
        Calendar startDateCalendar = calendar;

        Date endDate = dateFormat.parse(endDateStr);
        calendar.setTime(endDate);
        Calendar endDateCalendar = calendar;

        createCourseController.createCourse(courseName, courseState, startDateCalendar, endDateCalendar, minEnrolments, maxEnrolments, headTeacher);
        logger.debug("Created course with name: " + courseName);
    }
}