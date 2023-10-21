package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.ccacommander.testutil.TypicalMembers.ALICE;
import static seedu.ccacommander.testutil.TypicalMembers.HOON;
import static seedu.ccacommander.testutil.TypicalMembers.IDA;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.ccacommander.commons.exceptions.DataLoadingException;
import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.ReadOnlyCcaCommander;

public class JsonCcaCommanderStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCcaCommanderStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyCcaCommander> readAddressBook(String filePath) throws Exception {
        return new JsonCcaCommanderStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatCcaCommander.json"));
    }

    @Test
    public void readAddressBook_invalidMemberAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidMemberCcaCommander.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidMemberAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidMemberCcaCommander.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        CcaCommander original = getTypicalAddressBook();
        JsonCcaCommanderStorage jsonCcaCommanderStorage = new JsonCcaCommanderStorage(filePath);

        // Ensure there is a file path
        assertNotNull(jsonCcaCommanderStorage.getAddressBookFilePath());

        // Save in new file and read back
        jsonCcaCommanderStorage.saveAddressBook(original, filePath);
        ReadOnlyCcaCommander readBack = jsonCcaCommanderStorage.readAddressBook(filePath).get();
        assertEquals(original, new CcaCommander(readBack));

        // Modify data, overwrite exiting file, and read back
        original.createMember(HOON);
        original.removeMember(ALICE);
        jsonCcaCommanderStorage.saveAddressBook(original, filePath);
        readBack = jsonCcaCommanderStorage.readAddressBook(filePath).get();
        assertEquals(original, new CcaCommander(readBack));

        // Save and read without specifying file path
        original.createMember(IDA);
        jsonCcaCommanderStorage.saveAddressBook(original); // file path not specified
        readBack = jsonCcaCommanderStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new CcaCommander(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyCcaCommander addressBook, String filePath) {
        try {
            new JsonCcaCommanderStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new CcaCommander(), null));
    }
}
