package ecourse.infrastructure.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import ecourse.boardManagement.repositories.BoardParticipantRepository;
import ecourse.boardManagement.repositories.BoardRepository;
import ecourse.boardManagement.repositories.BoardStateRepository;
import ecourse.boardManagement.repositories.PostItRepository;
import ecourse.courseClassManagement.repository.CourseClassRepository;
import ecourse.courseManagement.repositories.CourseRepository;
import ecourse.courseManagement.repositories.CourseStateRepository;
import ecourse.courseUserManagement.repositories.CourseUserRepository;
import ecourse.examManagement.repositories.ExamRepository;
import ecourse.examManagement.repositories.QuestionRepository;
import ecourse.examTaken.repositories.ExamTakenRepository;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;
import ecourse.studentManagement.repositories.SignupRequestRepository;
import ecourse.studentManagement.repositories.StudentRepository;
import ecourse.teacherAssignmentManagement.repository.TeacherAssignmentRepository;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.repository.ManagerRepository;

/**
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return a transactional context
     */
    TransactionalContext newTransactionalContext();

    /**
     * @param autoTx the transactional context to enrol
     * @return a repository for users
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a repository for users
     */
    UserRepository users();

    /**
     * @param autoTx the transactional context to enroll
     * @return a repository for client users
     */
    StudentRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a repository for client users
     */
    StudentRepository clientUsers();

    /**
     * @param autoTx the transactional context to enroll
     * @return a repository for signup requests
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return a repository for signup requests
     */
    SignupRequestRepository signupRequests();

    CourseRepository courses();

    BoardRepository boards();

    BoardStateRepository boardStates();

    PostItRepository postIts();

    BoardParticipantRepository boardParticipants();

    CourseStateRepository courseStates();

    CourseUserRepository courseUsers();

    MeetingRepository meetings();

    TeacherAssignmentRepository teacherAssignments();

    ExamRepository exams();

    QuestionRepository questions();

    TeacherRepository teachers();

    ManagerRepository managers();

    StudentRepository students();

    CourseClassRepository courseClass();

    EnrollmentsRepository enrollments();

    MeetingParticipantRepository meetingParticipants();

    ExamTakenRepository examTaken();
}
