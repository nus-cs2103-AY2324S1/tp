package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.HOON;
import static seedu.address.testutil.TypicalEmployees.IDA;
import static seedu.address.testutil.TypicalEmployees.getTypicalManageHr;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHr;

public class JsonManageHrStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonManageHrStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readManageHr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readManageHr(null));
    }

    private java.util.Optional<ReadOnlyManageHr> readManageHr(String filePath) throws Exception {
        return new JsonManageHrStorage(Paths.get(filePath)).readManageHr(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readManageHr("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readManageHr("notJsonFormatManageHr.json"));
    }

    @Test
    public void readManageHr_invalidEmployeeManageHr_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readManageHr("invalidEmployeeManageHr.json"));
    }

    @Test
    public void readManageHr_invalidAndValidEmployeeManageHr_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readManageHr("invalidAndValidEmployeeManageHr.json"));
    }

    @Test
    public void readAndSaveManageHr_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ManageHr original = getTypicalManageHr();
        JsonManageHrStorage jsonManageHrStorage = new JsonManageHrStorage(filePath);

        // Save in new file and read back
        jsonManageHrStorage.saveManageHr(original, filePath);
        ReadOnlyManageHr readBack = jsonManageHrStorage.readManageHr(filePath).get();
        assertEquals(original, new ManageHr(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEmployee(HOON);
        original.removeEmployee(ALICE);
        jsonManageHrStorage.saveManageHr(original, filePath);
        readBack = jsonManageHrStorage.readManageHr(filePath).get();
        assertEquals(original, new ManageHr(readBack));

        // Save and read without specifying file path
        original.addEmployee(IDA);
        jsonManageHrStorage.saveManageHr(original); // file path not specified
        readBack = jsonManageHrStorage.readManageHr().get(); // file path not specified
        assertEquals(original, new ManageHr(readBack));

    }

    @Test
    public void saveManageHr_nullManageHr_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveManageHr(null, "SomeFile.json"));
    }

    /**
     * Saves {@code manageHr} at the specified {@code filePath}.
     */
    private void saveManageHr(ReadOnlyManageHr manageHr, String filePath) {
        try {
            new JsonManageHrStorage(Paths.get(filePath))
                    .saveManageHr(manageHr, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveManageHr_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveManageHr(new ManageHr(), null));
    }
}
