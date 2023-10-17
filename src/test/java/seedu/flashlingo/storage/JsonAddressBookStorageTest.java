package seedu.flashlingo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.flashlingo.testutil.Assert.assertThrows;
import static seedu.flashlingo.testutil.TypicalPersons.getTypicalFlashlingo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashlingo.commons.exceptions.DataLoadingException;
import seedu.flashlingo.model.AddressBook;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.ReadOnlyFlashlingo;
import seedu.flashlingo.storage.JsonFlashlingoStorage;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashlingo(null));
    }

    private java.util.Optional<ReadOnlyFlashlingo> readFlashlingo(String filePath) throws Exception {
        return new JsonFlashlingoStorage(Paths.get(filePath)).readFlashlingo(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashlingo("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFlashlingo("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Flashlingo original = getTypicalFlashlingo();
        JsonFlashlingoStorage jsonFlashlingoStorage = new JsonFlashlingoStorage(filePath);

        // Save in new file and read back
        jsonFlashlingoStorage.saveFlashlingo(original, filePath);
        ReadOnlyFlashlingo readBack = jsonFlashlingoStorage.readFlashlingo(filePath).get();
        assertEquals(original, new Flashlingo(readBack));

        // Modify data, overwrite exiting file, and read back
        //FIXME: Rewrite actions
//        original.addPerson(HOON);
//        original.removePerson(ALICE);
        jsonFlashlingoStorage.saveFlashlingo(original, filePath);
        readBack = jsonFlashlingoStorage.readFlashlingo(filePath).get();
        assertEquals(original, new Flashlingo(readBack));

        // Save and read without specifying file path
//        original.addPerson(IDA);
        jsonFlashlingoStorage.saveFlashlingo(original); // file path not specified
        readBack = jsonFlashlingoStorage.readFlashlingo().get(); // file path not specified
        assertEquals(original, new Flashlingo(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashlingo(null, "SomeFile.json"));
    }

    /**
     * Saves {@code flashlingo} at the specified {@code filePath}.
     */
    private void saveFlashlingo(ReadOnlyFlashlingo flashlingo, String filePath) {
        try {
            new JsonFlashlingoStorage(Paths.get(filePath))
                    .saveFlashlingo(flashlingo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashlingo(new Flashlingo(), null));
    }
}
