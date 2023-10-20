package seedu.address.model.student.grades;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;

/**
 * Represents an array of assignments to track the students grades.
 */
public class AssignmentTracker {

    public static final String MESSAGE_CONSTRAINTS = "Assignment Tracker needs to have positive number of assignments.";

    public final Assignment[] assignments;

    /**
     * Constructs an {@code AssignmentTracker}.
     *
     * @param numOfAssignments A valid integer for the number of assignments in a class.
     *
     */
    public AssignmentTracker(int numOfAssignments) {
        checkArgument(isValidAssignments(numOfAssignments), MESSAGE_CONSTRAINTS);
        assignments = new Assignment[numOfAssignments];
        Arrays.fill(assignments, new Assignment());
    }

    /**
     * Returns true if a given number is a valid amount of assignments
     */
    public static boolean isValidAssignments(int numOfAssignments) {
        return numOfAssignments >= 0;
    }

    /**
     * Change the mark of the assignment.
     * @param index Position of assignment in array.
     * @param marks Marks to be changed to.
     */
    public void editMarks(Index index, int marks) {
        assignments[index.getZeroBased()].setMarks(marks);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Assignments and marks:\n");
        for (int i = 0; i < assignments.length; i++) {
            ret.append("Assignment ").append(i + 1).append(": ").append(assignments[i].toString()).append("\n");
        }
        return ret.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentTracker)) {
            return false;
        }

        AssignmentTracker otherAssignmentTracker = (AssignmentTracker) other;
        return Arrays.equals(assignments, otherAssignmentTracker.assignments);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(assignments);
    }
}
