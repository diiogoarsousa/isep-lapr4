package ecourse.app.sharedBoard.console.presentation;

import java.util.Scanner;

import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.infoMessage;
import static ecourse.app.sharedBoard.console.domain.ColorOutputUtils.warningMessage;

public abstract class RequestsUI {

    private void displayMenu(String[] options, String title) {
        warningMessage(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println(" " + (i + 1) + ". " + options[i]);
        }
        infoMessage(" 0. Go back");
        System.out.println();
    }

    int handleChosenOption(String[] options, String title) {
        while (true) {
            displayMenu(options, title);

            // Get the user's choice
            infoMessage("Enter your choice: ");
            int choice = new Scanner(System.in).nextInt();
            System.out.println();

            if (choice == 0) {
                return 0;
            } else if (choice > 0 && choice <= options.length) {
                return choice;
            } else {
                warningMessage("Invalid choice. Please try again.");
            }
        }
    }
}
