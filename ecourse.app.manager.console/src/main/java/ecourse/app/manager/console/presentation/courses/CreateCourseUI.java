package ecourse.app.manager.console.presentation.courses;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseManagement.application.CreateCourseController;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.domain.CourseStateEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateCourseUI extends AbstractUI {
    private final CreateCourseController createCourseController = new CreateCourseController();

    /**
     * doShow method to show the UI
     *
     * @return false
     */
    @Override
    protected boolean doShow() {

        createCourse();

        return false;
    }

    /**
     * headline method to show the headline
     *
     * @return "Create Course"
     */
    @Override
    public String headline() {
        return "Create Course";
    }

    /**
     * Method to ask the user in the console for the course name
     *
     * @return the course name
     */
    private String askForCourseName() {
        return Console.readLine("Course Name");
    }

    /**
     * Method to ask the user in the console for the course start date
     *
     * @return the course start date
     */
    private Calendar askForCourseStartDate() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (true) {
            try {
                String startDateString = Console.readLine("Course Start Date (dd-MM-yyyy)");
                Date startDate = dateFormat.parse(startDateString);

                if (startDate.before(currentDate.getTime())) {
                    System.out.println("Error: Start date must be after the current date");
                    continue;
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                return calendar;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format. Please enter a date in the format dd-MM-yyyy");
            }
        }
    }

    /**
     * Method to ask the user in the console for the course end date
     *
     * @return the course end date
     */
    private Calendar askForCourseEndDate(Calendar startDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (true) {
            try {
                String endDateString = Console.readLine("Course End Date (dd-MM-yyyy)");
                Date endDate = dateFormat.parse(endDateString);

                if (endDate.before(startDate.getTime()) || endDate.equals(startDate.getTime())) {
                    System.out.println("Error: End date must be after the start date");
                    continue;
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                return calendar;
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format. Please enter a date in the format dd-MM-yyyy");
            }
        }
    }

    /**
     * Method to ask the user in the console for the course min enrolments
     *
     * @return the course min enrolments
     */
    private Integer askForCourseMinEnrolments() {
        return Console.readInteger("Course Min Enrolments");
    }

    /**
     * Method to ask the user in the console for the course max enrolments
     *
     * @return the course max enrolments
     */
    private Integer askForCourseMaxEnrolments() {
        return Console.readInteger("Course Max Enrolments");
    }

    private String askForCourseHeadTeacher() {
        return Console.readLine("Course Head Teacher");
    }

    /**
     * Method to ask the user in the console for the course description
     *
     * @return the course description
     */
    private String askForCourseDescription() {
        return Console.readLine("Course Description");
    }


    /**
     * Method to create a course
     */
    private void createCourse() {
        final String courseName = askForCourseName();
        final CourseStateEnum courseSate = CourseStateEnum.CLOSE;
        final Calendar startDate = askForCourseStartDate();
        final Calendar endDate = askForCourseEndDate(startDate);
        final Integer minEnrolments = askForCourseMinEnrolments();
        final Integer maxEnrolments = askForCourseMaxEnrolments();
        final String headTeacher = askForCourseHeadTeacher();
        final String description = askForCourseDescription();

        try {
            final Course course = createCourseController.createCourse(courseName, courseSate, startDate, endDate, minEnrolments, maxEnrolments, headTeacher);
            if (course != null) {
                System.out.println("SUCCESS. Your course is " + course.name());
            } else {
                System.out.println("It was not possible to create your course");
            }
        } catch (final ConcurrencyException e) {
            System.out.println("Problems with Data integrity");
        }
    }

}
