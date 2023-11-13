package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FIX_BUG;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;
import static seedu.address.testutil.TypicalTasks.TASK_TWO;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskWise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.ReadOnlyTaskWise;
import seedu.address.model.TaskWise;
import seedu.address.storage.exceptions.storage.FileStorageLoadException;

public class JsonTaskWiseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskWiseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskWise_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskWise(null));
    }

    private java.util.Optional<ReadOnlyTaskWise> readTaskWise(String filePath) throws Exception {
        return new JsonTaskWiseStorage(Paths.get(filePath)).readTaskWise(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskWise("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(FileStorageLoadException.class, () -> readTaskWise("notJsonFormatTaskWise.json"));
    }

    @Test
    public void readTaskWise_invalidTaskTaskWise_throwDataLoadingException() {
        assertThrows(FileStorageLoadException.class, () -> readTaskWise("invalidTaskTaskWise.json"));
    }

    @Test
    public void readTaskWise_invalidAndValidTaskTaskWise_throwDataLoadingException() {
        assertThrows(FileStorageLoadException.class, () -> readTaskWise("invalidAndValidTaskTaskWise.json"));
    }

    @Test
    public void readAndSaveTaskWise_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskWise.json");
        TaskWise original = getTypicalTaskWise();
        JsonTaskWiseStorage jsonTaskWiseStorage = new JsonTaskWiseStorage(filePath);

        // Save in new file and read back
        jsonTaskWiseStorage.saveTaskWise(original, filePath);
        ReadOnlyTaskWise readBack = jsonTaskWiseStorage.readTaskWise(filePath).get();
        assertEquals(original, new TaskWise(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TASK_ONE);
        original.removeTask(FIX_BUG);
        jsonTaskWiseStorage.saveTaskWise(original, filePath);
        readBack = jsonTaskWiseStorage.readTaskWise(filePath).get();
        assertEquals(original, new TaskWise(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_TWO);
        jsonTaskWiseStorage.saveTaskWise(original); // file path not specified
        readBack = jsonTaskWiseStorage.readTaskWise().get(); // file path not specified
        assertEquals(original, new TaskWise(readBack));

    }

    @Test
    public void saveTaskWise_nullTaskWise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskWise(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveTaskWise(ReadOnlyTaskWise addressBook, String filePath) {
        try {
            new JsonTaskWiseStorage(Paths.get(filePath))
                    .saveTaskWise(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskWise_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskWise(new TaskWise(), null));
    }
}
