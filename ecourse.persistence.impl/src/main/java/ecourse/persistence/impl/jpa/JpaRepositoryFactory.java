package ecourse.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import ecourse.Application;
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
import ecourse.infrastructure.persistence.RepositoryFactory;
import ecourse.requestEnrollment.repositories.EnrollmentsRepository;
import ecourse.scheduleMeeting.repositories.MeetingParticipantRepository;
import ecourse.scheduleMeeting.repositories.MeetingRepository;
import ecourse.studentManagement.repositories.SignupRequestRepository;
import ecourse.studentManagement.repositories.StudentRepository;
import ecourse.teacherAssignmentManagement.repository.TeacherAssignmentRepository;
import ecourse.teacherManagement.repository.TeacherRepository;
import ecourse.userManagement.repository.ManagerRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaStudentRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaStudentRepository(autoTx);
    }

    @Override
    public JpaStudentRepository clientUsers() {
        return new JpaStudentRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CourseRepository courses() {
        return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardRepository boards() {
        return new JpaBoardRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardStateRepository boardStates() {
        return new JpaBoardStateRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public PostItRepository postIts() {
        return new JpaPostItRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardParticipantRepository boardParticipants() {
        return new JpaBoardParticipantRepository(Application.settings().getPersistenceUnitName());
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
        return new JpaMeetingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TeacherAssignmentRepository teacherAssignments() {
        return new JpaTeacherAssignmentRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamRepository exams() {
        return new JpaExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public QuestionRepository questions() {
        return new JpaQuestionRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TeacherRepository teachers() {
        return new JpaTeacherRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ManagerRepository managers() {
        return new JpaManagerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public StudentRepository students() {
        return new JpaStudentRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CourseClassRepository courseClass() {
        return new JpaCourseClassRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public EnrollmentsRepository enrollments() {
        return new JpaEnrollmentsRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MeetingParticipantRepository meetingParticipants() {
        return new JpaMeetingParticipantsRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamTakenRepository examTaken() {
        return new JpaExamTakenRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
