package ecourse.app.common.console.mySchedules;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.scheduleMeeting.application.CancelMeetingController;
import ecourse.scheduleMeeting.domain.Meeting;

import java.util.ArrayList;
import java.util.List;

public class CancelMeetingUI extends AbstractUI {

    private final CancelMeetingController theController = new CancelMeetingController();

    @Override
    protected boolean doShow() {
        Meeting meeting = showMyMeetings();
        if (!(meeting == null)) {
            System.out.println("O Meeting do dia " + meeting.day().getTime() + " foi cancelado.");
            this.theController.cancelMeeting(meeting);
        }
        return false;
    }


    @Override
    public String headline() {
        return "Cancel a Meeting";
    }

    private Meeting showMyMeetings() {
        final List<Meeting> list = new ArrayList<>();
        final Iterable<Meeting> iterable = this.theController.listMyMeetings();
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
            final var option = Console.readInteger("Enter Meeting nº to cancel or 0 to finish ");
            if (option == 0) {
                System.out.println("No meeting selected");
            } else {
                try {
                    return list.get(option - 1);
                } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                    System.out
                            .println(
                                    "WARNING: That entity has already been changed or deleted since you last read it");
                }
            }
        }
        return null;
    }
}
