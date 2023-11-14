package networkbook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import networkbook.commons.core.GuiSettings;
import networkbook.commons.exceptions.NullValueException;
import networkbook.commons.util.JsonObject;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs, JsonObject {

    private GuiSettings guiSettings = new GuiSettings();
    private Path networkBookFilePath = Paths.get("data" , "networkbook.json");

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
        setNetworkBookFilePath(newUserPrefs.getNetworkBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        assert guiSettings != null;
        this.guiSettings = guiSettings;
    }

    public Path getNetworkBookFilePath() {
        return networkBookFilePath;
    }

    public void setNetworkBookFilePath(Path networkBookFilePath) {
        assert networkBookFilePath != null;
        this.networkBookFilePath = networkBookFilePath;
    }

    @Override
    public void assertFieldsAreNotNull() throws NullValueException {
        if (guiSettings == null || networkBookFilePath == null) {
            throw new NullValueException();
        }
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
                && networkBookFilePath.equals(otherUserPrefs.networkBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, networkBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + networkBookFilePath);
        return sb.toString();
    }

}
