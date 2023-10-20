package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;



/**
 * Read-only view of settings.
 */
public interface ReadOnlySettings {
    public Path getContactsPath();

    public GuiSettings getGuiSettings();
}
