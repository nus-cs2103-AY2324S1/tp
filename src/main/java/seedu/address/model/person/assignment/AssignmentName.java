package seedu.address.model.person.assignment;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.assignment.initialise.AssignmentInitialise;
import seedu.address.model.person.assignment.initialise.AssignmentNameInitialise;

/**
 * Represents an assignment name.
 */
public class AssignmentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment does not exist";
    private static final AssignmentNameInitialise checkIfValid = new AssignmentNameInitialise();

    public final String assignmentName;

    /**
     * Creates an assignment name.
     */
    public AssignmentName(String name) {
        requireNonNull(name);
        this.assignmentName = name;
    }

    /**
     * Checks if the assignment name exists using a string.
     *
     * @param test The assignment name as a string.
     * @return Whether the assignment exists.
     */
    public static boolean isValidName(String test) {
        AssignmentInitialise.init();
        return checkIfValid.contains(new AssignmentName(test));
    }

    /**
     * Checks if the assignment name exists using an AssignmentName object.
     *
     * @param test The assignment name as an AssignmentName.
     * @return Whether the assignment exists.
     */
    public static boolean isValidName(AssignmentName test) {
        AssignmentInitialise.init();
        return checkIfValid.contains(test);
    }

    @Override
    public String toString() {
        return this.assignmentName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignmentName)) {
            return false;
        }

        AssignmentName otherName = (AssignmentName) other;
        return assignmentName.equals(otherName.assignmentName);
    }

    @Override
    public int hashCode() {
        return assignmentName.hashCode();
    }
}
