package ecourse.app.teacher.console.presentation.courseClass;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.courseClassManagement.application.ScheduleCourseClassController;
import ecourse.courseClassManagement.domain.CourseClass;
import ecourse.courseClassManagement.exceptions.ScheduleOverlapException;
import ecourse.courseManagement.domain.Course;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


public class ScheduleCourseClassUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ScheduleCourseClassController theController = new ScheduleCourseClassController();

    @Override
    protected boolean doShow() {

        final Course course = selectCourse();

        final String titleString = selectTitle();

        final LocalDate theDay = selectDate();
        final String[] validTime = selectTime();
        theDay.atTime(Integer.parseInt(validTime[0]), Integer.parseInt(validTime[1]));

        final Integer duration = selectDuration();

        boolean repeat = true;
        do {
            if (scheduleCourseClass(titleString, duration, theDay, course)) {
                theController.saveCourseClass();
                repeat = false;
            }
        } while (repeat || Console.readBoolean("Schedule other class? (Y/N)"));
        return true;
    }

    @Override
    public String headline() {
        return "Schedule a Course Class";
    }

    private boolean scheduleCourseClass(final String title, final Integer duration, final LocalDate date, final Course course) {
        try {
            final CourseClass courseClass = theController.scheduleCourseClass(title, date, duration, course);

            System.out.println( courseClass.identity() + " was scheduled.\n");
            return true;

        } catch (final ConcurrencyException e) {
            System.out.println("Problems with Data integrity");
        } catch (final ScheduleOverlapException e) {
            System.out.println("There is already a class scheduled for that time");
        }

        return false;
    }

    private String selectTitle() {
        return Console.readLine("Class Title:");
    }

    private Course selectCourse() {

        final List<Course> courses =  this.theController.getCoursesAssignedToTeacher(authz.session().get().authenticatedUser());

        var option = 0;

        if (courses.isEmpty()) {
            System.out.println("There are no registered Courses");
        } else {
            var cont = 1;
            System.out.println("Select a Course to schedule a class\n");
            System.out.printf("%-6s%-10s%n", "Nº:", "Course");
            for (final Course course : courses) {
                System.out.printf("%-6d%-10s%n", cont, course.name());
                cont++;
            }

            option = Console.readOption(1, courses.size(), 0);
            if (option != 0)
                return courses.get(option - 1);
        }

        return null;
    }

    private LocalDate selectDate() {
        LocalDate theDay;
        Calendar cal;

        do {
            cal= Console.readCalendar("Class Date (yyyy-MM-dd):","yyyy-MM-dd");
            theDay = LocalDate.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());

        } while (theDay.isBefore(LocalDate.now()));
        System.out.println("--------------");
        return theDay;
    }

    private String[] selectTime() {
        String stringTime;
        int hour, minutes;
        do {
            stringTime = Console.readLine("Please enter class start time (hh:mm): ");
            String[] time = stringTime.split(":");

            hour = Integer.parseInt(time[0]);
            minutes = Integer.parseInt(time[1]);

            if (hour < 0 || hour > 23 || minutes < 0 || minutes > 59)
                System.out.printf("Invalid time: %s\n", stringTime);

        } while (hour < 0 || hour > 23 || minutes < 0 || minutes > 59);
        System.out.println("--------------");

        return stringTime.split(":");
    }

    private Integer selectDuration() {
        int duration;

        do {
            System.out.println("Please enter class duration");
            duration = Console.readInteger("(Minutes):");
            if (duration <= 0)
                System.out.printf("Invalid Duration: %d\n", duration);
        }
        while (duration <= 0);
        System.out.println("--------------");
        return duration;
    }


}
