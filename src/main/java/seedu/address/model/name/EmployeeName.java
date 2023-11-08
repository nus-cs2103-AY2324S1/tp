package seedu.address.model.name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's name in the ManageHR app.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class EmployeeName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static String validationRegex = "[\\p{Alpha}][\\p{Alpha}. ]*";

    public final String fullName;
    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public EmployeeName(String name) {
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
        if (!(other instanceof EmployeeName)) {
            return false;
        }

        EmployeeName otherName = (EmployeeName) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
