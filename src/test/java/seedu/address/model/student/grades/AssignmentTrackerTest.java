package seedu.address.model.student.grades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentTrackerTest {

    @Test
    public void constructor_invalidNumOfAssignments_throwsIllegalArgumentException() {
        int invalidNumOfAssignments = -1;
        assertThrows(IllegalArgumentException.class, () -> new AssignmentTracker(invalidNumOfAssignments));
    }

    @Test
    public void isValidAssignments() {
        // invalid number of assignments
        assertFalse(AssignmentTracker.isValidAssignments(-1)); // -negative number

        // valid number of assignments
        assertTrue(AssignmentTracker.isValidAssignments(1));
        assertTrue(AssignmentTracker.isValidAssignments(2));
        assertTrue(AssignmentTracker.isValidAssignments(10));
    }

    @Test
    public void equals() {
        AssignmentTracker assignmentTracker = new AssignmentTracker(13);

        // same values -> returns true
        assertTrue(assignmentTracker.equals(new AssignmentTracker(13)));

        // same object -> returns true
        assertTrue(assignmentTracker.equals(assignmentTracker));

        // null -> returns false
        assertFalse(assignmentTracker.equals(null));

        // different values -> returns false
        assertFalse(assignmentTracker.equals(new AssignmentTracker(26)));
    }

    @Test
    public void toStringMethod() {
        AssignmentTracker assignmentTracker = new AssignmentTracker(3);

        assertEquals("Assignments and marks:\n"
                + "Assignment 1: 0 / 100\n"
                + "Assignment 2: 0 / 100\n"
                + "Assignment 3: 0 / 100\n", assignmentTracker.toString());
    }

    @Test
    public void test_hashCode() {
        AssignmentTracker assignmentTracker = new AssignmentTracker(13);

        // same values -> returns true
        assertTrue(assignmentTracker.hashCode() == new AssignmentTracker(13).hashCode());

        // same object -> returns true
        assertTrue(assignmentTracker.hashCode() == assignmentTracker.hashCode());

        // null -> returns false
        assertFalse(assignmentTracker.hashCode() == 0);

        // different values -> returns false
        assertFalse(assignmentTracker.hashCode() == new AssignmentTracker(26).hashCode());
    }
}
