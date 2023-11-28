package ecourse.app.sharedBoard.console.controller;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;
import ecourse.app.sharedBoard.console.presentation.BoardUtils;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.ACK;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.SEL_BOARD;

public class BoardRequestController {

    /**
     * Create a board.
     *
     * @param client SBPClient.
     */
    public void createBoard(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        BoardUtils ui = new BoardUtils();
        String title;
        title = ui.getTitle();

        int rows;
        int cols;
        try {
            rows = ui.getRows();
            cols = ui.getColumns();
        } catch (NumberFormatException e) {
            errorMessage("\n### Invalid input! Please enter a valid number. ###\n");
            client.stopConnection();
            return;
        }

        SBPMessageFormat response = client.sendCreateBoardRequest(title, rows, cols);

        if (response.getCode() == ACK.getCode()) {
            successMessage("\n### Board created successfully! ###\n");
        } else {
            errorMessage("\n### Board creation failed! ###\n");
        }
        client.stopConnection();
    }


    public void deleteBoard(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendDeleteBoardRequest();

        String[] ownedBoards = new String(response.getData()).split("\0");
        if (ownedBoards.length == 0) {
            System.out.println("You don't own any boards.");
            return;
        }

        // Must be a pair id + title
        if (ownedBoards.length % 2 != 0) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        String[] board_ids = new String[ownedBoards.length / 2];
        String[] board_titles = new String[ownedBoards.length / 2];

        for (int i = 0; i + 1 < ownedBoards.length; i += 2) {
            board_ids[i / 2] = ownedBoards[i];
            board_titles[i / 2] = ownedBoards[i + 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board_ids.length; i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(board_titles[i])
                    .append("\n");
        }
        System.out.print(sb);
        errorMessage("0. Cancel");
        infoMessage("Enter your choice: ");
        int choice = new Scanner(System.in).nextInt();
        System.out.println();

        if (choice == 0) {
            client.stopConnection();
            return;
        }

        if (choice < 0 || choice > board_ids.length) {
            errorMessage("\n### Invalid choice! ###\n");
            client.stopConnection();
            return;
        }
        byte[] board_id = board_ids[choice - 1].getBytes();

        response = client.sendRequest(SEL_BOARD.getCode(), board_id);

        if (response.getCode() == ACK.getCode()) {
            successMessage("\n### Board deleted successfully! ###\n");
        } else {
            errorMessage("\n### Board deletion failed! ###\n");
        }
    }

    /**
     * List boards.
     *
     * @param client SBPClient.
     */
    public void listBoards(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendListBoardsRequest();

        String[] boards = new String(response.getData()).split("\0");
        if (boards.length == 0) {
            System.out.println("No boards available.");
            return;
        }

        // Must be a pair id + title
        if (boards.length % 2 != 0) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        String[] board_ids = new String[boards.length / 2];
        String[] board_titles = new String[boards.length / 2];

        for (int i = 0; i + 1 < boards.length; i += 2) {
            board_ids[i / 2] = boards[i];
            board_titles[i / 2] = boards[i + 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board_ids.length; i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(board_titles[i])
                    .append("\n");
        }
        System.out.print(sb);
        infoMessage("Press any key to continue...");
        new Scanner(System.in).nextLine();
        client.stopConnection();
    }
}
