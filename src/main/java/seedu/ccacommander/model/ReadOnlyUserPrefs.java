package seedu.ccacommander.model;

import java.nio.file.Path;

import seedu.ccacommander.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getCcaCommanderFilePath();

}
