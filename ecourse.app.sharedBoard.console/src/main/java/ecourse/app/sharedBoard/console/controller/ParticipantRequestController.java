package ecourse.app.sharedBoard.console.controller;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.*;

public class ParticipantRequestController {

    /**
     * Share a board with another user.
     *
     * @param client The client to use.
     */
    public void shareBoard(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendShareBoardRequest();

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

        if (response.getCode() != LIST_PART.getCode()) {
            errorMessage("\n### There are no available users to share the board with. ###\n");
            client.stopConnection();
            return;
        }

        var availableUsers = new String(response.getData()).split("\0");

        if (availableUsers.length == 0) {
            errorMessage("There are no available users to share the board with.");
            return;
        }

        System.out.println("Choose the user you want to share the board with:");
        for (int i = 0; i < availableUsers.length; i++) {
            System.out.println(i + 1 + ". " + availableUsers[i]);
        }

        errorMessage("0. Cancel");
        infoMessage("Enter your choice: ");
        choice = new Scanner(System.in).nextInt();
        System.out.println();

        if (choice == 0) {
            client.stopConnection();
            return;
        }

        if (choice < 0 || choice > availableUsers.length) {
            errorMessage("\n### Invalid choice! ###\n");
            client.stopConnection();
            return;
        }

        byte[] username = availableUsers[choice - 1].getBytes();

        response = client.sendRequest(SEL_PART.getCode(), username);

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Board sharing failed! ###\n");
        } else {
            successMessage("\n### Board shared successfully! ###\n");
        }

        client.stopConnection();
    }

    /**
     * Remove a participant from a board.
     *
     * @param client The client to use.
     */
    public void removeParticipant(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.removeBoardParticipantRequest();

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

        if (response.getCode() != LIST_PART.getCode()) {
            errorMessage("\n### There are no available users to remove from the board. ###\n");
            client.stopConnection();
            return;
        }

        var availableUsers = new String(response.getData()).split("\0");

        if (availableUsers.length == 0) {
            errorMessage("There are no available users to remove from the board.");
            return;
        }

        System.out.println("Choose the user you want to remove from the board:");
        for (int i = 0; i < availableUsers.length; i++) {
            System.out.println(i + 1 + ". " + availableUsers[i]);
        }

        errorMessage("0. Cancel");
        infoMessage("Enter your choice: ");
        choice = new Scanner(System.in).nextInt();
        System.out.println();

        if (choice == 0) {
            client.stopConnection();
            return;
        }

        if (choice < 0 || choice > availableUsers.length) {
            errorMessage("\n### Invalid choice! ###\n");
            client.stopConnection();
            return;
        }

        byte[] username = availableUsers[choice - 1].getBytes();

        response = client.sendRequest(SEL_PART.getCode(), username);

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Board participant removal failed! ###\n");
        } else {
            successMessage("\n### Board participant removed successfully! ###\n");
        }

        client.stopConnection();
    }

    /**
     * Toggle the write permissions of a participant.
     *
     * @param client The client to use.
     */
    public void toggleWritePermissions(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }
        SBPMessageFormat response = client.toggleCanWriteAccessParticipantRequest();

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

        if (response.getCode() != LIST_PART.getCode()) {
            errorMessage("\n### There are no available users in the board. ###\n");
            client.stopConnection();
            return;
        }

        var availableUsers = new String(response.getData()).split("\0");

        if (availableUsers.length == 0) {
            errorMessage("There are no participants in the board.");
            return;
        }

        System.out.println("Choose the user you want to toggle permissions from the board:");
        for (int i = 0; i < availableUsers.length; i++) {
            System.out.println(i + 1 + ". " + availableUsers[i]);
        }

        errorMessage("0. Cancel");
        infoMessage("Enter your choice: ");
        choice = new Scanner(System.in).nextInt();
        System.out.println();

        if (choice == 0) {
            client.stopConnection();
            return;
        }

        if (choice < 0 || choice > availableUsers.length) {
            errorMessage("\n### Invalid choice! ###\n");
            client.stopConnection();
            return;
        }

        byte[] username = availableUsers[choice - 1].getBytes();

        response = client.sendRequest(TOGGLE_PERM_PART.getCode(), username);

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Board participant permission toggle failed! ###\n");
        } else {
            successMessage("\n### Board participant permission toggled successfully! ###\n");
        }

        client.stopConnection();

    }

    /**
     * View the participants of a board.
     *
     * @param client The client to use.
     */
    public void viewBoardParticipants(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }
        SBPMessageFormat response = client.viewBoardParticipantsRequest();

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

        if (response.getCode() != LIST_PART.getCode()) {
            errorMessage("\n### There are no available users in the board. ###\n");
            client.stopConnection();
            return;
        }

        var availableUsers = new String(response.getData()).split("\0");

        if (availableUsers.length == 0) {
            errorMessage("There are no participants in the board.");
            return;
        }

        System.out.println("Participants in the board:");
        for (int i = 0; i < availableUsers.length; i++) {
            System.out.println(i + 1 + ". " + availableUsers[i]);
        }
        infoMessage("Press any key to continue...");
        new Scanner(System.in).nextLine();
        System.out.println();

        client.stopConnection();
    }
}
