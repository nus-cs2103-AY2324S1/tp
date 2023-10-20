package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Contacts;
import seedu.address.model.ReadOnlyContacts;
import seedu.address.testutil.TestData;

public class JsonContactsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src",
        "test",
        "data",
        "JsonContactsStorageTest"
    );

    @TempDir
    public static Path tempDir;

    private Optional<? extends ReadOnlyContacts> read(String fileName) throws DataLoadingException {
        return new JsonContactsStorage(
            JsonContactsStorageTest.TEST_DATA_FOLDER.resolve(fileName)
        ).readContacts();
    }

    @Test
    public void readContacts_typical_successfullyRead() throws DataLoadingException {
        ReadOnlyContacts expected = TestData.Valid.Contact.getTypicalContacts();
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
        Path tempPath = JsonContactsStorageTest.tempDir.resolve("tempContacts.json");

        Contacts contacts = TestData.Valid.Contact.getTypicalContacts();

        ContactsStorage storage = new JsonContactsStorage(tempPath);

        // Try writing when the file doesn't exist
        storage.saveContacts(contacts);
        ReadOnlyContacts readBack = storage.readContacts().get();
        assertEquals(contacts, readBack);

        contacts.add(TestData.Valid.Contact.AMY);
        contacts.remove(TestData.Valid.Contact.ALICE);

        // Try saving when the file exists
        storage.saveContacts(contacts);
        readBack = storage.readContacts().get();
        assertEquals(contacts, readBack);

    }
}
