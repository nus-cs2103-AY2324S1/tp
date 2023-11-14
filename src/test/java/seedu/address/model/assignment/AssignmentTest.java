package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_NAME_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_TAG_ASSIGNMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT1;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Assignment assignment = new AssignmentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> assignment.getTags().remove(0));
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(ASSIGNMENT1.isDuplicate(ASSIGNMENT1));

        // null -> returns false
        assertFalse(ASSIGNMENT1.isDuplicate(null));

        // same name, all other attributes different -> returns true
        Assignment editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withDescription(VALID_DESCRIPTION_ASSIGNMENT)
                        .withTags(VALID_TAG_ASSIGNMENT)
                .withDeadline(LocalDateTime.now())
                .withPlannedDate(LocalDateTime.now())
                        .withStatus(true).build();
        assertTrue(ASSIGNMENT1.isDuplicate(editedAssignment));

        // different name, all other attributes same -> returns false
        editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withName(VALID_NAME_ASSIGNMENT).build();
        assertFalse(ASSIGNMENT1.isDuplicate(editedAssignment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment assignmentCopy = new AssignmentBuilder(ASSIGNMENT1).build();
        assertTrue(ASSIGNMENT1.equals(assignmentCopy));

        // same object -> returns true
        assertTrue(ASSIGNMENT1.equals(ASSIGNMENT1));

        // null -> returns false
        assertFalse(ASSIGNMENT1.equals(null));

        // different type -> returns false
        assertFalse(ASSIGNMENT1.equals(5));

        // different person -> returns false
        assertFalse(ASSIGNMENT1.equals(ASSIGNMENT2));

        // different name -> returns false
        Assignment editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withName(VALID_NAME_ASSIGNMENT).build();
        assertFalse(ASSIGNMENT1.equals(editedAssignment));

        // different endDate -> returns false
        editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withDeadline(LocalDateTime.now()).build();
        assertFalse(ASSIGNMENT1.equals(editedAssignment));

        // different planned date -> returns false
        editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withPlannedDate(LocalDateTime.now()).build();
        assertFalse(ASSIGNMENT1.equals(editedAssignment));

        // different description -> returns false
        editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withDescription(VALID_DESCRIPTION_ASSIGNMENT).build();
        assertFalse(ASSIGNMENT1.equals(editedAssignment));

        // different tags -> returns false
        editedAssignment = new AssignmentBuilder(ASSIGNMENT1).withTags(VALID_TAG_ASSIGNMENT).build();
        assertFalse(ASSIGNMENT1.equals(editedAssignment));
    }

    @Test
    public void toStringMethod() {
        String expected = Assignment.class.getCanonicalName() + "{name=" + ASSIGNMENT1.getName() + ", completeness="
                + ASSIGNMENT1.getStatus() + ", description=" + ASSIGNMENT1.getDescription() + ", plannedFinishDate="
                + ASSIGNMENT1.getPlannedFinishDate() + ", end="
                + ASSIGNMENT1.getEnd() + ", tags=" + ASSIGNMENT1.getTags() + "}";
        assertEquals(expected, ASSIGNMENT1.toString());
    }
}
