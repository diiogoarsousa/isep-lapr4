package ecourse.examManagement.application;

import eapli.framework.application.ApplicationService;
import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.domain.*;
import ecourse.examManagement.repositories.ExamRepository;
import ecourse.examManagement.repositories.QuestionRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

import java.util.Calendar;
import java.util.List;

@ApplicationService
public class ExamService {
    private final ExamRepository examRepository = PersistenceContext.repositories().exams();
    private final QuestionRepository questionRepository = PersistenceContext.repositories().questions();

    public Exam createExam(String title, Header header, Course course, final List<Section> list, Calendar date) {
        return examRepository.save(new Exam(title, header, course, list, date));
    }

    public Iterable<Question> findQuestionsByType(QuestionType type) {
        return questionRepository.findQuestionsByType(type);
    }

    public List<Exam> findExamsByCourse(Course course) {
        return examRepository.getExamsByCourse(course);
    }

    public Iterable<Exam> findAllExams() {
        return examRepository.findAllExams();
    }
}
