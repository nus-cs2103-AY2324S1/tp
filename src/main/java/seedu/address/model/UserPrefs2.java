package seedu.address.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import seedu.address.commons.core.GuiSettings;

import static java.util.Objects.requireNonNull;

/**
 * Represents User's preferences.
 */
public class UserPrefs2 implements ReadOnlyUserPrefs2 {

    private GuiSettings guiSettings = new GuiSettings();
    private Path deckFilePath = Paths.get("data" , "deck.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs2() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs2(ReadOnlyUserPrefs2 userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs2 newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setDeckFilePath(newUserPrefs.getDeckFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getDeckFilePath() {
        return deckFilePath;
    }

    public void setDeckFilePath(Path deckFilePath) {
        requireNonNull(deckFilePath);
        this.deckFilePath = deckFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs2)) {
            return false;
        }

        UserPrefs2 otherUserPrefs = (UserPrefs2) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && deckFilePath.equals(otherUserPrefs.deckFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, deckFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + deckFilePath);
        return sb.toString();
    }

}
