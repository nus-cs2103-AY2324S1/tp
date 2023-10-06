package networkbook.storage;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import networkbook.commons.exceptions.IllegalValueException;
import networkbook.commons.util.JsonUtil;
import networkbook.model.NetworkBook;
import networkbook.testutil.TypicalPersons;

public class JsonSerializableNetworkBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNetworkBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsNetworkBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonNetworkBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonNetworkBook.json");

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

}
