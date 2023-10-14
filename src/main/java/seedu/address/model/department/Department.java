package seedu.address.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Department in the ManageHR.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDepartmentName(String)}
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS = "Departments names should be alphabets"
            + " and/or ascii characters only";
    public static final String VALIDATION_REGEX = "[A-Za-z\\p{ASCII}&&[^0-9]]+";

    public final String departmentName;

    /**
     * Constructs a {@code Department}.
     *
     * @param departmentName A valid department name.
     */
    public Department(String departmentName) {
        requireNonNull(departmentName);
        checkArgument(isValidDepartmentName(departmentName), MESSAGE_CONSTRAINTS);
        this.departmentName = departmentName;
    }

    /**
     * Returns true if a given string is a valid department name.
     */
    public static boolean isValidDepartmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDepartment = (Department) other;
        return departmentName.equals(otherDepartment.departmentName);
    }

    @Override
    public int hashCode() {
        return departmentName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + departmentName + ']';
    }

}
