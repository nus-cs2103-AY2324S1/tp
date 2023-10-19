package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.FilterSettings;
import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    FilterSettings getFilterSettings();

    Path getAddressBookFilePath();

}
