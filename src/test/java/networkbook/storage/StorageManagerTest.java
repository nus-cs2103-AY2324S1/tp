package networkbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import networkbook.commons.core.GuiSettings;
import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.UserPrefs;
import networkbook.testutil.TypicalPersons;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonNetworkBookStorage networkBookStorage = new JsonNetworkBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(networkBookStorage, userPrefsStorage);
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
    public void networkBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonNetworkBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonNetworkBookStorageTest} class.
         */
        NetworkBook original = TypicalPersons.getTypicalNetworkBook();
        storageManager.saveNetworkBook(original);
        ReadOnlyNetworkBook retrieved = storageManager.readNetworkBook().get();
        assertEquals(original, new NetworkBook(retrieved));
    }

    @Test
    public void getNetworkBookFilePath() {
        assertNotNull(storageManager.getNetworkBookFilePath());
    }

}
