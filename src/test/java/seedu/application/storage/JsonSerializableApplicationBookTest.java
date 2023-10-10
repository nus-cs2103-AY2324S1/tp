package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.commons.util.JsonUtil;
import seedu.application.model.ApplicationBook;
import seedu.application.testutil.TypicalJobs;

public class JsonSerializableApplicationBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableApplicationBookTest");
    private static final Path TYPICAL_JOBS_FILE = TEST_DATA_FOLDER.resolve("typicalJobsApplicationBook.json");
    private static final Path INVALID_JOB_FILE = TEST_DATA_FOLDER.resolve("invalidJobApplicationBook.json");
    private static final Path DUPLICATE_JOB_FILE = TEST_DATA_FOLDER.resolve("duplicateJobApplicationBook.json");

    @Test
    public void toModelType_typicalJobsFile_success() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_JOBS_FILE,
                JsonSerializableApplicationBook.class).get();
        ApplicationBook applicationBookFromFile = dataFromFile.toModelType();
        ApplicationBook typicalJobsApplicationBook = TypicalJobs.getTypicalApplicationBook();
        assertEquals(applicationBookFromFile, typicalJobsApplicationBook);
    }

    @Test
    public void toModelType_invalidJobFile_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(INVALID_JOB_FILE,
                JsonSerializableApplicationBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateJobs_throwsIllegalValueException() throws Exception {
        JsonSerializableApplicationBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_JOB_FILE,
                JsonSerializableApplicationBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableApplicationBook.MESSAGE_DUPLICATE_JOB,
                dataFromFile::toModelType);
    }

}
