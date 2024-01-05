package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.task.Task.FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;
import static seedu.address.testutil.TypicalTasks.TASK2;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(TASK1.isSameTask(TASK1));

        // null -> returns false
        assertFalse(TASK1.isSameTask(null));

        // same name, same description -> returns true
        Task editedTask1 = new TaskBuilder(TASK1).build();
        assertTrue(TASK1.isSameTask(editedTask1));

        // different name, same description -> returns false
        editedTask1 = new TaskBuilder(TASK1).withDescription("Hello world").build();
        assertFalse(TASK1.isSameTask(editedTask1));

        // same name, different description -> returns false
        editedTask1 = new TaskBuilder(TASK1).withName("Say goodbye").build();
        assertFalse(TASK1.isSameTask(editedTask1));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Task taskCopy = new TaskBuilder(TASK1).build();
        assertTrue(TASK1.equals(taskCopy));

        // same object -> returns true
        assertTrue(TASK1.equals(TASK1));

        // null -> returns false
        assertFalse(TASK1.equals(null));

        // different type -> returns false
        assertFalse(TASK1.equals(5));

        // different task -> returns false
        assertFalse(TASK1.equals(TASK2));

        // different name -> returns false
        Task editedTask = new TaskBuilder(TASK1).withName(TASK2.getName().taskName).build();
        assertFalse(TASK1.equals(editedTask));

        // different description -> returns false
        editedTask = new TaskBuilder(TASK1).withDescription(TASK2.getDescription().description).build();
        assertFalse(TASK1.equals(editedTask));

    }

    @Test
    public void hashCode_sameFields_expectSameHashCode() {
        Task task1 = new Task(new TaskName("Task 1"), new TaskDescription("Description 1"),
                TaskPriority.LOW, LocalDate.parse("22/10/2023", FORMATTER), TaskProgress.NOT_STARTED);
        Task task2 = new Task(new TaskName("Task 1"), new TaskDescription("Description 1"),
                TaskPriority.LOW, LocalDate.parse("22/10/2023", FORMATTER), TaskProgress.NOT_STARTED);

        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void hashCode_differentFields_expectDifferentHashCode() {
        Task task1 = new Task(new TaskName("Task 1"), new TaskDescription("Description 1"),
                TaskPriority.LOW, LocalDate.parse("22/10/2023", FORMATTER), TaskProgress.NOT_STARTED);
        Task task2 = new Task(new TaskName("Task 2"), new TaskDescription("Description 2"),
                TaskPriority.HIGH, LocalDate.parse("23/10/2023", FORMATTER), TaskProgress.NOT_STARTED);

        assertThrows(AssertionError.class, () -> assertEquals(task1.hashCode(), task2.hashCode()));
    }

    @Test
    public void toStringMethod() {
        String expected = String.format("%s{name=%s, description=%s, priority=%s, date=%s, progress=%s}",
                Task.class.getCanonicalName(), TASK1.getName(), TASK1.getDescription(),
                TASK1.getPriority(), TASK1.getDate(), TASK1.getProgress());
        assertEquals(expected, TASK1.toString());
    }
}
