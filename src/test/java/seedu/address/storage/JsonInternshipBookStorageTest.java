package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.GOVTECH;
import static seedu.address.testutil.TypicalInternships.JANESTREET;
import static seedu.address.testutil.TypicalInternships.OPTIVER;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipBook;
import seedu.address.model.ReadOnlyInternshipBook;

public class JsonInternshipBookStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonInternshipBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternshipBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternshipBook(null));
    }

    private java.util.Optional<ReadOnlyInternshipBook> readInternshipBook(String filePath) throws Exception {
        return new JsonInternshipBookStorage(
                Paths.get(filePath)).readInternshipBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternshipBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readInternshipBook("notJsonFormatInternshipBook.json"));
    }

    @Test
    public void readInternshipBook_invalidInternshipInternshipBook_throwDataLoadingException() {
        assertThrows(
                DataLoadingException.class, () ->
                        readInternshipBook("invalidInternshipInternshipBook.json")
        );
    }

    @Test
    public void readInternshipBook_invalidAndValidInternshipInternshipBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () ->
                readInternshipBook("invalidAndValidInternshipInternshipBook.json")
        );
    }

    @Test
    public void readAndSaveInternshipBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternshipBook.json");
        InternshipBook original = getTypicalInternshipBook();
        JsonInternshipBookStorage jsonInternshipBookStorage = new JsonInternshipBookStorage(filePath);

        // Save in new file and read back
        jsonInternshipBookStorage.saveInternshipBook(original, filePath);
        ReadOnlyInternshipBook readBack = jsonInternshipBookStorage.readInternshipBook(filePath).get();
        assertEquals(original, new InternshipBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addInternship(JANESTREET);
        original.removeInternship(OPTIVER);
        jsonInternshipBookStorage.saveInternshipBook(original, filePath);
        readBack = jsonInternshipBookStorage.readInternshipBook(filePath).get();
        assertEquals(original, new InternshipBook(readBack));

        // Save and read without specifying file path
        original.addInternship(GOVTECH);
        jsonInternshipBookStorage.saveInternshipBook(original); // file path not specified
        readBack = jsonInternshipBookStorage.readInternshipBook().get(); // file path not specified
        assertEquals(original, new InternshipBook(readBack));

    }

    @Test
    public void saveInternshipBook_nullInternshipBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code internshipBook} at the specified {@code filePath}.
     */
    private void saveInternshipBook(ReadOnlyInternshipBook internshipBook, String filePath) {
        try {
            new JsonInternshipBookStorage(Paths.get(filePath))
                    .saveInternshipBook(internshipBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternshipBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternshipBook(new InternshipBook(), null));
    }
}
