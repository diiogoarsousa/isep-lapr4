package ecourse.courseClassManagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import ecourse.courseClassManagement.domain.CourseClass;
import ecourse.courseClassManagement.domain.CourseClassDate;
import ecourse.courseClassManagement.domain.CourseClassDuration;
import ecourse.courseClassManagement.domain.CourseClassTitle;
import ecourse.courseClassManagement.exceptions.ScheduleOverlapException;
import ecourse.courseClassManagement.repository.CourseClassRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.infrastructure.persistence.PersistenceContext;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.domain.BaseRoles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class ScheduleCourseClassController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CourseClassRepository courseClassRepository = PersistenceContext.repositories().courseClass();
    private final TeacherRepository teacherRepository = PersistenceContext.repositories().teachers();

    private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

    private CourseClass courseClass;

    public void saveCourseClass() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.TEACHER);
        courseClassRepository.save(this.courseClass);
    }


    public List<Course> getCoursesAssignedToTeacher(SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.TEACHER);

        List<Course> courseList = new ArrayList<>();
        courseRepository.findAllCoursesNotClosed().spliterator().forEachRemaining(courseList::add);

        return courseList;
    }

    public CourseClass scheduleCourseClass(final String title, final LocalDate startDate, final Integer duration, final Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.TEACHER);

        CourseClass courseClass = new CourseClass(new CourseClassTitle(title), new CourseClassDate(startDate), new CourseClassDuration(duration), course);

        //check if class overlaps with another class
        if (checkForCourseClassOverlap(courseClass)) {
            this.courseClass = courseClass;
            return courseClass;
        } else
            throw new ScheduleOverlapException();
    }

    private boolean checkForCourseClassOverlap(CourseClass courseClass) {
        Iterable<CourseClass> courseClasses = courseClassRepository.getAllCourseClassesFromCourse(courseClass.getCourse());

        for (CourseClass cc : courseClasses) {
            if (cc.hasSameWeekDay(courseClass))
                if (cc.getStartDate().isBefore(courseClass.getStartDate()) && cc.getEndDate().isBefore(courseClass.getStartDate())
                        || (cc.getStartDate().isBefore(courseClass.getEndDate()) && cc.getEndDate().isBefore(courseClass.getEndDate())))
                    return false;
        }
        return true;
        //TODO: Review this method. Not sure if it works as intended
    }

}
