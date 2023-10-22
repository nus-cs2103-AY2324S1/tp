package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.ccacommander.testutil.TypicalCcaCommander.getTypicalCcaCommander;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.ccacommander.commons.core.GuiSettings;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.ReadOnlyCcaCommander;
import seedu.ccacommander.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCcaCommanderStorage ccaCommanderStorage = new JsonCcaCommanderStorage(getTempFilePath("ccacommander"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(ccaCommanderStorage, userPrefsStorage);
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
    public void ccaCommanderReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCcaCommanderStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCcaCommanderStorageTest} class.
         */
        CcaCommander original = getTypicalCcaCommander();
        storageManager.saveCcaCommander(original);
        ReadOnlyCcaCommander retrieved = storageManager.readCcaCommander().get();
        assertEquals(original, new CcaCommander(retrieved));
    }

    @Test
    public void getCcaCommanderFilePath() {
        assertNotNull(storageManager.getCcaCommanderFilePath());
    }

}
