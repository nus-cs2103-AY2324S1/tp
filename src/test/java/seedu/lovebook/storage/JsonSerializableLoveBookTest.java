package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.commons.util.JsonUtil;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.testutil.TypicalPersons;

public class JsonSerializableLoveBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLoveBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalDatesLoveBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidDateLoveBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateDateLoveBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableLoveBook.class).get();
        LoveBook LoveBookFromFile = dataFromFile.toModelType();
        LoveBook typicalPersonsLoveBook = TypicalPersons.getTypicalLoveBook();
        assertEquals(LoveBookFromFile, typicalPersonsLoveBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableLoveBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableLoveBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLoveBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
