package seedu.letsgethired.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.commons.util.JsonUtil;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.testutil.TypicalInternApplications;

public class JsonSerializableInternTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInternTrackerTest");
    private static final Path TYPICAL_INTERN_APPLICATIONS_FILE =
            TEST_DATA_FOLDER.resolve("typicalInternApplicationInternTracker.json");
    private static final Path INVALID_INTERN_APPLICATION_FILE =
            TEST_DATA_FOLDER.resolve("invalidInternApplicationInternTracker.json");
    private static final Path DUPLICATE_INTERN_APPLICATION_FILE =
            TEST_DATA_FOLDER.resolve("duplicateInternApplicationInternTracker.json");

    @Test
    public void toModelType_typicalInternApplicationsFile_success() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_INTERN_APPLICATIONS_FILE,
                JsonSerializableInternTracker.class).get();
        InternTracker internTrackerFromFile = dataFromFile.toModelType();
        InternTracker typicalInternApplicationsInternTracker = TypicalInternApplications.getTypicalInternTracker();
        assertEquals(internTrackerFromFile, typicalInternApplicationsInternTracker);
    }

    @Test
    public void toModelType_invalidInternApplicationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(INVALID_INTERN_APPLICATION_FILE,
                JsonSerializableInternTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateInternApplications_throwsIllegalValueException() throws Exception {
        JsonSerializableInternTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INTERN_APPLICATION_FILE,
                JsonSerializableInternTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternTracker.MESSAGE_DUPLICATE_INTERN_APPLICATION,
                dataFromFile::toModelType);
    }

}
