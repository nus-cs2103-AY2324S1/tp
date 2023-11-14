package seedu.address.model.group.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.CS2101_OP1_UPLOAD;
import static seedu.address.testutil.TypicalTasks.CS2101_PLAN_OP2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.TaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    void constructor_withValidArguments_createsCorrectTaskObject() {
        Task task = new Task("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101, "TODO", "");
        assertEquals("Read book", task.getTask());
        assertEquals(TaskStatus.NOT_DONE, task.getStatus());
        assertEquals(TaskModule.CS2101, task.getModule());
        assertEquals("TODO", task.getType());
        assertEquals("", task.getBy());
    }

    @Test
    void toString_validTask_correctStringRepresentation() {
        Task task = new Task("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101, "TODO", "");
        String expected = "TODO NOT_DONE CS2101 Read book ";
        assertEquals(expected, task.toString());
    }

    @Test
    void setStatus_setsNewStatus_statusIsUpdated() {
        Task task = new Task("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101, "TODO", "");
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void parseDateTime_validDateTimeString_parsesCorrectly() {
        Task task = new Task();
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 9, 23, 23, 59);
        assertEquals(expectedDateTime, task.parseDateTime("23/09/2023 2359"));
    }

    @Test
    void parseDateTime_invalidDateTimeString_throwsException() {
        Task task = new Task();
        assertThrows(IllegalArgumentException.class, () -> {
            task.parseDateTime("invalid-date-time");
        });
    }

    @Test
    void mark_asDone_taskIsMarkedDone() {
        Task task = new Task("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101, "TODO", "");
        task.mark();
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void unMark_asNotDone_taskIsMarkedNotDone() {
        Task task = new Task("Read book", TaskStatus.DONE, TaskModule.CS2101, "TODO", "");
        task.unMark();
        assertEquals(TaskStatus.NOT_DONE, task.getStatus());
    }

    @Test
    void taskException_withMessage_containsCorrectMessage() {
        String errorMessage = "Test error message";
        TaskException exception = new TaskException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void constructor_withInvalidDateFormat_throwsTaskException() {
        assertThrows(TaskException.class, () -> {
            new Deadline("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101, "invalid-date-format");
        });
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task taskCopy = new TaskBuilder(CS2101_OP1_UPLOAD).build();
        assertTrue(CS2101_OP1_UPLOAD.equals(taskCopy));

        // same object -> returns true
        assertTrue(CS2101_OP1_UPLOAD.equals(CS2101_OP1_UPLOAD));

        // null -> returns false
        assertFalse(CS2101_OP1_UPLOAD.equals(null));

        // different type -> returns false
        assertFalse(CS2101_OP1_UPLOAD.equals("CS2101_OP1_UPLOAD"));

        // different task -> returns false
        assertFalse(CS2101_OP1_UPLOAD.equals(CS2101_PLAN_OP2));

        // different description -> returns false
        Task editedDescription = new TaskBuilder(CS2101_OP1_UPLOAD).withDescription("Different Task").build();
        assertFalse(CS2101_OP1_UPLOAD.equals(editedDescription));

        // different status -> returns false
        Task editedStatus = new TaskBuilder(CS2101_OP1_UPLOAD).withStatus(TaskStatus.NOT_DONE).build();
        assertFalse(CS2101_OP1_UPLOAD.equals(editedStatus));

        // different module -> returns false
        Task editedModule = new TaskBuilder(CS2101_OP1_UPLOAD).withModule(TaskModule.CS2103T).build();
        assertFalse(CS2101_OP1_UPLOAD.equals(editedModule));

        // different type -> returns false
        Task editedType = new TaskBuilder(CS2101_OP1_UPLOAD).withType("DEADLINE").build();
        assertFalse(CS2101_OP1_UPLOAD.equals(editedType));

        // different deadline -> returns false
        Task editedDeadline = new TaskBuilder(CS2101_OP1_UPLOAD).withBy("01/01/2024 0000").build();
        assertFalse(CS2101_OP1_UPLOAD.equals(editedDeadline));

    }
}
