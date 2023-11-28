package ecourse.app.sharedBoard.console.presentation;

import ecourse.app.sharedBoard.console.controller.ArchiveRequestController;
import ecourse.app.sharedBoard.console.domain.SBPClient;

public class ArchiveRequestsUI extends RequestsUI {
    private final ArchiveRequestController archiveRequestController = new ArchiveRequestController();

    public void ArchiveMenu(SBPClient client) {
        switch (handleChosenOption(new String[]{
                "Archive a board",
                "Restore a board",
                "View archived boards",
        }, "Archive board requests")) {
            case 1 -> archiveRequestController.archiveBoard(client);
            case 2 -> archiveRequestController.restoreBoard(client);
            case 3 -> archiveRequestController.viewArchivedBoards(client);
            case 0 -> {
            }
        }
    }
}
