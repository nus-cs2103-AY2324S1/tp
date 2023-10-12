package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;
import static seedu.lovebook.testutil.TypicalPersons.HOON;
import static seedu.lovebook.testutil.TypicalPersons.IDA;
import static seedu.lovebook.testutil.TypicalPersons.getTypicalLoveBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.lovebook.commons.exceptions.DataLoadingException;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;

public class JsonLoveBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLoveBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLoveBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLoveBook(null));
    }

    private java.util.Optional<ReadOnlyLoveBook> readLoveBook(String filePath) throws Exception {
        return new JsonLoveBookStorage(Paths.get(filePath)).readLoveBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLoveBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readLoveBook("notJsonFormatLoveBook.json"));
    }

    @Test
    public void readLoveBook_invalidDateLoveBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readLoveBook("invalidDateLoveBook.json"));
    }

    @Test
    public void readLoveBook_invalidAndValidDateLoveBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readLoveBook("invalidAndValidDateLoveBook.json"));
    }

    @Test
    public void readAndSaveLoveBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLoveBook.json");
        LoveBook original = getTypicalLoveBook();
        JsonLoveBookStorage jsonLoveBookStorage = new JsonLoveBookStorage(filePath);

        // Save in new file and read back
        jsonLoveBookStorage.saveLoveBook(original, filePath);
        ReadOnlyLoveBook readBack = jsonLoveBookStorage.readLoveBook(filePath).get();
        assertEquals(original, new LoveBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonLoveBookStorage.saveLoveBook(original, filePath);
        readBack = jsonLoveBookStorage.readLoveBook(filePath).get();
        assertEquals(original, new LoveBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonLoveBookStorage.saveLoveBook(original); // file path not specified
        readBack = jsonLoveBookStorage.readLoveBook().get(); // file path not specified
        assertEquals(original, new LoveBook(readBack));

    }

    @Test
    public void saveLoveBook_nullLoveBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLoveBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code LoveBook} at the specified {@code filePath}.
     */
    private void saveLoveBook(ReadOnlyLoveBook LoveBook, String filePath) {
        try {
            new JsonLoveBookStorage(Paths.get(filePath))
                    .saveLoveBook(LoveBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLoveBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLoveBook(new LoveBook(), null));
    }
}
