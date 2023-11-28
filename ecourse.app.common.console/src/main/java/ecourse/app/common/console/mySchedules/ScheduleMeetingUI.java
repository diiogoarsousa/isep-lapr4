package ecourse.app.common.console.mySchedules;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.time.util.Calendars;
import eapli.framework.time.util.CurrentTimeCalendars;
import ecourse.scheduleMeeting.application.MyMeetingController;
import ecourse.scheduleMeeting.domain.Meeting;
import ecourse.scheduleMeeting.domain.MeetingDate;
import ecourse.scheduleMeeting.domain.MeetingDuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class ScheduleMeetingUI extends AbstractUI {
    private final MyMeetingController theController = new MyMeetingController();

    @Override
    protected boolean doShow() {
        final Calendar theDay = selectValidDate();
        final String[] validHour = selectValidHour();
        theDay.set(Calendar.HOUR_OF_DAY, Integer.parseInt(validHour[0]));
        theDay.set(Calendar.MINUTE, Integer.parseInt(validHour[1]));
        final MeetingDuration duration = selectValidDuration();
        final Optional<Meeting> meeting = theController.myMeetingFor(new MeetingDate(theDay), duration);
        meeting.ifPresentOrElse(
                b -> System.out.println("You already have a meeting for this date."),
                () -> scheduleMeeting(duration, new MeetingDate(theDay), selectParticipants(theDay, duration)));
        return false;
    }

    // menu title
    @Override
    public String headline() {
        return "Schedule a Meeting";
    }

    // schedule the Meeting and send to controller to save in repository
    private void scheduleMeeting(final MeetingDuration duration, final MeetingDate date, final List<SystemUser> participants) {
        try {
            final Meeting meeting = theController.scheduleMeeting(duration, date, participants);
            if (meeting != null) {
                System.out.println("SUCCESS. Your meeting is " + meeting.identity());
            } else {
                System.out.println("It was not possible to schedule your meeting");
            }
        } catch (final ConcurrencyException e) {
            System.out.println("Problems with Data integrity");
        }
    }

    // this method gives a list of participants selected by user
    private List<SystemUser> selectParticipants(final Calendar theDay, final MeetingDuration duration) {
        final List<SystemUser> invited = new ArrayList<>();
        final List<SystemUser> list = (List<SystemUser>) this.theController.activeUsers();
        var option = 0;
        if (!list.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            do {
                var cont = 1;
                System.out.println("Select a User to invite to the Meeting\n");
                System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");
                for (final SystemUser user : list) {
                    System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(), user.name().firstName(), user.name().lastName());
                    cont++;
                }
                option = Console.readInteger("Enter user nº to invite or 0 to finish ");
                if (option == 0) {
                    System.out.println("User's invited");
                    return invited;
                } else {
                    try {
                        final Optional<Meeting> meeting = theController.inviteParticipantFor(new MeetingDate(theDay), list.get(option - 1), duration);
                        int finalOption = option;
                        meeting.ifPresentOrElse(
                                b -> System.out.println("You already have a meeting for this date."),
                                () -> {
                                    invited.add(list.get(finalOption - 1));
                                    list.remove(list.get(finalOption - 1));
                                })
                        ;
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING: That entity has already been changed or deleted since you last read it");
                    }
                }
            } while (option != 0);
        }
        return null;
    }

    // read the date to schedule meeting inserted by user
    private Calendar selectValidDate() {
        Calendar theDay;
        System.out.println("Please enter a date to Schedule the Meeting");
        do {
            theDay = Console.readCalendar("Meeting Date (yyyy-MM-dd):", "yyyy-MM-dd");
            System.out.println("TRACE: " + Calendars.format(theDay));
        } while (!theDay.after(CurrentTimeCalendars.now()));
        System.out.println("--------------");
        return theDay;
    }

    // read the hour to schedule meeting inserted by user
    private String[] selectValidHour() {
        String horaString;
        int hour, minute;
        do {
            horaString = Console.readLine("Please enter a Hour (hh:mm): ");
            String[] partesHora = horaString.split(":");
            hour = Integer.parseInt(partesHora[0]);
            minute = Integer.parseInt(partesHora[1]);
            if (hour < 0 || hour > 23)
                System.out.printf("Hora inválida: %d\n", hour);
            if (minute < 0 || minute > 59)
                System.out.printf("Minuto inválido: %d\n", minute);
        }
        while (hour < 0 || hour > 23 | minute < 0 || minute > 59);
        System.out.println("--------------");
        return horaString.split(":");
    }

    // read the valid duration to meeting, inserted by user
    private MeetingDuration selectValidDuration() {
        int duration;
        do {
            System.out.println("Please enter meeting duration");
            duration = Console.readInteger("(Minutes):");
            if (duration <= 0)
                System.out.printf("Invalid Duration: %d\n", duration);
        }
        while (duration <= 0);
        System.out.println("--------------");
        return new MeetingDuration(duration);
    }
}
