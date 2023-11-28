package ecourse.app.common.console.mySchedules;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class MeetingMenu extends Menu {

    private static final String MEETING_TITLE = "Meetings";
    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";


    private static final int EXIT_OPTION = 0;

    private static final int SCHEDULE_A_MEETING_OPTION = 1;
    private static final int CANCEL_A_MEETING_OPTION = 2;
    private static final int MANAGE_MEETINGS_OPTION = 3;
    private static final int LIST_PARTICIPANTS_MEETING_OPTION = 4;

    public MeetingMenu() {
        super(MEETING_TITLE);
        buildMeetingsMenu();
    }

    private void buildMeetingsMenu() {
        addItem(SCHEDULE_A_MEETING_OPTION, "Schedule a Meeting", () -> new ScheduleMeetingUI().show());
        addItem(CANCEL_A_MEETING_OPTION, "Cancel a Meeting", () -> new CancelMeetingUI().show());
        addItem(MANAGE_MEETINGS_OPTION, "Accept or Reject Meetings", () -> new AcceptRejectMeetingUI().show());
        addItem(LIST_PARTICIPANTS_MEETING_OPTION, "List Meeting Participants",() -> new ListMeetingParticipantsUI().show());
        addItem(MenuItem.separator(SEPARATOR_LABEL));
        addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    }
}
