package ecourse.examManagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.domain.Exam;

import java.util.List;

public interface ExamRepository extends DomainRepository<Long, Exam> {

    Iterable<Exam> findAllExams();

    List<Exam> getExamsByCourse(Course course);
}
