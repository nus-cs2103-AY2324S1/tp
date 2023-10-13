package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonLoveBookStorage loveBookStorage = new JsonLoveBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(loveBookStorage, userPrefsStorage);
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
    public void loveBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonLoveBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonLoveBookStorageTest} class.
         */
        LoveBook original = getTypicalLoveBook();
        storageManager.saveLoveBook(original);
        ReadOnlyLoveBook retrieved = storageManager.readLoveBook().get();
        assertEquals(original, new LoveBook(retrieved));
    }

    @Test
    public void getLoveBookFilePath() {
        assertNotNull(storageManager.getLoveBookFilePath());
    }

}
