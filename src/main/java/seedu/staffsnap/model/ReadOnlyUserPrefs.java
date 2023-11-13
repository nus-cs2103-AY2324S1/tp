package seedu.staffsnap.model;

import java.nio.file.Path;

import seedu.staffsnap.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getApplicantBookFilePath();

}
