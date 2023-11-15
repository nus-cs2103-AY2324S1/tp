package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPrescriptionListStorage prescriptionListStorage = new JsonPrescriptionListStorage(
            getTempFilePath("pl"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
            getTempFilePath("prefs"));
        JsonCompletedPrescriptionListStorage completedPrescriptionListStorage =
            new JsonCompletedPrescriptionListStorage(getTempFilePath("cpl"));
        storageManager = new StorageManager(prescriptionListStorage,
            completedPrescriptionListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStoragePrescription} class.
         * More extensive testing of UserPref saving/reading is done in
         * {@link JsonUserPrefsStoragePrescriptionTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void prescriptionListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPrescriptionListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPrescriptionListStorageTest} class.
         */
        PrescriptionList original = getTypicalPrescriptionList();
        storageManager.savePrescriptionList(original);
        ReadOnlyPrescriptionList retrieved = storageManager.readPrescriptionList().get();
        assertEquals(original, new PrescriptionList(retrieved));
    }

    @Test
    public void getPrescriptionListFilePath() {
        assertNotNull(storageManager.getPrescriptionListFilePath());
    }

}
