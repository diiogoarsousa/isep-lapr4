package ecourse.examManagement.application;

import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.domain.*;

import java.util.Calendar;
import java.util.List;

public class CreateExamController {
    private final ExamService examService = new ExamService();

    public Exam createExam(String title, Header header, Course course, final List<Section> list, Calendar date) {
        return examService.createExam(title, header, course, list, date);
    }

    public Iterable<Question> listQuestionsType(QuestionType type) {
        return examService.findQuestionsByType(type);
    }

}
