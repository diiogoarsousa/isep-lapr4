package ecourse.app.common.console.mySchedules;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import ecourse.scheduleMeeting.application.AcceptRejectMeetingController;
import ecourse.scheduleMeeting.domain.MeetingParticipant;

import java.util.ArrayList;
import java.util.List;

public class AcceptRejectMeetingUI extends AbstractUI {
    private final AcceptRejectMeetingController theController = new AcceptRejectMeetingController();

    @Override
    protected boolean doShow() {
        MeetingParticipant meetingParticipant = selectMeetingInvitation();
        if(meetingParticipant != null){
            changeInviteStatus(meetingParticipant);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Accept or Reject Meeting Invite:";
    }

    private MeetingParticipant selectMeetingInvitation(){
        final List<MeetingParticipant> list = new ArrayList<>();
        final Iterable<MeetingParticipant> iterable = this.theController.listMyInvitations();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Meetings");
        } else {
            var cont = 1;
            System.out.println("SELECT Meeting to Accept or Reject\n");
            System.out.printf("%-6s%-30s%-30s%n", "Nº:", "Meeting Day", "Organizer");
            for (final MeetingParticipant i : iterable) {
                list.add(i);
                System.out.printf("%-6d%-30s%-30s%n", cont, i.meeting().day().getTime(), i.meeting().organizer().username());
                cont++;
            }
            final var option = Console.readInteger("Enter Meeting nº to Accept/Reject or 0 to Exit");
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

    private void changeInviteStatus(MeetingParticipant meeting){
        int choice ;
        do {
            System.out.println("Menu:");
            System.out.println("1. Accept Meeting");
            System.out.println("2. Reject Meeting");
            System.out.println("0. Exit");
            choice = Console.readInteger("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("You selected 'Accept Meeting'");
                    theController.acceptMeeting(meeting);
                    break;
                case 2:
                    System.out.println("You selected 'Reject Meeting'");
                    theController.rejectMeeting(meeting);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 0 to 2.");
                    break;
            }
        } while (choice != 0);

    }
}
