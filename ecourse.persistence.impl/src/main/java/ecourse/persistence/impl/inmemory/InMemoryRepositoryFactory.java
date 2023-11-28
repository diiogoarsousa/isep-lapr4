package ecourse.persistence.impl.inmemory;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
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
import ecourse.infrastructure.bootstrapers.BaseBootstrapper;
import ecourse.infrastructure.persistence.RepositoryFactory;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;
import ecourse.studentManagement.repositories.SignupRequestRepository;
import ecourse.studentManagement.repositories.StudentRepository;
import ecourse.teacherAssignmentManagement.repository.TeacherAssignmentRepository;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.repository.ManagerRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public StudentRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryStudentRepository();
    }

    @Override
    public StudentRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public CourseRepository courses() {
        return null;
    }

    @Override
    public BoardRepository boards() {
        return null;
    }

    @Override
    public BoardStateRepository boardStates() {
        return null;
    }

    @Override
    public PostItRepository postIts() {
        return null;
    }

    @Override
    public BoardParticipantRepository boardParticipants() {
        return null;
    }

    @Override
    public CourseStateRepository courseStates() {
        return null;
    }

    @Override
    public CourseUserRepository courseUsers() {
        return null;
    }

    @Override
    public MeetingRepository meetings() {
        return null;
    }

    @Override
    public TeacherAssignmentRepository teacherAssignments() {
        return null;
    }

    @Override
    public ExamRepository exams() {
        return null;
    }

    @Override
    public QuestionRepository questions() {
        return null;
    }

    @Override
    public TeacherRepository teachers() {
        return new InMemoryTeacherRepository();
    }

    @Override
    public ManagerRepository managers() {
        return new InMemoryManagerRepository();
    }

    @Override
    public StudentRepository students() {
        return new InMemoryStudentRepository();
    }

    @Override
    public CourseClassRepository courseClass() {
        return null;
    }

    @Override
    public MeetingParticipantRepository meetingParticipants() {
        return null;
    }

    @Override
    public ExamTakenRepository examTaken() {
        return null;
    }

    @Override
    public EnrollmentsRepository enrollments() {
        return new InMemoryEnrollmentRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
