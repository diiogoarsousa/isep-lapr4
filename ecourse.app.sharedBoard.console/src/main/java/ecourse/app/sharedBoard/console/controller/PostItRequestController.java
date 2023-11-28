package ecourse.app.sharedBoard.console.controller;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;

import java.util.Arrays;
import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.*;

public class PostItRequestController {

    /**
     * Create a post-it.
     *
     * @param client SBPClient.
     */
    public void createPostIt(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendCreatePostItRequest();

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
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        infoMessage("Enter post-it title: ");
        String title = new Scanner(System.in).nextLine();
        infoMessage("Enter post-it content: ");
        String content = new Scanner(System.in).nextLine();
        infoMessage("Enter post-it row: ");
        int row = new Scanner(System.in).nextInt();
        infoMessage("Enter post-it column: ");
        int column = new Scanner(System.in).nextInt();

        response = client.sendPostItRequest(title, content, row, column);

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Post-it creation failed! ###\n");
        } else {
            successMessage("\n### Post-it created successfully! ###\n");
        }

        client.stopConnection();
    }

    public void changePostIt(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendChangePostItRequest();

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

        //this response has the postits
        response = client.sendRequest(SEL_BOARD.getCode(), board_id);

        if (response == null) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        if (response.getCode() != CHG_POSTIT.getCode()) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        if (response.getData().length == 0) {
            errorMessage("\n### You don't own any post-its in this board! ###\n");
            client.stopConnection();
            return;
        }

        String[] postits = new String(response.getData()).split("\0");

        if (postits.length == 0) {
            errorMessage("This board doesn't have any post-its!");
            return;
        }

        // Must be a pair id + title
        if (postits.length % 2 != 0) {
            errorMessage("\n### Invalid response from server! ###\n");
            client.stopConnection();
            return;
        }

        String[] postit_ids = new String[postits.length / 2];
        String[] postit_titles = new String[postits.length / 2];

        for (int i = 0; i + 1 < postits.length; i += 2) {
            postit_ids[i / 2] = postits[i];
            postit_titles[i / 2] = postits[i + 1];
        }

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < postit_ids.length; i++) {
            sb1.append(i + 1)
                    .append(". ")
                    .append(postit_titles[i])
                    .append("\n");
        }
        System.out.print(sb1);
        errorMessage("0. Cancel");
        infoMessage("Enter your choice: ");
        int choice1 = new Scanner(System.in).nextInt();
        System.out.println();

        if (choice1 == 0) {
            client.stopConnection();
            return;
        }

        if (choice1 < 0 || choice1 > postit_ids.length) {
            errorMessage("\n### Invalid choice! ###\n");
            client.stopConnection();
            return;
        }

        byte[] postit_id = postit_ids[choice1 - 1].getBytes();
        postit_id = Arrays.copyOf(postit_id, postit_id.length + 1);

        infoMessage("What should the new content be? ");
        String newContent = new Scanner(System.in).nextLine();

        byte[] newContentBytes = newContent.getBytes();
        newContentBytes = Arrays.copyOf(newContentBytes, newContentBytes.length + 1);
        byte[] postit_idContent = new byte[newContentBytes.length + postit_id.length + 1];

        System.arraycopy(postit_id, 0, postit_idContent, 0, postit_id.length);
        System.arraycopy(newContentBytes, 0, postit_idContent, postit_id.length, newContentBytes.length);


        response = client.sendRequest(CHG_POSTIT.getCode(), postit_idContent);

        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Post-it change failed! ###\n");
        } else {
            successMessage("\n### Post-it changed successfully! ###\n");
        }

        client.stopConnection();
    }

    public void undoChange(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendUndoPostItChangeRequest();
        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Undo post-it change failed! ###\n");
        } else {
            successMessage("\n### Post-it change undone successfully! ###\n");
        }

        client.stopConnection();
    }

    public void viewHistory(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendViewHistoryRequest();
        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### View history failed! ###\n");
        } else {
            successMessage("\n### History viewed successfully! ###\n");
        }

        client.stopConnection();
    }

    public void viewUpdates(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendViewUpdatesRequest();
        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### View updates failed! ###\n");
        } else {
            successMessage("\n### Updates viewed successfully! ###\n");
        }

        client.stopConnection();
    }
}
