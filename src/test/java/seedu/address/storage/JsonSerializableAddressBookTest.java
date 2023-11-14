package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_MUSICIANS_FILE = TEST_DATA_FOLDER.resolve("typicalMusiciansAddressBook.json");
    private static final Path INVALID_MUSICIAN_FILE = TEST_DATA_FOLDER.resolve("invalidMusicianAddressBook.json");
    private static final Path DUPLICATE_MUSICIAN_FILE = TEST_DATA_FOLDER.resolve("duplicateMusicianAddressBook.json");

    @Test
    public void toModelType_typicalMusiciansFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MUSICIANS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalMusiciansAddressBook = getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalMusiciansAddressBook);
    }

    @Test
    public void toModelType_invalidMusicianFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_MUSICIAN_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMusicians_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MUSICIAN_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_MUSICIAN,
                dataFromFile::toModelType);
    }

}
