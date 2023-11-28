package ecourse.listFutureExams.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.courseManagement.application.ListCourseService;
import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.application.ExamService;
import ecourse.examManagement.domain.Exam;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.requestEnrollment.domain.ApprovalStatusEnum;
import ecourse.studentManagement.application.StudentService;
import ecourse.userManagement.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class ListFutureExamsController {
    private static final Logger logger = LoggerFactory.getLogger(ListFutureExamsController.class);

    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final ExamService examService = new ExamService();
    private final ListCourseService courseService = new ListCourseService();
    private final StudentService studentService = new StudentService(PersistenceContext.repositories().students());

    /**
     * Lists all future exams for the current user.
     */
    public void listFutureExams() {
        logger.debug("Listing future exams...");
        SystemUser user = getCurrentUser();
        if (user == null) {
            return;
        }

        var student = getStudentByUser(user);
        if (student == null) {
            return;
        }

        var courses = getAcceptedCoursesByStudent(student);
        if (courses.isEmpty()) {
            System.out.println("No future exams available.");
            return;
        }

        StringBuilder sb = printExamsByCourse(courses);
        if (sb.length() == 0) {
            System.out.println("No future exams available.");
            return;
        }

        System.out.println(sb);
    }

    private SystemUser getCurrentUser() {
        var session = authorizationService.session();

        return session.map(UserSession::authenticatedUser).orElse(null);
    }

    private Student getStudentByUser(SystemUser user) {
        return studentService.findBySystemUser(user);
    }

    private List<Course> getAcceptedCoursesByStudent(Student student) {
        return courseService.listCoursesByStudent(student, ApprovalStatusEnum.ACCEPTED);
    }

    private StringBuilder printExamsByCourse(List<Course> courses) {
        StringBuilder sb = new StringBuilder();
        for (var course : courses) {
            var examsInCourse = examService.findExamsByCourse(course);

            if (examsInCourse.isEmpty()) {
                continue;
            }

            sb.append(course.name());

            for (var exam : examsInCourse) {
                sb.append("-> ")
                        .append(exam.title())
                        .append(" - ")
                        .append(exam.getDate().getTime() + "\n");
            }
        }
        return sb;
    }

    public List<Exam> getMyExams() {
        List<Exam> exams = new ArrayList<>();
        SystemUser user = getCurrentUser();
        if (user == null) {
            return null;
        }
        List<Course> courses = getAcceptedCoursesByStudent(getStudentByUser(user));
        for (Course course : courses) {
            for (Exam exam : examService.findExamsByCourse(course)) {
                exams.add(exam);
            }
        }
        return exams;
    }
}