package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path localCourseCatalogueFilePath = Paths.get("data", "localcoursecatalogue.json");
    private Path universitiesCatalogueFilePath = Paths.get("data", "universitycatalogue.json");
    private Path parterCourseCatalogueFilePath = Paths.get("data", "partnercoursecatalogue.json");
    private Path noteCatalogueFilePath = Paths.get("data", "note.json");
    private Path mappingCatalogueFilePath = Paths.get("data", "mappingcatalogue.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setLocalCourseCatalogueFilePath(newUserPrefs.getLocalCourseCatalogueFilePath());
        setPartnerCourseCatalogueFilePath(newUserPrefs.getPartnerCourseCatalogueFilePath());
        setUniversitiesCatalogueFilePath(newUserPrefs.getUniversityCatalogueFilePath());
        setNoteCatalogueFilePath(newUserPrefs.getNoteCatalogueFilePath());
        setMappingCatalogueFilePath(newUserPrefs.getMappingCatalogueFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getLocalCourseCatalogueFilePath() {
        return localCourseCatalogueFilePath;
    }

    public void setUniversitiesCatalogueFilePath(Path universitiesCatalogueFilePath) {
        requireNonNull(universitiesCatalogueFilePath);
        this.universitiesCatalogueFilePath = universitiesCatalogueFilePath;
    }

    public Path getUniversityCatalogueFilePath() {
        return universitiesCatalogueFilePath;
    }

    public void setLocalCourseCatalogueFilePath(Path localCourseCatalogueFilePath) {
        requireNonNull(localCourseCatalogueFilePath);
        this.localCourseCatalogueFilePath = localCourseCatalogueFilePath;
    }

    public Path getPartnerCourseCatalogueFilePath() {
        return parterCourseCatalogueFilePath;
    }

    public void setPartnerCourseCatalogueFilePath(Path parterCourseCatalogueFilePath) {
        requireNonNull(parterCourseCatalogueFilePath);
        this.parterCourseCatalogueFilePath = parterCourseCatalogueFilePath;
    }

    public Path getNoteCatalogueFilePath() {
        return noteCatalogueFilePath;
    }

    public void setNoteCatalogueFilePath(Path noteCatalogueFilePath) {
        requireNonNull(noteCatalogueFilePath);
        this.noteCatalogueFilePath = noteCatalogueFilePath;
    }

    public Path getMappingCatalogueFilePath() {
        return mappingCatalogueFilePath;
    }

    public void setMappingCatalogueFilePath(Path mappingCatalogueFilePath) {
        requireNonNull(mappingCatalogueFilePath);
        this.mappingCatalogueFilePath = mappingCatalogueFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && localCourseCatalogueFilePath.equals(otherUserPrefs.localCourseCatalogueFilePath)
                && parterCourseCatalogueFilePath.equals(otherUserPrefs.parterCourseCatalogueFilePath)
                && noteCatalogueFilePath.equals(otherUserPrefs.noteCatalogueFilePath)
                && universitiesCatalogueFilePath.equals(otherUserPrefs.universitiesCatalogueFilePath)
                && mappingCatalogueFilePath.equals(otherUserPrefs.mappingCatalogueFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, localCourseCatalogueFilePath,
                parterCourseCatalogueFilePath, noteCatalogueFilePath, universitiesCatalogueFilePath,
                mappingCatalogueFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location (LocalCourseCatalogue): " + localCourseCatalogueFilePath);
        sb.append("\nLocal data file location (PartnerCourseCatalogue): " + parterCourseCatalogueFilePath);
        sb.append("\nLocal data file location (UniversityCatalogue): " + universitiesCatalogueFilePath);
        sb.append("\nLocal data file location (NoteCatalogue): " + noteCatalogueFilePath);
        sb.append("\nLocal data file location (MappingCatalogue): " + mappingCatalogueFilePath);
        return sb.toString();
    }

}
