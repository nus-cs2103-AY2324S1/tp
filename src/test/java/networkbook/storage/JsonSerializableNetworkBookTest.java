package networkbook.storage;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.commons.exceptions.DuplicateEntryException;
import networkbook.commons.exceptions.IllegalValueException;
import networkbook.commons.exceptions.NullValueException;
import networkbook.commons.util.JsonUtil;
import networkbook.model.NetworkBook;
import networkbook.testutil.TypicalPersons;

public class JsonSerializableNetworkBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNetworkBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsNetworkBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonNetworkBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonNetworkBook.json");
    private static final Path PERSON_WITH_DUPLICATE_PHONES_FILE =
            TEST_DATA_FOLDER.resolve("personWithDuplicatePhones.json");
    private static final Path PERSON_WITH_NULL_PHONE_FILE = TEST_DATA_FOLDER.resolve("personWithNullPhone.json");
    private static final Path PERSON_WITH_NULL_NAME_FILE = TEST_DATA_FOLDER.resolve("personWithNullName.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableNetworkBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableNetworkBook.class).get();
        NetworkBook networkBookFromFile = dataFromFile.toModelType();
        NetworkBook typicalPersonsNetworkBook = TypicalPersons.getTypicalNetworkBook();
        assertEquals(networkBookFromFile, typicalPersonsNetworkBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNetworkBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableNetworkBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableNetworkBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableNetworkBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableNetworkBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_personWithDuplicateFields_throwsDuplicateEntryException() throws Exception {
        JsonSerializableNetworkBook dataFromFile = JsonUtil.readJsonFile(PERSON_WITH_DUPLICATE_PHONES_FILE,
                JsonSerializableNetworkBook.class).get();
        assertThrows(DuplicateEntryException.class, dataFromFile::toModelType);
    }

    @Test
    public void assertFieldsAreNotNull_nullPerson_throwsNullValueException() {
        List<JsonAdaptedPerson> personList = new ArrayList<>();
        personList.add(null);
        JsonSerializableNetworkBook networkBook = new JsonSerializableNetworkBook(personList);
        assertThrows(NullValueException.class, networkBook::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_personThrowingNullValueException_throwsNullValueException() {
        JsonAdaptedPerson personThrowingNullValueException = new JsonAdaptedPerson(TypicalPersons.BENSON) {
            public void assertFieldsAreNotNull() throws NullValueException {
                throw new NullValueException();
            }
        };
        List<JsonAdaptedPerson> personList = new ArrayList<>();
        personList.add(personThrowingNullValueException);
        JsonSerializableNetworkBook networkBook = new JsonSerializableNetworkBook(personList);
        assertThrows(NullValueException.class, networkBook::assertFieldsAreNotNull);
    }
}
