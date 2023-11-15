package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalPatients.ALICE;
import static seedu.cc.testutil.TypicalPatients.HOON;
import static seedu.cc.testutil.TypicalPatients.IDA;
import static seedu.cc.testutil.TypicalPatients.getTypicalClinicBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.commons.exceptions.DataLoadingException;
import seedu.cc.model.ClinicBook;
import seedu.cc.model.ReadOnlyClinicBook;

public class JsonClinicBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClinicBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClinicBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClinicBook(null));
    }

    private java.util.Optional<ReadOnlyClinicBook> readClinicBook(String filePath) throws Exception {
        return new JsonClinicBookStorage(Paths.get(filePath)).readClinicBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClinicBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readClinicBook("notJsonFormatClinicBook.json"));
    }

    @Test
    public void readClinicBook_invalidPatientClinicBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClinicBook("invalidPatientClinicBook.json"));
    }

    @Test
    public void readClinicBook_invalidAndValidPatientClinicBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readClinicBook("invalidAndValidPatientClinicBook.json"));
    }

    @Test
    public void readAndSaveClinicBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClinicBook.json");
        ClinicBook original = getTypicalClinicBook();
        JsonClinicBookStorage jsonClinicBookStorage = new JsonClinicBookStorage(filePath);

        // Save in new file and read back
        jsonClinicBookStorage.saveClinicBook(original, filePath);
        ReadOnlyClinicBook readBack = jsonClinicBookStorage.readClinicBook(filePath).get();
        assertEquals(original, new ClinicBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonClinicBookStorage.saveClinicBook(original, filePath);
        readBack = jsonClinicBookStorage.readClinicBook(filePath).get();
        assertEquals(original, new ClinicBook(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonClinicBookStorage.saveClinicBook(original); // file path not specified
        readBack = jsonClinicBookStorage.readClinicBook().get(); // file path not specified
        assertEquals(original, new ClinicBook(readBack));

    }

    @Test
    public void saveClinicBook_nullClinicBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClinicBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveClinicBook(ReadOnlyClinicBook addressBook, String filePath) {
        try {
            new JsonClinicBookStorage(Paths.get(filePath))
                    .saveClinicBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClinicBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClinicBook(new ClinicBook(), null));
    }
}
