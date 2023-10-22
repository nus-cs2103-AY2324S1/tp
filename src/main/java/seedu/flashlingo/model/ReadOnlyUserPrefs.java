package seedu.flashlingo.model;

import java.nio.file.Path;

import seedu.flashlingo.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFlashlingoFilePath();

    String getTheme();

}
