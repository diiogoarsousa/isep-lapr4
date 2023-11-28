package ecourse.examManagement.application;

import ecourse.examManagement.domain.Exam;
import ecourse.examManagement.repositories.ExamRepository;
import ecourse.infrastructure.persistence.PersistenceContext;

public class UpdateExamController {

    private final ExamRepository examRepository = PersistenceContext.repositories().exams();


    public Iterable<Exam> listExams() {
        return examRepository.findAll();
    }
}
