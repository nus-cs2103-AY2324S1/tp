package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.ALICE;
import static seedu.address.testutil.TestData.HOON;
import static seedu.address.testutil.TestData.IDA;
import static seedu.address.testutil.TestData.getTypicalConText;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ContactsManager;
import seedu.address.model.ContactList;

public class JsonContactsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonContactsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readConText_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readConText(null));
    }

    private java.util.Optional<ContactList> readConText(String filePath) throws Exception {
        return new JsonContactsStorage(Paths.get(filePath)).readConText(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readConText("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readConText("notJsonFormatConText.json"));
    }

    @Test
    public void readConText_invalidContactConText_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readConText("invalidContactConText.json"));
    }

    @Test
    public void readConText_invalidAndValidContactConText_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readConText("invalidAndValidContactConText.json"));
    }

    @Test
    public void readAndSaveConText_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempConText.json");
        ContactsManager original = getTypicalConText();
        JsonContactsStorage jsonContactsStorage = new JsonContactsStorage(filePath);

        // Save in new file and read back
        jsonContactsStorage.saveConText(original, filePath);
        ContactList readBack = jsonContactsStorage.readConText(filePath).get();
        assertEquals(original, new ContactsManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonContactsStorage.saveConText(original, filePath);
        readBack = jsonContactsStorage.readConText(filePath).get();
        assertEquals(original, new ContactsManager(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonContactsStorage.saveConText(original); // file path not specified
        readBack = jsonContactsStorage.readConText().get(); // file path not specified
        assertEquals(original, new ContactsManager(readBack));

    }

    @Test
    public void saveConText_nullConText_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConText(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ContactsManager} at the specified {@code filePath}.
     */
    private void saveConText(ContactList contactList, String filePath) {
        try {
            new JsonContactsStorage(Paths.get(filePath))
                    .saveConText(contactList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveConText_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConText(new ContactsManager(), null));
    }
}
