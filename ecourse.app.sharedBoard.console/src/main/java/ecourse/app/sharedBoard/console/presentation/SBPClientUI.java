package ecourse.app.sharedBoard.console.presentation;

import java.util.Scanner;

public class SBPClientUI {
    private final Scanner scanner;

    public SBPClientUI() {
        this.scanner = new Scanner(System.in);
    }

    public String getUsername() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }
}