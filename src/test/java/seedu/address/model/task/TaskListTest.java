package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;
import static seedu.address.testutil.TypicalTasks.TASK2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(TASK1));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(TASK1);
        assertTrue(taskList.contains(TASK1));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(TASK1);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(TASK1));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.editTask(null, TASK1));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.editTask(TASK1, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.editTask(TASK1, TASK1));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.add(TASK1);
        taskList.editTask(TASK1, TASK1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK1);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.add(TASK1);
        taskList.editTask(TASK1, TASK2);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        taskList.add(TASK1);
        taskList.add(TASK2);
        assertThrows(DuplicateTaskException.class, () -> taskList.editTask(TASK1, TASK2));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(TASK1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(TASK1);
        taskList.remove(TASK1);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        taskList.add(TASK1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK2);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(TASK1);
        List<Task> otherTaskList = Collections.singletonList(TASK1);
        taskList.setTasks(otherTaskList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK1);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(TASK1, TASK1);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getTask_indexWithinBounds_success() {
        taskList.add(TASK1);
        assertEquals(TASK1, taskList.getTask(0));
    }

    @Test
    public void getTask_indexOutsideBounds_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> taskList.getTask(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(taskList.asUnmodifiableObservableList().toString(), taskList.toString());
    }
}
