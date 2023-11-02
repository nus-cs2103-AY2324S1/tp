package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FEATURE;
import static seedu.address.testutil.TypicalTasks.REPORT;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.task.ReadOnlyTaskManager;
import seedu.address.model.task.TaskManager;

public class JsonTaskManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonTaskManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskManager(null));
    }

    private java.util.Optional<ReadOnlyTaskManager> readTaskManager(String filePath) throws Exception {
        return new JsonTaskManagerStorage(Paths.get(filePath)).readTaskManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readTaskManager("notJsonFormatTaskManager.json"));
    }

    @Test
    public void readTaskManager_invalidTaskTaskManager_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTaskManager("invalidTaskTaskManager.json"));
    }

    @Test
    public void readTaskManager_invalidAndValidTaskTaskManager_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readTaskManager("invalidAndValidTaskTaskManager.json"));
    }

    @Test
    public void readAndSaveTaskManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskManager.json");
        TaskManager original = getTypicalTaskManager();
        JsonTaskManagerStorage jsonTaskManagerStorage = new JsonTaskManagerStorage(filePath);

        // Save in new file and read back
        jsonTaskManagerStorage.saveTaskManager(original, filePath);
        ReadOnlyTaskManager readBack = jsonTaskManagerStorage.readTaskManager(filePath).get();
        assertEquals(original, new TaskManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(REPORT);
        jsonTaskManagerStorage.saveTaskManager(original, filePath);
        readBack = jsonTaskManagerStorage.readTaskManager(filePath).get();
        assertEquals(original, new TaskManager(readBack));

        // Save and read without specifying file path
        original.addTask(FEATURE);
        jsonTaskManagerStorage.saveTaskManager(original); // file path not specified
        readBack = jsonTaskManagerStorage.readTaskManager().get(); // file path not specified
        assertEquals(original, new TaskManager(readBack));

    }

    @Test
    public void saveTaskManager_nullTaskManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskManager} at the specified {@code filePath}.
     */
    private void saveTaskManager(ReadOnlyTaskManager taskManager, String filePath) {
        try {
            new JsonTaskManagerStorage(Paths.get(filePath))
                    .saveTaskManager(taskManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskManager(new TaskManager(), null));
    }
}
