package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HYDRATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(ASSIGNMENT));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(ASSIGNMENT);
        assertTrue(taskList.contains(ASSIGNMENT));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(ASSIGNMENT));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(ASSIGNMENT));
    }

    @Test
    public void remove_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(ASSIGNMENT);
        taskList.remove(ASSIGNMENT);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setPersons_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(ASSIGNMENT);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(HYDRATION);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(ASSIGNMENT);
        List<Task> tasks = Collections.singletonList(HYDRATION);
        taskList.setTasks(tasks);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(HYDRATION);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(ASSIGNMENT, ASSIGNMENT);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void toStringMethod() {
        assertEquals(taskList.asUnmodifiableList().toString(), taskList.toString());
    }

    @Test
    public void equalsMethod() {
        taskList.add(ASSIGNMENT);
        TaskList equivalentTaskList = new TaskList();
        equivalentTaskList.add(ASSIGNMENT);
        TaskList nonEquivalentTaskList = new TaskList();
        nonEquivalentTaskList.add(HYDRATION);
        Object nonTaskList = new Object();

        assertTrue(taskList.equals(taskList));

        assertTrue(taskList.equals(equivalentTaskList));

        assertFalse(taskList.equals(nonEquivalentTaskList));

        assertFalse(taskList.equals(nonTaskList));
    }
}
