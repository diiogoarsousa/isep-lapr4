package ecourse.app.sharedBoard.server.domain;

public class ColorOutputUtils {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String PURPLE = "\033[0;35m";

    /**
     * Prints a message in yellow.
     *
     * @param message The message to print.
     */
    public static void infoMessage(String message) {
        System.out.println(YELLOW + message + RESET);
    }

    /**
     * Prints a message in red.
     *
     * @param message The message to print.
     */
    public static void errorMessage(String message) {
        System.out.println(RED + message + RESET);
    }

    /**
     * Prints a message in green.
     *
     * @param message The message to print.
     */
    public static void successMessage(String message) {
        System.out.println(GREEN + message + RESET);
    }

    /**
     * Prints a message in purple.
     *
     * @param message The message to print.
     */
    public static void warningMessage(String message) {
        System.out.println(PURPLE + message + RESET);
    }
}
