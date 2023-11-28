package ecourse.app.sharedBoard.console.presentation;

import ecourse.app.sharedBoard.console.controller.ParticipantRequestController;
import ecourse.app.sharedBoard.console.domain.SBPClient;

public class ParticipantRequestsUI extends RequestsUI {
    private final ParticipantRequestController participantRequestController = new ParticipantRequestController();

    public void ParticipantMenu(SBPClient client) {
        switch (handleChosenOption(new String[]{
                "Share a board",
                "Remove a board participant",
                "Toggle write permissions to a board participant",
                "View board participants",
        }, "Participant requests")) {
            case 1 -> participantRequestController.shareBoard(client);
            case 2 -> participantRequestController.removeParticipant(client);
            case 3 -> participantRequestController.toggleWritePermissions(client);
            case 4 -> participantRequestController.viewBoardParticipants(client);
            case 0 -> {
                return;
            }
        }

    }
}
