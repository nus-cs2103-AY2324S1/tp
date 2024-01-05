package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.GradedTestListBook;
import seedu.address.testutil.TypicalGradedTest;

public class JsonSerializableGradedTestListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableGradedTestListTest");
    private static final Path TYPICAL_GRADED_TEST_FILE = TEST_DATA_FOLDER.resolve("typicalGradedTestList.json");
    private static final Path INVALID_GRADED_TEST_FILE = TEST_DATA_FOLDER.resolve("invalidGradedTestList.json");
    private static final Path DUPLICATE_GRADED_TEST_FILE = TEST_DATA_FOLDER.resolve("duplicateGradedTestList.json");

    @Test
    public void toModelType_typicalGradedTestFile_success() throws Exception {
        JsonSerializableGradedTestList dataFromFile = JsonUtil.readJsonFile(TYPICAL_GRADED_TEST_FILE,
                JsonSerializableGradedTestList.class).get();
        GradedTestListBook gradedTestListFromFile = dataFromFile.toModelType();
        GradedTestListBook typicalGradedTestList = TypicalGradedTest.getTypicalGradedTestList();
        assertEquals(gradedTestListFromFile, typicalGradedTestList);
    }

    @Test
    public void toModelType_invalidGradedTestFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGradedTestList dataFromFile = JsonUtil.readJsonFile(INVALID_GRADED_TEST_FILE,
                JsonSerializableGradedTestList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGradedTest_throwsIllegalValueException() throws Exception {
        JsonSerializableGradedTestList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GRADED_TEST_FILE,
                JsonSerializableGradedTestList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableGradedTestList.MESSAGE_DUPLICATE_GRADED_TEST,
                dataFromFile::toModelType);
    }
}
