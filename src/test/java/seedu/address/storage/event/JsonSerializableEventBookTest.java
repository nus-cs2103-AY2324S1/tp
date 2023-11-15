package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EventBook;
import seedu.address.testutil.TypicalEvents;
public class JsonSerializableEventBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventBookTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventBook.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEventBook.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableEventBook.class).get();
        EventBook eventBookFromFile = dataFromFile.toModelType();
        EventBook typicalEventsBook = TypicalEvents.getTypicalEventBook();
        assertEquals(eventBookFromFile, typicalEventsBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENTS_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
