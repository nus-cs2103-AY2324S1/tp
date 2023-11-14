package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;
import static seedu.application.testutil.TypicalJobs.GRASS_CUTTER;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.application.commons.exceptions.DataLoadingException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;

public class JsonApplicationBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonApplicationBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readApplicationBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readApplicationBook(null));
    }

    private java.util.Optional<ReadOnlyApplicationBook> readApplicationBook(String filePath) throws Exception {
        return new JsonApplicationBookStorage(Paths.get(filePath))
                .readApplicationBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readApplicationBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readApplicationBook("notJsonFormatApplicationBook.json"));
    }

    @Test
    public void readApplicationBook_invalidJobApplicationBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readApplicationBook("invalidJobApplicationBook.json"));
    }

    @Test
    public void readApplicationBook_invalidAndValidJobApplicationBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readApplicationBook("invalidAndValidJobApplicationBook.json"));
    }

    @Test
    public void readAndSaveApplicationBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempApplicationBook.json");
        ApplicationBook original = getTypicalApplicationBook();
        JsonApplicationBookStorage jsonApplicationBookStorage = new JsonApplicationBookStorage(filePath);

        // Save in new file and read back
        jsonApplicationBookStorage.saveApplicationBook(original, filePath);
        ReadOnlyApplicationBook readBack = jsonApplicationBookStorage.readApplicationBook(filePath).get();
        assertEquals(original, new ApplicationBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addJob(CHEF);
        original.removeJob(GRASS_CUTTER);
        jsonApplicationBookStorage.saveApplicationBook(original, filePath);
        readBack = jsonApplicationBookStorage.readApplicationBook(filePath).get();
        assertEquals(original, new ApplicationBook(readBack));

        // Save and read without specifying file path
        original.addJob(CLEANER);
        jsonApplicationBookStorage.saveApplicationBook(original); // file path not specified
        readBack = jsonApplicationBookStorage.readApplicationBook().get(); // file path not specified
        assertEquals(original, new ApplicationBook(readBack));

    }

    @Test
    public void saveApplicationBook_nullApplicationBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ApplicationBook} at the specified {@code filePath}.
     */
    private void saveApplicationBook(ReadOnlyApplicationBook applicationBook, String filePath) {
        try {
            new JsonApplicationBookStorage(Paths.get(filePath))
                    .saveApplicationBook(applicationBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveApplicationBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicationBook(new ApplicationBook(), null));
    }
}
