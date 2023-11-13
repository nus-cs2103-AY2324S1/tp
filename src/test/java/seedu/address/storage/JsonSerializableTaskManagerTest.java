package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.task.TaskManager;
import seedu.address.testutil.TypicalTasks;

public class JsonSerializableTaskManagerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableTaskManagerTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTaskManager.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskManager.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTaskManager.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTaskManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTaskManager.class).get();
        TaskManager taskManagerFromFile = dataFromFile.toModelType();
        TaskManager typicalTasksTaskManager = TypicalTasks.getTypicalTaskManager();
        assertEquals(taskManagerFromFile, typicalTasksTaskManager);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskManager dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTaskManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskManager.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }
}
