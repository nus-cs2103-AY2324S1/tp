package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGradedTest.GT1;
import static seedu.address.testutil.TypicalGradedTest.GT10;
import static seedu.address.testutil.TypicalGradedTest.GT9;
import static seedu.address.testutil.TypicalGradedTest.getTypicalGradedTestList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.GradedTestListBook;
import seedu.address.model.ReadOnlyGradedTestList;

public class JsonGradedTestListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonGradedTestListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGradedTestList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGradedTestList(null));
    }

    private java.util.Optional<ReadOnlyGradedTestList> readGradedTestList(String filePath)
            throws Exception {
        return new JsonGradedTestListStorage(Paths.get(filePath))
                .readGradedTestList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGradedTestList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () ->
                readGradedTestList("notJsonFormatGradedTestList.json"));
    }

    @Test
    public void readGradedTestList_invalidGradedTestList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, ()->
                readGradedTestList("invalidGradedTestList.json"));
    }

    @Test
    public void readGradedTestList_invalidAndValidGradedTestList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readGradedTestList("invalidAndValidGradedTestList.json"));
    }

    @Test
    public void readAndSaveGradedTestList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGradedTestList.json");
        GradedTestListBook original = getTypicalGradedTestList();
        JsonGradedTestListStorage jsonGradedTestListStorage = new JsonGradedTestListStorage(filePath);

        // Save in new file and read back
        jsonGradedTestListStorage.saveGradedTestList(original, filePath);
        ReadOnlyGradedTestList readBack = jsonGradedTestListStorage.readGradedTestList(filePath).get();
        assertEquals(original, new GradedTestListBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGradedTest(GT9);
        original.removeGradedTest(GT1);
        jsonGradedTestListStorage.saveGradedTestList(original, filePath);
        readBack = jsonGradedTestListStorage.readGradedTestList(filePath).get();
        assertEquals(original, new GradedTestListBook(readBack));

        // Save and read without specifying file path
        original.addGradedTest(GT10);
        jsonGradedTestListStorage.saveGradedTestList(original); // file path not specified
        readBack = jsonGradedTestListStorage.readGradedTestList().get(); // file path not specified
        assertEquals(original, new GradedTestListBook(readBack));

    }

    @Test
    public void saveTaskList_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveGradedTestList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code gradedTestList} at the specified {@code filePath}.
     */
    private void saveGradedTestList(ReadOnlyGradedTestList gradedTestList, String filePath) {
        try {
            new JsonGradedTestListStorage(Paths.get(filePath))
                    .saveGradedTestList(gradedTestList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGradedTestList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                saveGradedTestList(new GradedTestListBook(), null));
    }
}
