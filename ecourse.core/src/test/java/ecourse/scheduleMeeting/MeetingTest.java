package ecourse.scheduleMeeting;

import eapli.framework.infrastructure.authz.domain.model.*;
import ecourse.scheduleMeeting.domain.InviteState;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingDate;
import ecourse.scheduleMeeting.domain.MeetingDuration;
import ecourse.userManagement.domain.BaseRoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeetingTest {
    private Meeting meeting;

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }


    @BeforeEach
    public void setUp() {
        // Set up a sample meeting for testing
        List<SystemUser> participants = new ArrayList<>();
        participants.add(dummyUser("User1", BaseRoles.STUDENT));
        participants.add(dummyUser("User2", BaseRoles.STUDENT));
        SystemUser organizer = dummyUser("Organizer", BaseRoles.STUDENT);
        MeetingDuration duration = new MeetingDuration(60);
        MeetingDate date = new MeetingDate(new Calendar.Builder().setDate(2023, 5, 17).build()); //month is 0 based
        meeting = new Meeting(participants, duration, organizer, date);
    }

    @Test
    public void testMeetingInitialization() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        // Test if the meeting is initialized correctly
        Assertions.assertEquals(2, meeting.participants().size());
        Assertions.assertEquals("Organizer", meeting.organizer().username().toString());
        Assertions.assertEquals(60, meeting.duration().duration());
        Assertions.assertEquals("2023-06-17", format1.format(meeting.date().calendar().getTime()));
    }

    @Test
    public void testMeetingParticipantResponseOnInvite() {
        // Test if the participant responses are initialized correctly
        Assertions.assertEquals("User1", meeting.participants().get(0).participant().username().toString());
        Assertions.assertEquals(InviteState.INVITED, meeting.participants().get(0).state());
        Assertions.assertEquals("User2", meeting.participants().get(1).participant().username().toString());
        Assertions.assertEquals(InviteState.INVITED, meeting.participants().get(1).state());
    }

    @Test
    public void testMeetingParticipantResponseAccept() {
        // Test if the participant responses are initialized correctly
        meeting.participants().get(0).changeStateTo(InviteState.ACCEPTED);
        meeting.participants().get(1).changeStateTo(InviteState.REFUSED);
        Assertions.assertEquals(InviteState.ACCEPTED, meeting.participants().get(0).state());
        Assertions.assertEquals(InviteState.REFUSED, meeting.participants().get(1).state());

    }
}