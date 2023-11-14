package seedu.address.model.name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Department's name in the ManageHR app.
 * Guarantees: immutable; is valid as declared in {@link #isValidName}
 */
public class DepartmentName {

    public static final String MESSAGE_CONSTRAINTS = "Departments names should be alphabets"
            + " and/or ascii characters only";
    private static String validationRegex = "[A-Za-z\\p{ASCII}&&[^0-9]]+";

    public final String fullName;
    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public DepartmentName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(validationRegex);
    }
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DepartmentName)) {
            return false;
        }

        DepartmentName otherName = (DepartmentName) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
