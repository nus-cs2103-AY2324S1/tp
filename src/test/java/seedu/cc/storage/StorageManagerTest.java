package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.commons.core.GuiSettings;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.ReadOnlyClinicBook;
import seedu.cc.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClinicBookStorage addressBookStorage = new JsonClinicBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
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
    public void clinicBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClinicBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClinicBookStorageTest} class.
         */
        ClinicBook original = getTypicalClinicBook();
        storageManager.saveClinicBook(original);
        ReadOnlyClinicBook retrieved = storageManager.readClinicBook().get();
        assertEquals(original, new ClinicBook(retrieved));
    }

    @Test
    public void getClinicBookFilePath() {
        assertNotNull(storageManager.getClinicBookFilePath());
    }

}
