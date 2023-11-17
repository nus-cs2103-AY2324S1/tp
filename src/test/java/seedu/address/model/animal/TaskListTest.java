package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.storage.JsonAdaptedTask;

public class TaskListTest {
    private static final List<Task> EMPTY_TASK_LIST = new ArrayList<>();

    private static final List<Task> NON_EMPTY_TASK_LIST = List.of(
                    new Task("Walk dog"),
                    new Task("Feed dog"),
                    new Task("Medicate dog"));

    @Test
    public void testGetTasks_withEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(EMPTY_TASK_LIST, taskList.getTasks());
    }

    @Test
    public void testGetTasks_withNonEmptyTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
        }
        assertEquals(NON_EMPTY_TASK_LIST, taskList.getTasks());
    }


    @Test
    public void testDeleteTaskByIndex_withEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTaskByIndex(
                Index.fromZeroBased(0)));
    }

    @Test
    public void testDeleteTaskByIndex_withNonEmptyTaskList() {
        TaskList toTestTaskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            toTestTaskList.addTask(task);
        }
        TaskList expectedTaskList = new TaskList();
        for (int i = 1; i < NON_EMPTY_TASK_LIST.size(); i++) {
            expectedTaskList.addTask(NON_EMPTY_TASK_LIST.get(i));
        }
        toTestTaskList.deleteTaskByIndex(Index.fromZeroBased(0));
        assertEquals(expectedTaskList, toTestTaskList);
    }

    @Test
    public void testIsEmpty_withEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void testIsEmpty_withNonEmptyTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
        }
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void testGetSerializedTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
        }
        List<JsonAdaptedTask> jsonAdaptedTaskList =
                taskList.getSerializedTaskList();
        for (int i = 0; i < NON_EMPTY_TASK_LIST.size(); i++) {
            assertEquals(new JsonAdaptedTask(NON_EMPTY_TASK_LIST.get(i)),
                    jsonAdaptedTaskList.get(i));
        }
    }

    @Test
    public void testAddAllTasks_withNonEmptyTaskList() {
        TaskList taskList = new TaskList();
        taskList.addAllTasks(NON_EMPTY_TASK_LIST);
        assertEquals(NON_EMPTY_TASK_LIST, taskList.getTasks());
    }

    @Test
    public void testAddAllTasks_withEmptyTaskList() {
        TaskList taskList = new TaskList();
        taskList.addAllTasks(EMPTY_TASK_LIST);
        assertEquals(EMPTY_TASK_LIST, taskList.getTasks());
    }

    @Test
    public void testEquals_withSameTasks() {
        TaskList taskList1 = new TaskList();
        TaskList taskList2 = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList1.addTask(task);
            taskList2.addTask(task);
        }
        assertEquals(taskList1, taskList2);
    }

    @Test
    public void testEquals_withEmptyTask() {
        TaskList taskList1 = new TaskList();
        TaskList emptyTaskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList1.addTask(task);
        }
        assertNotEquals(emptyTaskList, taskList1);
    }

    @Test
    public void testEqual_withSameTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
        }
        assertEquals(taskList, taskList);
    }

    @Test
    public void testHashCode() {
        TaskList taskList = new TaskList();
        assertEquals(taskList, taskList);
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        StringBuilder expectedString = new StringBuilder();
        for (Task task : NON_EMPTY_TASK_LIST) {
            taskList.addTask(task);
            expectedString.append(task.toString()).append("\n");
        }
        expectedString.deleteCharAt(expectedString.length() - 1);
        assertEquals(expectedString.toString(),
                taskList.toString());
    }
}
