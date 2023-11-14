package seedu.address.model.group.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.TaskException;

public class DeadlineTest {

    @Test
    void constructor_validDeadline_createsDeadline() throws TaskException {
        String taskDescription = "Submit report";
        TaskStatus status = TaskStatus.NOT_DONE;
        TaskModule module = TaskModule.CS2103T;
        String by = "24/12/2023 2359";

        Deadline deadline = new Deadline(taskDescription, status, module, by);

        assertEquals(taskDescription, deadline.getTask());
        assertEquals(status, deadline.getStatus());
        assertEquals(module, deadline.getModule());
        assertEquals("D", deadline.getType());
        assertEquals(by, deadline.getBy());
        assertEquals("(by " + by + ")", deadline.getDeadline());
    }

    @Test
    void testToString_withValidDeadline() throws TaskException {
        Deadline deadline = new Deadline("Finish assignment", TaskStatus.NOT_DONE, TaskModule.CS2101,
                "23/09/2023 1300");
        String expectedToString = "D NOT_DONE CS2101 Finish assignment 23/09/2023 1300";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    void testToString_withDoneStatus() throws TaskException {
        Deadline deadline = new Deadline("Finish assignment", TaskStatus.DONE, TaskModule.CS2103T, "23/09/2023 1300");
        String expectedGenerateStr = "D DONE CS2103T Finish assignment 23/09/2023 1300";
        assertEquals(expectedGenerateStr, deadline.toString());
    }
}
