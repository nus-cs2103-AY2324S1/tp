package seedu.lovebook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.lovebook.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path loveBookFilePath = Paths.get("data" , "LoveBook.json");
    private Path datePrefsFilePath = Paths.get("data", "Preferences.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setLoveBookFilePath(newUserPrefs.getLoveBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getLoveBookFilePath() {
        return loveBookFilePath;
    }

    public Path getDatePrefsFilePath() {
        return datePrefsFilePath;
    }

    public void setLoveBookFilePath(Path loveBookFilePath) {
        requireNonNull(loveBookFilePath);
        this.loveBookFilePath = loveBookFilePath;
    }

    public void setDatePrefsFilePath(Path datePrefsFilePath) {
        requireNonNull(datePrefsFilePath);
        this.datePrefsFilePath = datePrefsFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && loveBookFilePath.equals(otherUserPrefs.loveBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, loveBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + loveBookFilePath);
        return sb.toString();
    }

}
