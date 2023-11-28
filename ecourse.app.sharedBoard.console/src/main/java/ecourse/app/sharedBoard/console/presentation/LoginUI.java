package ecourse.app.sharedBoard.console.presentation;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.ACK;

public class LoginUI {
    BoardRequestsUI boardRequestsUI = new BoardRequestsUI();
    ParticipantRequestsUI participantRequestsUI = new ParticipantRequestsUI();
    ArchiveRequestsUI archiveRequestsUI = new ArchiveRequestsUI();
    PostItRequestsUI postItRequestsUI = new PostItRequestsUI();

    public void login(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        // Get the username and password from the user.
        SBPClientUI ui = new SBPClientUI();
        String username = ui.getUsername();
        String password = ui.getPassword();

        SBPMessageFormat response = client.sendAuthenticationRequest(username, password);
        if (response.getCode() != ACK.getCode()) {
            errorMessage("\n### Authentication failed! ###\n");
            client.stopConnection();
            return;
        }

        // Display the sub menu
        while (true) {
            warningMessage("\nSelect a menu:");
            System.out.println("""
                    1. Board Menu
                    2. Participant Menu
                    3. Archive Menu
                    4. Post-It Menu
                    5. Logout""");

            // Get the user's choice
            infoMessage("Enter your choice: ");
            int choice = new Scanner(System.in).nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> boardRequestsUI.BoardMenu(client);
                case 2 -> participantRequestsUI.ParticipantMenu(client);
                case 3 -> archiveRequestsUI.ArchiveMenu(client);
                case 4 -> postItRequestsUI.PostItMenu(client);
                case 5 -> {
                    client.stopConnection();
                    return;
                }
                default -> errorMessage("Invalid choice");
            }
        }
    }
}
