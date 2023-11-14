package networkbook.model;

import java.nio.file.Path;

import networkbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getNetworkBookFilePath();

}
