package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class AssignmentCommandTest {
    @Test
    public void equals() {
        Name assignmentName1 = new Name("Assignment1");
        Name assignmentName2 = new Name("Assignment2");
        int score = 100;

        AssignmentCommand assignmentCommand1 = new AssignmentCommand(assignmentName1, score);
        AssignmentCommand assignmentCommand2 = new AssignmentCommand(assignmentName1, score);
        AssignmentCommand assignmentCommand3 = new AssignmentCommand(assignmentName2, score);

        // Check for equality
        assertTrue(assignmentCommand1.equals(assignmentCommand2));

        // Check for inequality
        assertFalse(assignmentCommand1.equals(assignmentCommand3));
    }
}
