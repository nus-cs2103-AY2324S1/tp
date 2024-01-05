package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.SessionListBook;
import seedu.address.testutil.TypicalSessions;

public class JsonSerializableSessionListTest {


    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableSessionListTest");
    private static final Path TYPICAL_SESSIONS_FILE = TEST_DATA_FOLDER.resolve("typicalSessionList.json");
    private static final Path INVALID_SESSION_FILE = TEST_DATA_FOLDER.resolve("invalidSessionList.json");
    private static final Path DUPLICATE_SESSION_FILE = TEST_DATA_FOLDER.resolve("duplicateSessionList.json");

    @Test
    public void toModelType_typicalSessionsFile_success() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SESSIONS_FILE,
                JsonSerializableSessionList.class).get();
        SessionListBook sessionListFromFile = dataFromFile.toModelType();
        SessionListBook typicalSessionList = TypicalSessions.getTypicalSessionList();
        assertEquals(sessionListFromFile, typicalSessionList);
    }

    @Test
    public void toModelType_invalidSessionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(INVALID_SESSION_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSessions_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SESSION_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSessionList.MESSAGE_DUPLICATE_SESSION,
                dataFromFile::toModelType);
    }

}
