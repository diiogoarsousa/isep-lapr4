package ecourse.examManagement.application;

import ecourse.courseManagement.application.ListCourseService;
import ecourse.examTaken.domain.ExamTaken;


public class ListGradesOfExamController {
    private final ListCourseService listCourseService = new ListCourseService();

    public StringBuilder listGradesOfExamsOfAllCourses() {
        StringBuilder sb = new StringBuilder();
        for (var course : listCourseService.allCourses()) {
            sb.append("→ ")
                    .append(course.name())
                    .append("\n");
            for (var exam : course.getExams()) {
                sb.append("\t⌊ ")
                        .append(exam.title())
                        .append("\n");
                for (ExamTaken examTaken : exam.getExamTaken()) {
                    sb.append("\t\t⌊ ")
                            .append(examTaken.getStudent().mecanographicNumber())
                            .append(" → ")
                            .append(examTaken.grade())
                            .append("\n");
                }
            }
        }
        return sb;
    }
}