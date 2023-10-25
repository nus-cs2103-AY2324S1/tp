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
    private static final Path TYPICAL_DATES_FILE = TEST_DATA_FOLDER.resolve("typicalDatesLoveBook.json");
    private static final Path INVALID_DATE_FILE = TEST_DATA_FOLDER.resolve("invalidDateLoveBook.json");
    private static final Path DUPLICATE_DATE_FILE = TEST_DATA_FOLDER.resolve("duplicateDateLoveBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_DATES_FILE,
                JsonSerializableLoveBook.class).get();
        LoveBook loveBookFromFile = dataFromFile.toModelType();
        LoveBook typicalPersonsLoveBook = TypicalPersons.getTypicalLoveBook();
        assertEquals(loveBookFromFile, typicalPersonsLoveBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(INVALID_DATE_FILE,
                JsonSerializableLoveBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableLoveBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DATE_FILE,
                JsonSerializableLoveBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLoveBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
