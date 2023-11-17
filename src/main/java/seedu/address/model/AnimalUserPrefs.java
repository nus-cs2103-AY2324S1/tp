package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class AnimalUserPrefs implements AnimalReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path animalCatalogFilePath = Paths.get("data" , "animalcatalog.json");

    /**
     * Creates a {@code AnimalUserPrefs} with default values.
     */
    public AnimalUserPrefs() {}

    /**
     * Creates a {@code AnimalUserPrefs} with the prefs in {@code userPrefs}.
     */
    public AnimalUserPrefs(AnimalReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code AnimalUserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(AnimalReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAnimalCatalogFilePath(newUserPrefs.getAnimalCatalogFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAnimalCatalogFilePath() {
        return animalCatalogFilePath;
    }

    public void setAnimalCatalogFilePath(Path animalCatalogFilePath) {
        requireNonNull(animalCatalogFilePath);
        this.animalCatalogFilePath = animalCatalogFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalUserPrefs)) {
            return false;
        }

        AnimalUserPrefs otherUserPrefs = (AnimalUserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && animalCatalogFilePath.equals(otherUserPrefs.animalCatalogFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, animalCatalogFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + animalCatalogFilePath);
        return sb.toString();
    }

}
