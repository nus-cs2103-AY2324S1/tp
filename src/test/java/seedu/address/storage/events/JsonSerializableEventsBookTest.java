package seedu.address.storage.events;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EventsBook;
import seedu.address.testutil.TypicalEvents;

public class JsonSerializableEventsBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventsBookTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsBook.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEventsBook.json");
    private static final Path DUPLICATE_EVENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateEventsBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableEventsBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableEventsBook.class).get();
        EventsBook eventsBookFromFile = dataFromFile.toModelType();
        EventsBook typicalEventEventsBook = TypicalEvents.getTypicalEventsBook();
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventsBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENTS_FILE,
                JsonSerializableEventsBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableEventsBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENTS_FILE,
                JsonSerializableEventsBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventsBook.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
