package networkbook.storage;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import networkbook.commons.exceptions.DataLoadingException;
import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.testutil.TypicalPersons;

public class JsonNetworkBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNetworkBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNetworkBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNetworkBook(null));
    }

    private java.util.Optional<ReadOnlyNetworkBook> readNetworkBook(String filePath) throws Exception {
        return new JsonNetworkBookStorage(Paths.get(filePath)).readNetworkBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNetworkBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readNetworkBook("notJsonFormatNetworkBook.json"));
    }

    @Test
    public void readNetworkBook_invalidPersonNetworkBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNetworkBook("invalidPersonNetworkBook.json"));
    }

    @Test
    public void readNetworkBook_invalidAndValidPersonNetworkBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNetworkBook("invalidAndValidPersonNetworkBook.json"));
    }

    @Test
    public void readAndSaveNetworkBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNetworkBook.json");
        NetworkBook original = TypicalPersons.getTypicalNetworkBook();
        JsonNetworkBookStorage jsonNetworkBookStorage = new JsonNetworkBookStorage(filePath);

        // Save in new file and read back
        jsonNetworkBookStorage.saveNetworkBook(original, filePath);
        ReadOnlyNetworkBook readBack = jsonNetworkBookStorage.readNetworkBook(filePath).get();
        assertEquals(original, new NetworkBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(TypicalPersons.HOON);
        original.removePerson(TypicalPersons.ALICE);
        jsonNetworkBookStorage.saveNetworkBook(original, filePath);
        readBack = jsonNetworkBookStorage.readNetworkBook(filePath).get();
        assertEquals(original, new NetworkBook(readBack));

        // Save and read without specifying file path
        original.addPerson(TypicalPersons.IDA);
        jsonNetworkBookStorage.saveNetworkBook(original); // file path not specified
        readBack = jsonNetworkBookStorage.readNetworkBook().get(); // file path not specified
        assertEquals(original, new NetworkBook(readBack));

    }

    @Test
    public void saveNetworkBook_nullNetworkBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNetworkBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code networkBook} at the specified {@code filePath}.
     */
    private void saveNetworkBook(ReadOnlyNetworkBook networkBook, String filePath) {
        try {
            new JsonNetworkBookStorage(Paths.get(filePath))
                    .saveNetworkBook(networkBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNetworkBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNetworkBook(new NetworkBook(), null));
    }
}
