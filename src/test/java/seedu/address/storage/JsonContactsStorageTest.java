package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.testutil.TestData;
import seedu.address.model.Contacts;



public class JsonContactsStorageTest {
    @TempDir
    public static Path TEMP_DIR;

    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src",
        "test",
        "data",
        "JsonContactsStorageTest"
    );

    private Optional<? extends ReadOnlyContacts> read(String fileName) throws DataLoadingException {
        return new JsonContactsStorage(
            JsonContactsStorageTest.TEST_DATA_FOLDER.resolve(fileName)
        ).readContacts();
    }

    @Test
    public void readContacts_typical_successfullyRead() throws DataLoadingException {
        ReadOnlyContacts expected = TestData.getTypicalContacts();
        ReadOnlyContacts actual = this.read("typicalContacts.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readContacts_missingEntries_defaultsUsed() throws DataLoadingException {
        ReadOnlyContacts expected = new Contacts();
        ReadOnlyContacts actual = this.read("../empty.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void saveContacts() throws DataLoadingException, IOException {
        Path tempPath = JsonContactsStorageTest.TEMP_DIR.resolve("tempContacts.json");

        Contacts contacts = TestData.getTypicalContacts();

        ContactsStorage storage = new JsonContactsStorage(tempPath);

        // Try writing when the file doesn't exist
        storage.saveContacts(contacts);
        ReadOnlyContacts readBack = storage.readContacts().get();
        assertEquals(contacts, readBack);

        contacts.add(TestData.HOON);
        contacts.remove(TestData.ALICE);

        // Try saving when the file exists
        storage.saveContacts(contacts);
        readBack = storage.readContacts().get();
        assertEquals(contacts, readBack);

    }
}
