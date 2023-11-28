package ecourse;

public class Application {

    public static final String VERSION = "1.0.0";
    public static final String COPYRIGHT = "Group 1";

    private static final AppSettings SETTINGS = new AppSettings();

    private Application() {
        // private visibility to ensure singleton & utility
    }

    public static AppSettings settings() {
        return SETTINGS;
    }
}
