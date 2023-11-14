package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.InternshipBook;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipBook;

public class InternshipStorageManagerTest {

    @TempDir
    public Path testFolder;

    private InternshipStorageManager internshipStorageManager;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage = new JsonInternshipBookStorage(getTempFilePath("ab"));
        JsonInternshipUserPrefsStorage internshipUserPrefsStorage = new JsonInternshipUserPrefsStorage(
                getTempFilePath("prefs")
            );
        internshipStorageManager = new InternshipStorageManager(internshipBookStorage, internshipUserPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the InternshipStorageManager is properly wired to the
         * {@link JsonInternshipUserPrefsStorage} class.
         * More extensive testing of InternshipUserPref saving/reading is done in
         * {@link JsonInternshipUserPrefsStorageTest} class.
         */
        InternshipUserPrefs original = new InternshipUserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        internshipStorageManager.saveUserPrefs(original);
        InternshipUserPrefs retrieved = internshipStorageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void internshipBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the InternshipStorageManager is properly wired to the
         * {@link JsonInternshipBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInternshipBookStorageTest} class.
         */
        InternshipBook original = getTypicalInternshipBook();
        internshipStorageManager.saveInternshipBook(original);
        ReadOnlyInternshipBook retrieved = internshipStorageManager.readInternshipBook().get();
        assertEquals(original, new InternshipBook(retrieved));
    }

    @Test
    public void getInternshipBookFilePath() {
        assertNotNull(internshipStorageManager.getInternshipBookFilePath());
    }

    @Test
    public void getUserPrefsFilePath() {
        Path expectedPath = getTempFilePath("prefs");
        Path actualPath = internshipStorageManager.getUserPrefsFilePath();
        assertEquals(expectedPath, actualPath);
    }
}
