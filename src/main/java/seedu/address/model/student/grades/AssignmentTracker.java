package seedu.address.model.student.grades;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an array of assignments to track the student's grades.
 */
public class AssignmentTracker {

    public static final String MESSAGE_CONSTRAINTS = "Assignment Tracker needs to have positive number of assignments.";

    private Assignment[] assignments;

    /**
     * Constructs an {@code AssignmentTracker}.
     *
     * @param numOfAssignments A valid integer for the number of assignments in a class.
     *
     */
    public AssignmentTracker(int numOfAssignments) {
        checkArgument(isValidAssignments(numOfAssignments), MESSAGE_CONSTRAINTS);
        assignments = new Assignment[numOfAssignments];
        IntStream.range(0, numOfAssignments).forEach(i -> assignments[i] = new Assignment());
    }

    /**
     * Constructs an {@code AssignmentTracker}. With the given assignment tracker list.
     *
     * @param assignmentTracker A list of integer to represent marks for the assignments.
     */
    public AssignmentTracker(List<Integer> assignmentTracker) throws IllegalValueException {
        requireNonNull(assignmentTracker);
        assignments = new Assignment[assignmentTracker.size()];
        for (int i = 0; i < assignmentTracker.size(); i++) {
            assignments[i] = new Assignment(assignmentTracker.get(i));
        }
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

    /**
     * Returns a Json friendly version of the assignmentTracker.
     */
    public List<Integer> getJsonAssignmentTracker() {
        List<Integer> assignmentTracker = new ArrayList<>();
        for (Assignment assignment : assignments) {
            assignmentTracker.add(assignment.getMarks());
        }
        return assignmentTracker;
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

    /**
     * Updates the length of the assignment tracker. Whenever the assignment count changes.
     */
    public void updateAssignmentCountChange(int assignmentCount) {
        if (assignmentCount == assignments.length) {
            return;
        }
        Assignment[] newAssignments = new Assignment[assignmentCount];
        for (int i = 0; i < assignmentCount; i++) {
            if (i < assignments.length) {
                newAssignments[i] = assignments[i];
            } else {
                newAssignments[i] = new Assignment();
            }
        }
        assignments = newAssignments;
    }
}
