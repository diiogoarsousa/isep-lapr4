package ecourse.courseManagement.application;

import ecourse.courseManagement.domain.Course;
import ecourse.examManagement.domain.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("ListExamsInACourseController")
class ListExamsInACourseControllerTest {

    private ListExamsInACourseController listExamsInACourseController;

    @BeforeEach
    void setUp() {
        listExamsInACourseController = mock(ListExamsInACourseController.class);
    }

    @Test
    void getExamsOfACourseReturnsEmptyListWhenNoExams() {
        //Arrange
        Course course = mock(Course.class);
        List<Exam> exams = new ArrayList<>();
        when(course.getExams()).thenReturn(exams);

        //Act
        List<String> result = listExamsInACourseController.getExamsOfACourse(course);

        //Assert
        assertTrue(result.isEmpty());
    }

}