package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ShortcutSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

    private ShortcutSettings shortcutSettings = new ShortcutSettings();

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
        setShortcutSettings(newUserPrefs.getShortcutSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public ShortcutSettings getShortcutSettings() { return shortcutSettings; }

    public void setShortcutSettings(ShortcutSettings shortcutSettings) {
        requireNonNull(shortcutSettings);
        this.shortcutSettings = shortcutSettings;
    }

    /**
     * Registers the new mapping.
     * @param alias New alias for an existing command word
     * @param commandWord The command word to be mapped to the alias
     * @return The alias' previously mapped command word if any, else returns null.
     */
    public String registerShortcut(String alias, String commandWord) {
        return shortcutSettings.registerShortcut(alias, commandWord);
    }

    /**
     * Checks if the alias has a mapping registered and returns it if found.
     * @param alias Query alias
     * @return Registered command word if found, else given alias
     */
    public String getShortcut(String alias) {
        return shortcutSettings.getShortcut(alias);
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
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
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && shortcutSettings.equals(otherUserPrefs.shortcutSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("Shortcut Settings : " + shortcutSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
