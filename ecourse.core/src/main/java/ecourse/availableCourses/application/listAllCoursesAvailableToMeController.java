package ecourse.availableCourses.application;

import ecourse.courseManagement.application.ListCourseService;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;

import java.util.List;

public class listAllCoursesAvailableToMeController {
    private final ListCourseService listCourseService = new ListCourseService();

    public List<Course> availableCourses() {
        return listCourseService.getCoursesByState(CourseStateEnum.ENROLL);
    }
}
