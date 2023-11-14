package seedu.address.model.group.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.TaskException;

public class TodoTest {

    @Test
    void constructor_validTodo_createsTodo() throws TaskException {
        String taskDescription = "Submit report";
        TaskStatus status = TaskStatus.NOT_DONE;
        TaskModule module = TaskModule.CS2103T;

        Todo deadline = new Todo(taskDescription, status, module);

        assertEquals(taskDescription, deadline.getTask());
        assertEquals(status, deadline.getStatus());
        assertEquals(module, deadline.getModule());
        assertEquals("T", deadline.getType());
        assertEquals("", deadline.getDeadline());
    }

    @Test
    void testToString_withValidTodo() throws TaskException {
        Todo todo = new Todo("Finish assignment", TaskStatus.NOT_DONE, TaskModule.CS2101);
        String expectedToString = "T NOT_DONE CS2101 Finish assignment ";
        assertEquals(expectedToString, todo.toString());
    }

    @Test
    void testToString_withDoneStatus() throws TaskException {
        Todo todo = new Todo("Finish assignment", TaskStatus.DONE, TaskModule.CS2103T);
        String expectedGenerateStr = "T DONE CS2103T Finish assignment ";
        assertEquals(expectedGenerateStr, todo.toString());
    }
}
