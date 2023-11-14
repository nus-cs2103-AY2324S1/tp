package seedu.address.model.group.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.TaskException;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void construct_emptyTaskList() {
        assertTrue(taskList.isEmpty());
    }

    @Test
    void addTask_singleTask_taskAdded() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    void deleteTask_taskPresent_taskDeleted() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertTrue(taskList.isEmpty());
    }

    @Test
    void getTask_validIndex_taskReturned() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void isTaskInAllTasks_taskExists_trueReturned() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        assertTrue(taskList.isTaskInAllTasks("T", "Read book"));
    }

    @Test
    void addTasks_multipleTasks_tasksAdded() {
        Task task1 = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        Task task2 = null;
        try {
            task2 = new Deadline("Submit assignment", TaskStatus.NOT_DONE, TaskModule.CS2103T, "23/09/2023 2359");
        } catch (TaskException e) {
            throw new RuntimeException(e);
        }
        taskList.addTasks(Arrays.asList(task1, task2));
        assertEquals(2, taskList.getTasks().size());
    }

    @Test
    void isEmpty_taskListEmpty_trueReturned() {
        assertTrue(taskList.isEmpty());
    }

    @Test
    void getTaskList_tasksPresent_copyReturned() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        assertEquals(taskList.getTasks(), taskList.getTaskList());
        assertFalse(taskList.getTasks() == taskList.getTaskList()); // Checking for actual copy, not same reference
    }

    @Test
    void toString_tasksInList_correctStringRepresentation() {
        Task task = new Todo("Read book", TaskStatus.NOT_DONE, TaskModule.CS2101);
        taskList.addTask(task);
        String expected = "❌ T 1. CS2101 Read book \n";
        assertEquals(expected, taskList.toString());
    }
}
