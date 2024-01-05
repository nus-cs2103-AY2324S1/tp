package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSessions.SESSION1A;
import static seedu.address.testutil.TypicalSessions.SESSION3A;
import static seedu.address.testutil.TypicalSessions.SESSION_TYPICAL1;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlySessionList;
import seedu.address.model.SessionListBook;

public class JsonSessionListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSessionListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSessionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSessionList(null));
    }

    private java.util.Optional<ReadOnlySessionList> readSessionList(String filePath) throws Exception {
        return new JsonSessionListStorage(Paths.get(filePath)).readSessionList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSessionList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readSessionList("notJsonFormatSessionList.json"));
    }

    @Test
    public void readSessionList_invalidSessionList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readSessionList("invalidSessionList.json"));
    }

    @Test
    public void readSessionList_invalidAndValidSessionList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readSessionList("invalidAndValidSessionList.json"));
    }

    @Test
    public void readAndSaveSessionList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSessionList.json");
        SessionListBook original = getTypicalSessionList();
        JsonSessionListStorage jsonSessionListStorage = new JsonSessionListStorage(filePath);

        // Save in new file and read back
        jsonSessionListStorage.saveSessionList(original, filePath);
        ReadOnlySessionList readBack = jsonSessionListStorage.readSessionList(filePath).get();
        assertEquals(original, new SessionListBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addSession(SESSION1A);
        original.removeSession(SESSION_TYPICAL1);
        jsonSessionListStorage.saveSessionList(original, filePath);
        readBack = jsonSessionListStorage.readSessionList(filePath).get();
        assertEquals(original, new SessionListBook(readBack));

        // Save and read without specifying file path
        original.addSession(SESSION3A);
        jsonSessionListStorage.saveSessionList(original); // file path not specified
        readBack = jsonSessionListStorage.readSessionList().get(); // file path not specified
        assertEquals(original, new SessionListBook(readBack));
    }

    @Test
    public void saveSessionList_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSessionList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskList} at the specified {@code filePath}.
     */
    private void saveSessionList(ReadOnlySessionList sessionList, String filePath) {
        try {
            new JsonSessionListStorage(Paths.get(filePath))
                    .saveSessionList(sessionList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("There should not be an error writing to the file.", e);
        }
    }

    @Test
    public void saveSessionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSessionList(new SessionListBook(), null));
    }


}
