package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.task.Task.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class TaskTest {
    private static final String VALID_DESCRIPTION_STRING = "Eat Nuts";
    private static final TaskDescription VALID_TASK_DESCRIPTION = new TaskDescription(VALID_DESCRIPTION_STRING);
    private static final Deadline VALID_DEADLINE = new Deadline("2023-02-11 14:00");
    private static final LocalDateTime VALID_DEADLINE_DATETIME =
            LocalDateTime.parse("2023-02-11 14:00", DATE_TIME_STRING_FORMATTER);

    @Test
    public void constructorTest() {
        assertThrows(NullPointerException.class, () -> new Task((String) null, VALID_DEADLINE_DATETIME));

        assertThrows(NullPointerException.class, () -> new Task((TaskDescription) null, VALID_DEADLINE));

        Task taskWithNoDeadline = new Task(VALID_TASK_DESCRIPTION, new Deadline((String) null));

        assertEquals(taskWithNoDeadline, new Task(VALID_DESCRIPTION_STRING, null));
    }

    @Test
    public void getDescriptionTest() {
        Task testTask = new Task(VALID_DESCRIPTION_STRING, VALID_DEADLINE_DATETIME);
        assertEquals(VALID_TASK_DESCRIPTION, testTask.getDescription());
        assertNotEquals(VALID_DESCRIPTION_STRING, testTask.getDescription());
    }

    @Test
    public void getDescriptionStringTest() {
        Task testTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        assertEquals(VALID_DESCRIPTION_STRING, testTask.getDescriptionString());
    }

    @Test
    public void getDeadlineTest() {
        Task testTask = new Task(VALID_DESCRIPTION_STRING, VALID_DEADLINE_DATETIME);
        assertEquals(VALID_DEADLINE, testTask.getDeadline());
        Task testTask2 = new Task(VALID_DESCRIPTION_STRING, null);
        assertEquals(new Deadline((String) null), testTask2.getDeadline());
    }

    @Test
    public void getDeadlineStringTest() {
        Task testTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        assertEquals("2023-02-11 14:00", testTask.getDeadlineString());

        Task testTask2 = new Task(VALID_DESCRIPTION_STRING, null);
        assertEquals("-", testTask2.getDeadlineString());
    }

    @Test
    public void equalsMethod() {
        Task testTask = new Task(VALID_DESCRIPTION_STRING, VALID_DEADLINE_DATETIME);
        Task equivalentTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        Task nonEquivalentTask = new Task(VALID_DESCRIPTION_STRING, null);
        Task differentDescription = new Task("Eat Fruits", VALID_DEADLINE_DATETIME);
        Object notTask = new Object();

        assertTrue(testTask.equals(testTask));

        assertTrue(testTask.equals(equivalentTask));

        assertFalse(testTask.equals(nonEquivalentTask));

        assertFalse(testTask.equals(differentDescription));

        assertFalse(testTask.equals(notTask));
    }

    @Test
    public void toStringMethod() {
        Task testTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        String expected = Task.class.getCanonicalName() + "{description=" + VALID_TASK_DESCRIPTION + ", deadline="
                + VALID_DEADLINE + "}";
        assertEquals(expected, testTask.toString());
    }

    @Test
    public void deadlineComparatorTest() {
        Comparator<Task> comparator = new Task.TaskDeadlineComparator();

        Task testTask = new Task(VALID_DESCRIPTION_STRING, VALID_DEADLINE_DATETIME);
        Task sameTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        Task taskWithNoDeadline = new Task(VALID_DESCRIPTION_STRING, null);
        Task differentDescription = new Task("Eat Fruits", VALID_DEADLINE_DATETIME);
        Task laterDeadline = new Task(VALID_DESCRIPTION_STRING,
                LocalDateTime.parse("2023-02-11 15:00", DATE_TIME_STRING_FORMATTER));

        assertEquals(0, comparator.compare(testTask, sameTask));
        assertEquals(-1, comparator.compare(testTask, taskWithNoDeadline));
        assertEquals(1, comparator.compare(taskWithNoDeadline, testTask));
        assertTrue(comparator.compare(testTask, differentDescription) > 0);
        assertTrue(comparator.compare(differentDescription, testTask) < 0);
        assertEquals(-1, comparator.compare(testTask, laterDeadline));
        assertEquals(1, comparator.compare(laterDeadline, testTask));
    }

    @Test
    public void descriptionComparatorTest() {
        Comparator<Task> comparator = new Task.TaskDescriptorComparator();

        Task testTask = new Task(VALID_DESCRIPTION_STRING, VALID_DEADLINE_DATETIME);
        Task sameTask = new Task(VALID_TASK_DESCRIPTION, VALID_DEADLINE);
        Task taskWithNoDeadline = new Task(VALID_DESCRIPTION_STRING, null);
        Task differentDescription = new Task("Eat Fruits", VALID_DEADLINE_DATETIME);
        Task laterDeadline = new Task(VALID_DESCRIPTION_STRING,
                LocalDateTime.parse("2023-02-11 15:00", DATE_TIME_STRING_FORMATTER));

        assertEquals(0, comparator.compare(testTask, sameTask));

        assertEquals(0, comparator.compare(testTask, taskWithNoDeadline));

        assertTrue(comparator.compare(testTask, differentDescription) > 0);
        assertTrue(comparator.compare(differentDescription, testTask) < 0);

        assertEquals(0, comparator.compare(testTask, laterDeadline));
    }

    @Test
    public void deadlineComparatorEqualsTest() {
        assertTrue(new Task.TaskDeadlineComparator().equals(new Task.TaskDeadlineComparator()));
    }

    @Test
    public void descriptionComparatorEqualsTest() {
        assertTrue(new Task.TaskDescriptorComparator().equals(new Task.TaskDescriptorComparator()));
    }
}
