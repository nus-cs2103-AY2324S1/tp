package seedu.address.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.util.ToStringBuilder;



/**
 * Mutable settings, both readable and writable.
 */
public class Settings implements ReadOnlySettings {
    private Path contactsPath = Paths.get("data" , "contacts.json");

    private GuiSettings guiSettings = new GuiSettings();

    /**
     * Constructs with default values.
     */
    public Settings() {}

    /**
     * Constructs a clone of the specified {@link ReadOnlySettings}.
     */
    public Settings(ReadOnlySettings newSettings) {
        overwrite(newSettings);
    }

    @Override
    public Path getContactsPath() {
        return this.contactsPath;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return this.guiSettings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("contactsPath", this.contactsPath)
                .add("guiSettings", this.guiSettings)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof Settings)) {
            return false;
        }
        Settings otherSettings = (Settings)other;

        return (
            this.contactsPath.equals(otherSettings.contactsPath)
            && this.guiSettings.equals(otherSettings.guiSettings)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactsPath, guiSettings);
    }

    public void setContactsPath(Path newPath) {
        this.contactsPath = newPath;
    }

    public void setGuiSettings(GuiSettings newGuiSettings) {
        this.guiSettings = newGuiSettings;
    }

    /**
     * Overwrites current settings with the specified {@link ReadOnlySettings}.
     */
    public void overwrite(ReadOnlySettings newSettings) {
        setContactsPath(newSettings.getContactsPath());

        setGuiSettings(newSettings.getGuiSettings());
    }
}
