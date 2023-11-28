package ecourse.app.common.console.utils;

import eapli.framework.util.Utility;
import ecourse.courseManagement.application.ListExamsInACourseController;
import ecourse.courseManagement.domain.Course;

import java.util.List;

import static eapli.framework.io.util.Console.readInteger;

@Utility
public final class UiUtils {
    private static final ListExamsInACourseController listExamsInACourseController = new ListExamsInACourseController();
    public static String listArrowIndicator = "⇨ ";

    private UiUtils() {
    }

    /**
     * Prompts the user to choose a course from a list of courses.
     *
     * @return the chosen course.
     */
    public static Course chooseCourse() {
        System.out.println("Select a course:");
        List<Course> courses = listExamsInACourseController.getAllCourses();
        return getCourse(courses);
    }

    /**
     * Prompts the user to choose a course from a list of courses.
     *
     * @param courses the list of courses to choose from.
     * @return the chosen course.
     */
    public static Course chooseCourse(List<Course> courses) {
        System.out.println("Select a course:");
        return getCourse(courses);
    }

    private static Course getCourse(List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).name());
        }
        int option;
        do {
            option = readInteger("");
        } while (option < 1 || option > courses.size());

        return courses.get(option - 1);
    }
}