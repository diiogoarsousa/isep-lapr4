package ecourse.app.manager.console.presentation.courses.enrollments;

import eapli.framework.presentation.console.AbstractUI;
import ecourse.bulkEnrollment.application.EnrollBulkStudentController;

import static eapli.framework.io.util.Console.readInteger;


public class EnrollBulkStudentUI extends AbstractUI {
    private final EnrollBulkStudentController controller = new EnrollBulkStudentController();


    @Override
    protected boolean doShow() {
        var csvData = controller.askForFileCSVPathAndGetItsData();
        var courses = controller.getListOfCoursesAvailable(csvData);
        if (courses.isEmpty()) {
            System.out.println("There are no courses available for enrollment.");
            return false;
        }

        System.out.println("Select a course:");
        for (int i = 0; i < courses.size(); i++) {
            long cantBeEnrolled = controller.getAvailability(csvData, courses.get(i));

            StringBuilder sb = new StringBuilder();
            sb.append(i + 1).append(". ").append(courses.get(i).name());

            if (cantBeEnrolled != 0) {
                sb.append(" [").append(cantBeEnrolled + courses.get(i).getMaxEnrollments())
                        .append("/")
                        .append(courses.get(i).getMaxEnrollments()).append("]");
            }
            System.out.print(sb);
        }
        int option;
        do {
            option = readInteger("");
        } while (option < 1 || option > courses.size());

        var notEnrolledStudents = controller.enrollStudents(csvData, courses.get(option - 1));

        if (notEnrolledStudents.isEmpty()) {
            System.out.println("All students were enrolled successfully.");
        } else {
            System.out.println("The following students couldn't be enrolled:");
            notEnrolledStudents.forEach(System.out::println);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Enroll Students in Bulk";
    }
}
