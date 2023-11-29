package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuickNotesStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data");
    private static final Path TEST_DATA_FILE = TEST_DATA_FOLDER.resolve("testQuickNotes.txt");

    private QuickNotesStorage quickNotesStorage;

    @BeforeEach
    void setUp() {
        quickNotesStorage = new QuickNotesStorage(TEST_DATA_FILE.toString());
    }

    @Test
    public void defaultConstructor_setsDefaultPath() {
        QuickNotesStorage storage = new QuickNotesStorage();
        assertEquals("./data/quicknotes.txt", storage.getQuickNotesPath());
    }

    @Test
    public void saveNotes_validNote_successfullySaved() throws IOException {
        String sampleContent = "Sample note content";
        quickNotesStorage.saveNotes(sampleContent);

        String readContent = new String(Files.readAllBytes(TEST_DATA_FILE));
        assertEquals(sampleContent, readContent);
    }

    @Test
    public void saveNotes_directoryDoesNotExist_createsDirectory() throws IOException {
        Path tempPath = TEST_DATA_FOLDER.resolve("tempData");
        QuickNotesStorage storageWithNewDir = new QuickNotesStorage(tempPath.resolve("tempQuickNotes.txt").toString());

        // Ensure the file and directory do not exist initially
        Files.deleteIfExists(tempPath.resolve("tempQuickNotes.txt"));
        Files.deleteIfExists(tempPath);

        String sampleContent = "Content for new directory";
        storageWithNewDir.saveNotes(sampleContent);

        assertTrue(Files.exists(tempPath));

        // Cleanup
        Files.delete(tempPath.resolve("tempQuickNotes.txt"));
        Files.delete(tempPath);
    }

    @Test
    public void saveNotes_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickNotesStorage.saveNotes(null));
    }

    @Test
    public void clearNotes_validFile_successfullyCleared() throws IOException {
        String sampleContent = "Sample note for clearing";
        Files.write(TEST_DATA_FILE, sampleContent.getBytes());

        quickNotesStorage.clearNotes();

        String clearedContent = new String(Files.readAllBytes(TEST_DATA_FILE));
        assertEquals("", clearedContent);
    }

    @Test
    public void loadNotes_validFile_successfullyLoaded() throws IOException {
        String sampleContent = "Sample note for loading";
        Files.write(TEST_DATA_FILE, sampleContent.getBytes());

        String loadedContent = quickNotesStorage.loadNotes();
        assertEquals(sampleContent, loadedContent);
    }

    @Test
        public void loadNotes_invalidFile_throwsIoException() {
        Path invalidPath = TEST_DATA_FOLDER.resolve("nonExistentFile.txt");
        QuickNotesStorage invalidStorage = new QuickNotesStorage(invalidPath.toString());

        assertThrows(IOException.class, invalidStorage::loadNotes);
    }
}
