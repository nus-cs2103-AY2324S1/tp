package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class DeassignmentCommandTest {
    @Test
    public void equals() {
        Name assignmentName1 = new Name("Assignment1");
        Name assignmentName2 = new Name("Assignment2");

        DeassignmentCommand deassignmentCommand1 = new DeassignmentCommand(assignmentName1);
        DeassignmentCommand deassignmentCommand2 = new DeassignmentCommand(assignmentName1);
        DeassignmentCommand deassignmentCommand3 = new DeassignmentCommand(assignmentName2);

        // Check for equality
        assertTrue(deassignmentCommand1.equals(deassignmentCommand2));

        // Check for inequality
        assertFalse(deassignmentCommand1.equals(deassignmentCommand3));
    }
}
