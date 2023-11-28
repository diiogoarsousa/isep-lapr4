package ecourse.courseManagement.application;

import eapli.framework.application.UseCaseController;
import ecourse.courseManagement.domain.Course;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@UseCaseController
public class ListExamsInACourseController {
    ListCourseService listCourseService = new ListCourseService();

    /**
     * Gets a list of all courses
     *
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        return StreamSupport.stream(listCourseService.allCourses().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of exams of a course
     *
     * @param course Course to get exams from
     * @return List of exams of a course
     */
    public List<String> getExamsOfACourse(Course course) {
        return listCourseService.getExamsOfACourse(course);
    }
}