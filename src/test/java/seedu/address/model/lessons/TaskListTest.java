package seedu.address.model.lessons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.lessons.exceptions.DuplicateTaskException;
import seedu.address.model.lessons.exceptions.TaskNotFoundException;



public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(TASK_1));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        taskList.add(TASK_1);
        assertTrue(taskList.contains(TASK_1));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        taskList.add(TASK_1);
        Task editedTask1 = new Task(TASK_1.getDescription());
        assertTrue(taskList.contains(editedTask1));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(TASK_1);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(TASK_1));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(null, TASK_1));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTask(TASK_1, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(TASK_1, TASK_1));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        taskList.add(TASK_1);
        taskList.setTask(TASK_1, TASK_1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_1);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        taskList.add(TASK_1);
        Task editedTask1 = new Task(TASK_1.getDescription());
        taskList.setTask(TASK_1, editedTask1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(editedTask1);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        taskList.add(TASK_1);
        taskList.setTask(TASK_1, TASK_2);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        taskList.add(TASK_1);
        taskList.add(TASK_2);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTask(TASK_1, TASK_2));
    }
    // To remove? -> change in implementation.
    /*@Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }*/
    // To remove? -> change in implementation.
    /*@Test
    public void remove_taskIndexDoesNotExist_IndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(-1));
    }*/

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(TASK_1);
        taskList.remove(0);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_taskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(TASK_1);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        taskList.setTasks(expectedTaskList);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(TASK_1);
        List<Task> newTaskList = Collections.singletonList(TASK_2);
        taskList.setTasks(newTaskList);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(TASK_2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(TASK_1, TASK_1);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assert taskList.toString().isEmpty();
        assertEquals(taskList.asUnmodifiableObservableList().toString(), "[" + taskList.toString() + "]");
    }
}
