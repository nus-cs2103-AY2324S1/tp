package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path prescriptionListFilePath = Paths.get("data" , "prescriptionList.json");
    private Path completedPrescriptionListFilePath = Paths.get("data", "completedPrescriptionList.json");
    private LocalDate storedDate = LocalDate.now();

    /**
     * Creates a {@code UserPrefsPrescriptionPrescription} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefsPrescription} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefsPrescription} with {@code newUserPrefsPrescription}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefsPrescription) {
        requireNonNull(newUserPrefsPrescription);
        setGuiSettings(newUserPrefsPrescription.getGuiSettings());
        setPrescriptionListFilePath(newUserPrefsPrescription.getPrescriptionListFilePath());
        setCompletedPrescriptionListFilePath(newUserPrefsPrescription.getCompletedPrescriptionListFilePath());
        setStoredDate(newUserPrefsPrescription.getStoredDate());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPrescriptionListFilePath() {
        return prescriptionListFilePath;
    }

    public void setPrescriptionListFilePath(Path prescriptionListFilePath) {
        requireNonNull(prescriptionListFilePath);
        this.prescriptionListFilePath = prescriptionListFilePath;
    }

    public Path getCompletedPrescriptionListFilePath() {
        return completedPrescriptionListFilePath;
    }

    public void setCompletedPrescriptionListFilePath(Path completedPrescriptionListFilePath) {
        requireNonNull(completedPrescriptionListFilePath);
        this.completedPrescriptionListFilePath = completedPrescriptionListFilePath;
    }

    public LocalDate getStoredDate() {
        return storedDate;
    }

    public void setStoredDate(LocalDate storedDate) {
        this.storedDate = storedDate;
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

        UserPrefs otherUserPrefsPrescription = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefsPrescription.guiSettings)
                && prescriptionListFilePath.equals(otherUserPrefsPrescription.prescriptionListFilePath)
                && completedPrescriptionListFilePath.equals(
                otherUserPrefsPrescription.completedPrescriptionListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, prescriptionListFilePath, completedPrescriptionListFilePath, storedDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal prescription list data file location : " + prescriptionListFilePath);
        sb.append("\nLocal completed prescription list data file location : " + completedPrescriptionListFilePath);
        sb.append("\nStored date : " + storedDate);
        return sb.toString();
    }

}
