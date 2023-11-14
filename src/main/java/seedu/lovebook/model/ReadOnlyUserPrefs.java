package seedu.lovebook.model;

import java.nio.file.Path;

import seedu.lovebook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getLoveBookFilePath();

}
