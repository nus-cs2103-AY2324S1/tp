package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Group;
import seedu.address.model.person.Name;

public class AssignmentGroupCommandTest {
    @Test
    public void equals() {
        Name assignmentName1 = new Name("Assignment1");
        Name assignmentName2 = new Name("Assignment2");
        Group group = new Group("Group1");
        int score = 100;

        AssignmentGroupCommand assignmentGroupCommand1 = new AssignmentGroupCommand(group, assignmentName1, score);
        AssignmentGroupCommand assignmentGroupCommand2 = new AssignmentGroupCommand(group, assignmentName1, score);
        AssignmentGroupCommand assignmentGroupCommand3 = new AssignmentGroupCommand(group, assignmentName2, score);

        // Check for equality
        assertTrue(assignmentGroupCommand1.equals(assignmentGroupCommand2));

        // Check for inequality
        assertFalse(assignmentGroupCommand1.equals(assignmentGroupCommand3));
    }
}
