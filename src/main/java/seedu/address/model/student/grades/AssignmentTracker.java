package seedu.address.model.student.grades;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        IntStream.range(0, numOfAssignments).forEach(i -> assignments[i] = new Assignment());
    }

    public AssignmentTracker(List<Integer> assignmentTracker) {
        requireNonNull(assignmentTracker);
        assignments = new Assignment[assignmentTracker.size()];
        IntStream.range(0, assignmentTracker.size())
                .forEach(i -> assignments[i] = new Assignment(assignmentTracker.get(i)));
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
            ret.append("Assignment " + (i + 1) + ": " + assignments[i].toString() + "\n");
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
        return assignments.hashCode();
    }

}
