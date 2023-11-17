package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface AnimalReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAnimalCatalogFilePath();

}
