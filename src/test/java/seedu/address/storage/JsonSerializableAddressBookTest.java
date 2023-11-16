package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_DEVELOPER_FILE = TEST_DATA_FOLDER.resolve("typicalDevelopersAddressBook.json");

    private static final Path INVALID_DEVELOPER_FILE = TEST_DATA_FOLDER.resolve("invalidDeveloperAddressBook.json");
    private static final Path DUPLICATE_DEVELOPER_FILE = TEST_DATA_FOLDER.resolve("duplicateDeveloperAddressBook.json");

    /*@Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DEVELOPER_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalDevelopers.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_DEVELOPER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DEVELOPER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_DEVELOPER,
                dataFromFile::toModelType);
    }*/
}
