package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getLocalCourseCatalogueFilePath();

    Path getPartnerCourseCatalogueFilePath();

    Path getUniversityCatalogueFilePath();

    Path getNoteCatalogueFilePath();

    Path getMappingCatalogueFilePath();
}
