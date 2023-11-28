package ecourse.app.sharedBoard.console.controller;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.*;

public class ArchiveRequestController {

    public void archiveBoard(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendArchiveBoardRequest();

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

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Archive board failed! ###\n");
        } else {
            successMessage("\n### Board archived successfully! ###\n");
        }

        client.stopConnection();
    }

    public void restoreBoard(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendRestoreBoardRequest();

        String[] archivedBoards = new String(response.getData()).split("\0");
        if (archivedBoards.length == 0) {
            System.out.println("You don't own any archived boards.");
            return;
        }

        // Must be a pair id + title
        if (archivedBoards.length % 2 != 0) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        String[] board_ids = new String[archivedBoards.length / 2];
        String[] board_titles = new String[archivedBoards.length / 2];

        for (int i = 0; i + 1 < archivedBoards.length; i += 2) {
            board_ids[i / 2] = archivedBoards[i];
            board_titles[i / 2] = archivedBoards[i + 1];
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

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Unarchive board failed! ###\n");
        } else {
            successMessage("\n### Board unarchived successfully! ###\n");
        }

        client.stopConnection();
    }

    public void viewArchivedBoards(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendViewArchivedBoardsRequest();
        if (response.getCode() == ERR.getCode()) {
            errorMessage("\nYou don't own any archived boards.\n");
            client.stopConnection();
            return;
        }

        String[] archivedBoards = new String(response.getData()).split("\0");

        if (archivedBoards.length == 0) {
            System.out.println("You don't own any archived boards.");
            return;
        }

        // Must be a pair id + title
        if (archivedBoards.length % 2 != 0) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        String[] board_ids = new String[archivedBoards.length / 2];
        String[] board_titles = new String[archivedBoards.length / 2];

        for (int i = 0; i + 1 < archivedBoards.length; i += 2) {
            board_ids[i / 2] = archivedBoards[i];
            board_titles[i / 2] = archivedBoards[i + 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board_ids.length; i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(board_titles[i])
                    .append("\n");
        }
        System.out.print(sb);
        infoMessage("Type any key to continue: ");
        new Scanner(System.in).nextLine();
        System.out.println();

        client.stopConnection();
    }
}
