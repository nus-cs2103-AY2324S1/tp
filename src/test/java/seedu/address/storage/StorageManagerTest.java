package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalVolunteers.getTypicalVolunteerStorage;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyVolunteerStorage;
import seedu.address.model.UserPrefs;
import seedu.address.model.VolunteerStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonVolunteerStorage volunteerStorage = new JsonVolunteerStorage(getTempFilePath("ab"));
        JsonEventStorage eventStorage = new JsonEventStorage(getTempFilePath("bc"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(eventStorage, volunteerStorage, userPrefsStorage);
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
    public void volunteerStorageReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonVolunteerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonVolunteerStorageTest} class.
         */
        VolunteerStorage original = getTypicalVolunteerStorage();
        storageManager.saveVolunteerStorage(original);
        ReadOnlyVolunteerStorage retrieved = storageManager.readVolunteerStorage().get();
        assertEquals(original, new VolunteerStorage(retrieved));
    }

    @Test
    public void getVolunteerStorageFilePath() {
        assertNotNull(storageManager.getVolunteerStorageFilePath());
    }

}
