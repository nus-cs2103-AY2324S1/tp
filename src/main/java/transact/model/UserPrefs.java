package transact.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import transact.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data", "addressbook.json");
    private Path transactionBookFilePath = Paths.get("data", "transactionbook.csv");
    private Path importFilePath = Paths.get("import");
    private Path exportFilePath = Paths.get("export");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setTransactionBookFilePath(newUserPrefs.getTransactionBookFilePath());
        setImportFilePath(newUserPrefs.getImportFilePath());
        setExportFilePath(newUserPrefs.getExportFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getTransactionBookFilePath() {
        return transactionBookFilePath;
    }

    public void setTransactionBookFilePath(Path transactionBookFilePath) {
        requireNonNull(transactionBookFilePath);
        this.transactionBookFilePath = transactionBookFilePath;
    }

    public Path getExportFilePath() {
        return exportFilePath;
    }

    public void setExportFilePath(Path exportFilePath) {
        requireNonNull(exportFilePath);
        this.exportFilePath = exportFilePath;
    }

    public Path getImportFilePath() {
        return importFilePath;
    }

    public void setImportFilePath(Path importFilePath) {
        requireNonNull(importFilePath);
        this.importFilePath = importFilePath;
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
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
