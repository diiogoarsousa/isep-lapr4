package ecourse.app.sharedBoard.console.presentation;

import ecourse.app.sharedBoard.console.controller.BoardRequestController;
import ecourse.app.sharedBoard.console.domain.SBPClient;

public class BoardRequestsUI extends RequestsUI {
    private final BoardRequestController boardRequestController = new BoardRequestController();

    public void BoardMenu(SBPClient client) {
        switch (handleChosenOption(new String[]{
                "Create a board",
                "Delete a board",
                "List boards",
        }, "Board requests")) {
            case 1 -> boardRequestController.createBoard(client);
            case 2 -> boardRequestController.deleteBoard(client);
            case 3 -> boardRequestController.listBoards(client);
            case 0 -> {
            }
        }

    }
}
