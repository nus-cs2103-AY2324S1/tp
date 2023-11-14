package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class InternshipUserPrefs implements ReadOnlyInternshipUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path internshipFilePath = Paths.get("data" , "internshipBook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public InternshipUserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public InternshipUserPrefs(ReadOnlyInternshipUserPrefs internshipUserPrefs) {
        this();
        resetData(internshipUserPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyInternshipUserPrefs internshipNewUserPrefs) {
        requireNonNull(internshipNewUserPrefs);
        setGuiSettings(internshipNewUserPrefs.getGuiSettings());
        setInternshipFilePath(internshipNewUserPrefs.getInternshipFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getInternshipFilePath() {
        return internshipFilePath;
    }

    public void setInternshipFilePath(Path internshipFilePath) {
        requireNonNull(internshipFilePath);
        this.internshipFilePath = internshipFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipUserPrefs)) {
            return false;
        }

        InternshipUserPrefs otherUserPrefs = (InternshipUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && internshipFilePath.equals(otherUserPrefs.internshipFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, internshipFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + internshipFilePath);
        return sb.toString();
    }

}
