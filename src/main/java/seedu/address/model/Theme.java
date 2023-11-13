package seedu.address.model;

/**
 * Encapsulates the themes that the application can take on.
 */
public enum Theme {
    DARK, LIGHT;

    public static final String MESSAGE_CONSTRAINTS = "Theme can only be 'dark' or 'light' (not case sensitive)";
    public static Theme getThemeValue(String theme) {
        return Theme.valueOf(theme.toUpperCase());
    }
}
