package seedu.letsgethired.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInternTrackerStorage internBookStorage = new JsonInternTrackerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(internBookStorage, userPrefsStorage);
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
    public void internBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInternTrackerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInternTrackerStorageTest} class.
         */
        InternTracker original = getTypicalInternTracker();
        storageManager.saveInternTracker(original);
        ReadOnlyInternTracker retrieved = storageManager.readInternTracker().get();
        assertEquals(original, new InternTracker(retrieved));
    }

    @Test
    public void getInternTrackerFilePath() {
        assertNotNull(storageManager.getInternTrackerFilePath());
    }

    @Test
    public void getUserPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

}
