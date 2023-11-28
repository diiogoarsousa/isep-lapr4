package ecourse.app.sharedBoard.console.presentation;

import ecourse.app.sharedBoard.console.controller.PostItRequestController;
import ecourse.app.sharedBoard.console.domain.SBPClient;

public class PostItRequestsUI extends RequestsUI {
    private final PostItRequestController postItRequestController = new PostItRequestController();

    public void PostItMenu(SBPClient client) {
        switch (handleChosenOption(new String[]{
                "Create a Post-It",
                "Change a Post-It",
                "Undo change",
                "View updates",
                "View history",
        }, "Archive board requests")) {
            case 1 -> postItRequestController.createPostIt(client);
            case 2 -> postItRequestController.changePostIt(client);
//            case 3 -> postItRequestController.undoChange(client);
//            case 4 -> postItRequestController.viewUpdates(client);
//            case 5 -> postItRequestController.viewHistory(client);
            case 0 -> {
            }
        }
    }
}
