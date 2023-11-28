package ecourse.app.sharedBoard.console.presentation;

import java.util.Scanner;

public class BoardUtils {

    private final Scanner scanner;

    public BoardUtils() {
        this.scanner = new Scanner(System.in);
    }

    public String getTitle() {
        System.out.print("Enter the title of the board: ");
        return scanner.nextLine();
    }

    public int getRows() {
        System.out.print("Enter the number of rows: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getColumns() {
        System.out.print("Enter the number of columns: ");
        return Integer.parseInt(scanner.nextLine());
    }
}