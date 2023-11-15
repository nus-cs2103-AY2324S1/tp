package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyRecipeBook;
import seedu.address.model.RecipeBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInventoryStorage inventoryStorage = new JsonInventoryStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonRecipeBookStorage recipeBookStorage = new JsonRecipeBookStorage(getTempFilePath("recipeBook"));
        storageManager = new StorageManager(inventoryStorage, userPrefsStorage, recipeBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void inventoryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInventoryStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInventoryStorageTest} class.
         */
        Inventory original = getTypicalInventory();
        storageManager.saveInventory(original);
        ReadOnlyInventory retrieved = storageManager.readInventory().get();
        assertEquals(original, new Inventory(retrieved));
    }

    @Test
    public void getInventoryFilePath() {
        assertNotNull(storageManager.getInventoryFilePath());
    }

    @Test
    public void recipeBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies that StorageManager is properly wired to the
         * {@Link JsonRecipeBookStorage} class.
         */
        RecipeBook original = getTypicalRecipeBook();
        storageManager.saveRecipeBook(original);
        ReadOnlyRecipeBook retrieved = storageManager.readRecipeBook().get();
        assertEquals(original, new RecipeBook(retrieved));
    }

    @Test
    public void getRecipeBookFilePath() {
        assertNotNull(storageManager.getRecipeBookFilePath());
    }

}
