package ecourse.app.sharedBoard.console;

import ecourse.app.sharedBoard.console.domain.SBPClient;
import ecourse.app.sharedBoard.console.domain.SBPMessageFormat;
import ecourse.app.sharedBoard.console.presentation.BoardUtils;
import ecourse.app.sharedBoard.console.presentation.LoginUI;
import ecourse.app.sharedBoard.console.presentation.SBPClientUI;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.*;
import static ecourse.app.sharedBoard.console.domain.SBPCodeEnum.*;

public class Client {
    public static void main(String[] args) {
        SBPClient client = new SBPClient();
        Scanner scanner = new Scanner(System.in);
        LoginUI loginUI = new LoginUI();

        while (true) {
            // Display the main menu
            warningMessage("Main Menu");
            System.out.println("""
                    1. Test server communication
                    2. Login
                    3. Disconnection request
                    4. Exit""");

            // Get the user's choice
            infoMessage("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> testServerCommunication(client);
                case 2 -> loginUI.login(client);
                case 3 -> disconnect(client);
                case 4 -> System.exit(0);
                default -> errorMessage("Invalid choice");
            }
        }
    }

    private static void testServerCommunication(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendCommunicationsTestRequest();

        if (response != null && response.getCode() == ACK.getCode()) {
            successMessage("\n### The server is running! ###\n");
            client.stopConnection();
        } else {
            errorMessage("\n### Couldn't reach the server! ###\n");
        }
    }

    private static void disconnect(SBPClient client) {
        if (!client.startConnection()) {
            return;
        }

        SBPMessageFormat response = client.sendEndOfSessionRequest();

        if (response != null && response.getCode() == ACK.getCode()) {
            successMessage("\n### End of session successful! ###\n");
            client.stopConnection();
        } else {
            errorMessage("\n### Couldn't reach the server! ###\n");
        }
    }
}
