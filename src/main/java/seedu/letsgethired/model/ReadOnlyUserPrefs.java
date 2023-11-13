package seedu.letsgethired.model;

import java.nio.file.Path;

import seedu.letsgethired.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getInternTrackerFilePath();

}
