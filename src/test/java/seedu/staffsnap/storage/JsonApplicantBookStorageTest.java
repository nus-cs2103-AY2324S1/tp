package seedu.staffsnap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.HOON;
import static seedu.staffsnap.testutil.TypicalApplicants.IDA;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.staffsnap.commons.exceptions.DataLoadingException;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.ReadOnlyApplicantBook;

public class JsonApplicantBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonApplicantBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readApplicantBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readApplicantBook(null));
    }

    private java.util.Optional<ReadOnlyApplicantBook> readApplicantBook(String filePath) throws Exception {
        return new JsonApplicantBookStorage(
                Paths.get(filePath)).readApplicantBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readApplicantBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readApplicantBook("notJsonFormatApplicantBook.json"));
    }

    @Test
    public void readApplicantBook_invalidApplicantApplicantBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readApplicantBook("invalidApplicantApplicantBook.json"));
    }

    @Test
    public void readApplicantBook_invalidAndValidApplicantApplicantBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readApplicantBook("invalidAndValidApplicantApplicantBook.json"));
    }

    @Test
    public void readAndSaveApplicantBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempApplicantBook.json");
        ApplicantBook original = getTypicalApplicantBook();
        JsonApplicantBookStorage jsonApplicantBookStorage = new JsonApplicantBookStorage(filePath);

        // Save in new file and read back
        jsonApplicantBookStorage.saveApplicantBook(original, filePath);
        ReadOnlyApplicantBook readBack = jsonApplicantBookStorage.readApplicantBook(filePath).get();
        assertEquals(original, new ApplicantBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplicant(HOON);
        original.removeApplicant(ALICE);
        jsonApplicantBookStorage.saveApplicantBook(original, filePath);
        readBack = jsonApplicantBookStorage.readApplicantBook(filePath).get();
        assertEquals(original, new ApplicantBook(readBack));

        // Save and read without specifying file path
        original.addApplicant(IDA);
        jsonApplicantBookStorage.saveApplicantBook(original); // file path not specified
        readBack = jsonApplicantBookStorage.readApplicantBook().get(); // file path not specified
        assertEquals(original, new ApplicantBook(readBack));

    }

    @Test
    public void saveApplicantBook_nullApplicantBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicantBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code applicantBook} at the specified {@code filePath}.
     */
    private void saveApplicantBook(ReadOnlyApplicantBook applicantBook, String filePath) {
        try {
            new JsonApplicantBookStorage(Paths.get(filePath))
                    .saveApplicantBook(applicantBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveApplicantBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveApplicantBook(new ApplicantBook(), null));
    }
}
