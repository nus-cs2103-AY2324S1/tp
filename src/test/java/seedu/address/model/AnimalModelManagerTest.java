package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;

public class AnimalModelManagerTest {
    private static final AnimalCatalog ANIMAL_CATALOG = new AnimalCatalog();

    private static final AnimalUserPrefs ANIMAL_USER_PREFS = new AnimalUserPrefs();

    private static final GuiSettings GUI_SETTINGS = new GuiSettings();

    @TempDir
    public Path temporaryFolder;

    @Test
    public void testSetUserPrefs_withNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertThrows(NullPointerException.class, () -> animalModelManager.setUserPrefs(null));
    }

    @Test
    public void testSetUserPrefs_withNonNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertDoesNotThrow(() -> animalModelManager.setUserPrefs(ANIMAL_USER_PREFS));
    }

    @Test
    public void testGetUserPrefs() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        animalModelManager.setUserPrefs(ANIMAL_USER_PREFS);
        assertEquals(ANIMAL_USER_PREFS, animalModelManager.getUserPrefs());
    }

    @Test
    public void testSetGuiSettings_withNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertThrows(NullPointerException.class, () -> animalModelManager.setGuiSettings(null));
    }

    @Test
    public void testSetGuiSettings_withNonNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertDoesNotThrow(() -> animalModelManager.setGuiSettings(GUI_SETTINGS));
    }

    @Test
    public void testGetGuiSettings() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        animalModelManager.setGuiSettings(GUI_SETTINGS);
        assertEquals(GUI_SETTINGS, animalModelManager.getGuiSettings());
    }

    @Test
    public void testSetAnimalCatalogFilePath_withNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertThrows(NullPointerException.class, ()-> animalModelManager.setAnimalCatalogFilePath(null));
    }

    @Test
    public void testSetAnimalCatalogFilePath_withNonNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertDoesNotThrow(() -> animalModelManager.setAnimalCatalogFilePath(temporaryFolder));
    }

    @Test
    public void testSetAnimalCatalog_withNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertThrows(NullPointerException.class, () -> animalModelManager.setAnimalCatalog(null));
    }

    @Test
    public void testSetAnimalCatalog_withNonNull() {
        AnimalModelManager animalModelManager = new AnimalModelManager();
        assertDoesNotThrow(() -> animalModelManager.setAnimalCatalog(ANIMAL_CATALOG));
    }
}
