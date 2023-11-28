package ecourse.courseManagement.application.viadto;

import eapli.framework.application.UseCaseController;
import ecourse.courseManagement.dto.CourseDTO;

@UseCaseController
public class ListCourseDTOController {

    private final ListCourseDTOService svc = new ListCourseDTOService();

    public Iterable<CourseDTO> allCourses() {
        return svc.allCourses();
    }
}
