package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.Valid.Contact.ALICE;
import static seedu.address.testutil.TestData.Valid.Contact.AMY;
import static seedu.address.testutil.TestData.Valid.Contact.BOB;
import static seedu.address.testutil.TestData.Valid.Contact.getTypicalContactsManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ContactList;
import seedu.address.model.ContactsManager;

public class JsonContactsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonContactsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readContactsManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readContactsManager(null));
    }

    private java.util.Optional<ContactList> readContactsManager(String filePath) throws Exception {
        return new JsonContactsStorage(Paths.get(filePath)).readContactsManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readContactsManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readContactsManager("notJsonFormatConText.json"));
    }

    @Test
    public void readContactsManager_invalidContactContactsManager_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readContactsManager("invalidContactConText.json"));
    }

    @Test
    public void readContactsManager_invalidAndValidContactContactsManager_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readContactsManager("invalidAndValidContactConText.json"));
    }

    @Test
    public void readAndSaveContactsManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempConText.json");
        ContactsManager original = getTypicalContactsManager();
        JsonContactsStorage jsonContactsStorage = new JsonContactsStorage(filePath);

        // Save in new file and read back
        jsonContactsStorage.saveContactsManager(original, filePath);
        ContactList readBack = jsonContactsStorage.readContactsManager(filePath).get();
        assertEquals(original, new ContactsManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(AMY);
        original.removeContact(ALICE);
        jsonContactsStorage.saveContactsManager(original, filePath);
        readBack = jsonContactsStorage.readContactsManager(filePath).get();
        assertEquals(original, new ContactsManager(readBack));

        // Save and read without specifying file path
        original.addContact(BOB);
        jsonContactsStorage.saveContactsManager(original); // file path not specified
        readBack = jsonContactsStorage.readContactsManager().get(); // file path not specified
        assertEquals(original, new ContactsManager(readBack));

    }

    @Test
    public void saveContactsManager_nullContactsManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactsManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ContactsManager} at the specified {@code filePath}.
     */
    private void saveContactsManager(ContactList contactList, String filePath) {
        try {
            new JsonContactsStorage(Paths.get(filePath))
                    .saveContactsManager(contactList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveContactsManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactsManager(new ContactsManager(), null));
    }
}
