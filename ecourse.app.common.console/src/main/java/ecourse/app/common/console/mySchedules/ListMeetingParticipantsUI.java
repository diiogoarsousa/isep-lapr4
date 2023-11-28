package ecourse.app.common.console.mySchedules;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.scheduleMeeting.application.ListMeetingController;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingParticipant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static ecourse.app.common.console.utils.UiUtils.listArrowIndicator;

public class ListMeetingParticipantsUI extends AbstractUI {
    private static final Logger logger = LoggerFactory.getLogger(ListMeetingParticipantsUI.class);
    private final ListMeetingController controller = new ListMeetingController();


    @Override
    protected boolean doShow() {
        Meeting myMeeting = selectMyMeeting();

        System.out.println("+==============================================================================+");

        for (MeetingParticipant i : controller.listMeetingParticipants(myMeeting)) {
            System.out.println(listArrowIndicator + "Username: " + i.participant().username() + "\n\t◟Invite State: " + i.state());
        }

        return false;
    }

    @Override
    public String headline() {
        return "List a Meeting Participants";
    }

    /**
     *  Select Meeting by user
     * @return meeting
     */
    private Meeting selectMyMeeting() {
        final Iterable<Meeting> iterable = this.controller.listMyMeetings();
        final List<Meeting> list = new ArrayList<>();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Meetings");
        } else {
            var cont = 1;
            System.out.println("SELECT User to invite to Meeting\n");
            System.out.printf("%-6s%-30s%n", "Nº:", "Meeting Day");

            for (final Meeting meeting : iterable) {
                list.add(meeting);
                System.out.printf("%-6d%-30s%n", cont, meeting.day().getTime());
                cont++;
            }

            final var option = Console.readInteger("\nEnter Meeting nº to see participants or 0 to finish ");
            if (option != 0) {
                try {
                    return list.get(option - 1);
                } catch (IndexOutOfBoundsException e) {
                    logger.error("ELEMENT NOT FOUND");
                    System.out.println("Invalid Meeting");
                }
            } else {
                System.out.println("No meeting selected");
            }
        }
        return null;
    }
}
